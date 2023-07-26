package com.linkedlist.linkllet.feature.home.folder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.linkedlist.linkellet.core.data.repository.LinkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class Event {
    object CloseScreen : Event()
    data class ShowToast(val message: String) : Event()
}

data class AddEditFolderUiState(
    val folderName: String = "",
    val error: Boolean = false,
    val cancelDialogVisibility: Boolean = false,
)

@HiltViewModel
class AddEditFolderViewModel @Inject constructor(
    private val linkRepository: LinkRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(AddEditFolderUiState())
    val uiState: StateFlow<AddEditFolderUiState> = _uiState.asStateFlow()

    private val _eventsFlow: MutableSharedFlow<Event> = MutableSharedFlow()
    val eventsFlow = _eventsFlow.asSharedFlow()

    fun updateFolderName(newFolderName: String) {
        _uiState.update {
            it.copy(
                folderName = newFolderName,
                error = false,
            )
        }
    }

    fun addFolder() {
        val folderName = uiState.value.folderName
        if(folderName.trim().isBlank()) {
            _uiState.update { it.copy(folderName = folderName.trim(), error = true) }
            viewModelScope.launch {
                _eventsFlow.emit(Event.ShowToast("폴더 제목을 입력해 주세요"))
            }
            return
        }

        viewModelScope.launch {
            linkRepository.addFolder(uiState.value.folderName).collect { result ->
                result.onSuccess {
                    _eventsFlow.emit(Event.CloseScreen)
                    _eventsFlow.emit(Event.ShowToast("폴더가 추가되었어요"))
                }.onFailure {
                    _eventsFlow.emit(Event.ShowToast("폴더 저장에 실패했어요"))
                }
            }
        }
    }

    fun navigateUp() {
        if(uiState.value.folderName.trim().isBlank()) {
            viewModelScope.launch {
                _eventsFlow.emit(Event.CloseScreen)
            }
        } else _uiState.update { it.copy(cancelDialogVisibility = true) }
    }

    fun hideCancelDialog() {
        _uiState.update {
            it.copy(cancelDialogVisibility = false)
        }
    }
}