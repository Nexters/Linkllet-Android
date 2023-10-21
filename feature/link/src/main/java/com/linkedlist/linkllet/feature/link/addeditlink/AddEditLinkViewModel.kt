package com.linkedlist.linkllet.feature.link.addeditlink

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.linkedlist.linkllet.core.data.repository.LinkRepository
import com.linkedlist.linkllet.feature.link.model.FolderType
import com.linkedlist.linkllet.feature.link.model.FolderUiModel
import com.linkedlist.linkllet.feature.link.model.toFolderType
import com.linkedlist.linkllet.feature.link.model.toFolderUiModel
import com.linkedlist.linkllet.feature.link.navigation.EMPTY_LINK
import com.linkedlist.linkllet.feature.link.navigation.FOLDER_ID
import com.linkedlist.linkllet.feature.link.navigation.LINK_URL
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.regex.Pattern
import javax.inject.Inject

data class AddEditLinkUiState(
    val link: String = "",
    val title: String = "",
    val folders: List<FolderUiModel> = emptyList(),
    val isButtonEmphasized : Boolean = false,
    val isLoading: Boolean = false,
    val isLinkSaved: Boolean = false
)
enum class AddEditLinkError{
    READY, TITLE_BLANK, LINK_BLANK, TITLE_LINK_BLANK, NETWORK_ERROR,NOT_VALID_URL
}

@HiltViewModel
class AddEditLinkViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val linkRepository: LinkRepository
) : ViewModel() {

    private val folderId = savedStateHandle.get<Long?>(
        key = FOLDER_ID
    ) ?: -1

    private val linkUrl = savedStateHandle.get<String?>(
        key = LINK_URL
    ) ?: ""

    private val _error = MutableStateFlow<AddEditLinkError>(AddEditLinkError.READY)
    val error : StateFlow<AddEditLinkError> = _error.asStateFlow()

    private val _snackbarState = MutableStateFlow("")
    val snackbarState: StateFlow<String> = _snackbarState.asStateFlow()

    private val _uiState = MutableStateFlow(
        AddEditLinkUiState(
            link = getValidUrl() ?: (getClipboardUrl() ?: "")
        )
    )
    val uiState: StateFlow<AddEditLinkUiState> = _uiState.asStateFlow()

    fun getChangedInputs() : Boolean {
        return uiState.value.title.trim().isNotBlank() || uiState.value.link.trim().isNotBlank()
                || if(folderId != -1L) uiState.value.folders.find { it.isSelected }?.id != folderId
                    else uiState.value.folders.find { it.isSelected }?.folderType != FolderType.DEFAULT
    }

    fun updateIsButtonEmphasized(emphasized : Boolean){
        _uiState.value = uiState.value.copy(
            isButtonEmphasized = emphasized
        )
    }

    fun updateSnackbarState(state : String){
        _snackbarState.value = state
    }

    fun updateError(error : AddEditLinkError){
        _error.value = error
    }

    fun checkTitleAndLink() : Boolean {
        val isValidTitle = uiState.value.title.isNotBlank()
        val isValidLink = uiState.value.link.isNotBlank()
        return if(!isValidTitle && !isValidLink){
            _error.value = AddEditLinkError.TITLE_LINK_BLANK
            false
        }else if(!isValidTitle ){
            _error.value = AddEditLinkError.TITLE_BLANK
            false
        }else if(!isValidLink){
            _error.value = AddEditLinkError.LINK_BLANK
            false
        }else true
    }

    fun fetchFolders(){
        viewModelScope.launch {
            try {
                linkRepository.getFolders()
                    .catch {
                        _error.emit(AddEditLinkError.NETWORK_ERROR)
                    }.collect {
                        try {
                            _uiState.emit(
                                uiState.value.copy(
                                    folders = it.map {
                                        if(folderId == -1L ) {
                                            it.toFolderUiModel(it.type.toFolderType() == FolderType.DEFAULT)
                                        }else {
                                            it.toFolderUiModel(it.id == folderId)
                                        }
                                    }
                                )
                            )
                        } catch (e: Throwable) {
                            _error.emit(AddEditLinkError.NETWORK_ERROR)
                        }
                    }
            }catch (e: Exception){
                _error.emit(AddEditLinkError.NETWORK_ERROR)
            }

        }
    }

    fun addLink() {
        viewModelScope.launch {
            try {
                if(checkTitleAndLink()){
                    linkRepository.addLink(
                        id = uiState.value.folders.firstOrNull{it.isSelected}?.id ?: 0,
                        name = uiState.value.title.trim(),
                        url = uiState.value.link.trim()
                    ).catch {
                        _error.emit(AddEditLinkError.NETWORK_ERROR)
                    }.collect {
                        try {
                            _uiState.emit(uiState.value.copy(
                                isLinkSaved = true
                            ))
                        } catch (e: Throwable) {
                            if(e.message == null) _error.emit(AddEditLinkError.NETWORK_ERROR)
                            else {
                                e.message?.let {
                                    _snackbarState.emit(it)
                                }
                            }
                        }
                    }
                }else {
                    _snackbarState.emit("정보를 입력해주세요.")
                }
            }catch (e: Exception){
                _error.emit(AddEditLinkError.NETWORK_ERROR)
            }

        }
    }

    fun updateLink(link: String) {
        _uiState.update {
            it.copy(link = link)
        }

        if(link.isNotBlank() && uiState.value.title.isNotBlank()) _uiState.value = uiState.value.copy(isButtonEmphasized = true)
        else _uiState.value = uiState.value.copy(isButtonEmphasized = false)

        if(error.value == AddEditLinkError.TITLE_LINK_BLANK) _error.value = AddEditLinkError.TITLE_BLANK
        else if(error.value == AddEditLinkError.LINK_BLANK) _error.value = AddEditLinkError.READY
    }

    fun updateTitle(title: String) {
        _uiState.update {
            it.copy(title = title)
        }

        if(title.isNotBlank() && uiState.value.link.isNotBlank()) _uiState.value = uiState.value.copy(isButtonEmphasized = true)
        else _uiState.value = uiState.value.copy(isButtonEmphasized = false)

        if(error.value == AddEditLinkError.TITLE_LINK_BLANK) _error.value = AddEditLinkError.LINK_BLANK
        else if(error.value == AddEditLinkError.TITLE_BLANK) _error.value = AddEditLinkError.READY
    }

    fun updateFolder(title: String) {
        _uiState.update {
            it.copy(folders = it.folders.map {
                if (it.name == title) it.copy(isSelected = true)
                else it.copy(isSelected = false)
            })
        }
    }

    fun getClipboardUrl() : String? {
        return try {
            if(linkUrl== EMPTY_LINK) null
            else if(linkUrl.isNotBlank()){
                Uri.decode(linkUrl)
            }else {
                null
            }
        }catch (e: Exception){
            null
        }

    }

    fun getValidUrl() : String? {
        val sharedUrl = savedStateHandle.getStateFlow(NavController.KEY_DEEP_LINK_INTENT,Intent()).value.getStringExtra(Intent.EXTRA_TEXT) ?: return null
        return if(isValidUrl(sharedUrl)){
            sharedUrl
        }else {
            _error.value = AddEditLinkError.NOT_VALID_URL
            null
        }
    }

    private fun isValidUrl(url : String ) : Boolean {
        val pattern = Pattern.compile(
            "^(https?|ftp)://[\\w-]+(\\.[\\w-]+)+([\\w.,@?^=%&:/~+#-]*[\\w@?^=%&/~+#-])?\$",
            Pattern.CASE_INSENSITIVE
        )
        val matcher = pattern.matcher(url)
        return matcher.matches()
    }
}

