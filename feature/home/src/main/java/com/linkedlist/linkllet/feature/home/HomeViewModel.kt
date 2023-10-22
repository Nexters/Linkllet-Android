package com.linkedlist.linkllet.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.linkedlist.linkllet.core.data.repository.AuthRepository
import com.linkedlist.linkllet.core.data.repository.LinkRepository
import com.linkedlist.linkllet.core.ui.FolderModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import javax.inject.Inject

sealed class Event {
    data class Error(val message: String) : Event()
}

data class HomeUiState(
    val folders: List<FolderModel> = emptyList(),
    val expanded: Boolean = false,
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val linkRepository: LinkRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private val _error = MutableStateFlow(false)
    val error: StateFlow<Boolean> = _error.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()

    private val _eventsFlow = MutableSharedFlow<Event>()
    val eventsFlow: SharedFlow<Event> = _eventsFlow.asSharedFlow()

    fun fetchFolders() {
        linkRepository.getFolders()
            .onStart { _loading.value = true }
            .map { folders ->
                folders.map {
                    FolderModel(
                        folderId = it.id,
                        name = it.name,
                        totalItems = it.size,
                        type = it.type
                    )
                }
            }
            .onEach { folders ->
                _uiState.update { it.copy(folders = folders) }
            }
            .catch { _error.value = true }
            .onCompletion { _loading.value = false }
            .launchIn(viewModelScope)
    }

    fun expandCard(expanded: Boolean) {
        _uiState.update {
            it.copy(expanded = expanded)
        }
    }
}