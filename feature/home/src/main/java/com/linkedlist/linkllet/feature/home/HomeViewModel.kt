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
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.net.ConnectException
import javax.inject.Inject

sealed class Event {
    data class Error(val message: String) : Event()
}

data class HomeUiState(
    val folders: List<FolderModel> = emptyList(),
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

    init {
        signupAndFetchFolders()
    }

    private fun signupAndFetchFolders() {
        viewModelScope.launch {
            authRepository.signUp().collect { result ->
                result.onSuccess {
                    // fixme : 일단은 콜백으로 작성했으나 더 좋은 구조로 작성할 수 있는지 고민하기
                    fetchFolders()
                }.onFailure {
                    if (it is ConnectException) {
                        _eventsFlow.emit(Event.Error("네트워크 연결을 확인해 주세요"))
                    }
                }
            }
        }
    }

    fun fetchFolders() {
        viewModelScope.launch {
            _loading.value = true
            linkRepository.getFolders().collect { result ->
                result.mapCatching { folders ->
                    folders.map {
                        FolderModel(
                            folderId = it.id,
                            name = it.name,
                            totalItems = it.size,
                            type = it.type
                        )
                    }
                }.onSuccess { folders ->
                    _uiState.update { it.copy(folders = folders) }
                    _loading.value = false
                }.onFailure {
                    _loading.value = false
                    _error.value = false
                }
            }
        }
    }
}