package com.linkedlist.linkllet.feature.link.addeditlink

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.linkedlist.linkellet.core.data.repository.LinkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class Folder(
    val id : Long,
    val name: String = "",
    val isSelected: Boolean = false
)

data class AddEditLinkUiState(
    val link: String = "",
    val title: String = "",
    val folders: List<Folder> = emptyList(),
    val isLoading: Boolean = false,
    val isLinkSaved: Boolean = false
)
enum class AddEditLinkError{
    READY, TITLE_BLANK, LINK_BLANK, TITLE_LINK_BLANK, NETWORK_ERROR
}

@HiltViewModel
class AddEditLinkViewModel @Inject constructor(
    private val linkRepository: LinkRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(AddEditLinkUiState())
    val uiState: StateFlow<AddEditLinkUiState> = _uiState.asStateFlow()

    private val _error = MutableStateFlow<AddEditLinkError>(AddEditLinkError.READY)
    val error : StateFlow<AddEditLinkError> = _error.asStateFlow()

    init {
        fetchFolders()
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
                        it.onSuccess {
                            _uiState.emit(
                                uiState.value.copy(
                                    folders = it.map {
                                        if(it.type == "DEFAULT")  Folder(id = it.id, name = it.name, isSelected = true)
                                        else  Folder(id = it.id, name = it.name)
                                    }
                                )
                            )
                        }.onFailure {
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
                        name = uiState.value.title,
                        url = uiState.value.link
                    ).catch {
                        _error.emit(AddEditLinkError.NETWORK_ERROR)
                    }.collect {
                        it.onSuccess {
                            _uiState.emit(uiState.value.copy(
                                isLinkSaved = true
                            ))
                        }.onFailure {
                            _error.emit(AddEditLinkError.NETWORK_ERROR)
                        }

                    }
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
        if(error.value == AddEditLinkError.TITLE_LINK_BLANK) _error.value = AddEditLinkError.TITLE_BLANK
        else if(error.value == AddEditLinkError.LINK_BLANK) _error.value = AddEditLinkError.READY
    }

    fun updateTitle(title: String) {
        _uiState.update {
            it.copy(title = title)
        }
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
}