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
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class Event {
    object CloseScreen : Event()
}

@HiltViewModel
class AddEditFolderViewModel @Inject constructor(
    private val linkRepository: LinkRepository,
) : ViewModel() {
    private val _folderName = MutableStateFlow("")
    val folderName: StateFlow<String> = _folderName.asStateFlow()

    private val _eventFlow: MutableSharedFlow<Event> = MutableSharedFlow()
    val eventFlow = _eventFlow.asSharedFlow()

    fun updateFolderName(newFolderName: String) {
        _folderName.value = newFolderName
    }

    fun addFolder() {
        viewModelScope.launch {
            linkRepository.addFolder(folderName.value).collect { result ->
                result.onSuccess {
                    _eventFlow.emit(Event.CloseScreen)
                }
            }
        }
    }
}