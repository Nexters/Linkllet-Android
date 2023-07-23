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

    private val _eventFlow: MutableSharedFlow<Event> = MutableSharedFlow()
    val eventFlow = _eventFlow.asSharedFlow()

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
            return
        }

        viewModelScope.launch {
            linkRepository.addFolder(uiState.value.folderName).collect { result ->
                result.onSuccess {
                    _eventFlow.emit(Event.CloseScreen)
                }
            }
        }
    }

    fun navigateUp() {
        if(uiState.value.folderName.trim().isBlank()) {
            viewModelScope.launch {
                _eventFlow.emit(Event.CloseScreen)
            }
        } else _uiState.update { it.copy(cancelDialogVisibility = true) }
    }

    fun hideCancelDialog() {
        _uiState.update {
            it.copy(cancelDialogVisibility = false)
        }
    }
}