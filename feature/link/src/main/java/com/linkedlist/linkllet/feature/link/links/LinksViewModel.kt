package com.linkedlist.linkllet.feature.link.links

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.linkedlist.linkellet.core.data.repository.LinkRepository
import com.linkedlist.linkllet.feature.link.addeditlink.AddEditLinkError
import com.linkedlist.linkllet.feature.link.navigation.FOLDER_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject


data class Link(
    val id : Long,
    val title : String,
    val link : String,
    val date : String
)

data class LinksUiState(
    val links: List<Link> = emptyList(),
    val isLoading: Boolean = false,
    val isFolderDeleted : Boolean = false,
    val isLinkDeleted : Boolean = false
)

enum class LinksError{
    READY, NETWORK_ERROR
}

@HiltViewModel
class LinksViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val linkRepository: LinkRepository
) : ViewModel() {

    private val folderId = savedStateHandle.get<Long?>(
        key = FOLDER_ID
    )

    private val _uiState = MutableStateFlow(LinksUiState())
    val uiState: StateFlow<LinksUiState> = _uiState.asStateFlow()

    private val _error = MutableStateFlow<LinksError>(LinksError.READY)
    val error : StateFlow<LinksError> = _error.asStateFlow()

    fun fetchLinks() {
        viewModelScope.launch {
            try {
                folderId?.let {
                    linkRepository.getLinks(
                        id = it
                    ).catch {
                        _error.emit(LinksError.NETWORK_ERROR)
                    }.collect {
                        it.onSuccess {
                            _uiState.emit(
                                uiState.value.copy(
                                    links = it.map {
                                        Link(
                                            id = it.id,
                                            title = it.name,
                                            link = it.url,
                                            date = "2023.07.20"
                                        )
                                    }
                                )
                            )
                        }.onFailure {
                            _error.emit(LinksError.NETWORK_ERROR)
                        }
                    }
                }

            }catch (e : Exception){
                _error.emit(LinksError.NETWORK_ERROR)
            }
        }
    }

    fun deleteFolder() {
        viewModelScope.launch {
            try {
                folderId?.let {
                    linkRepository.deleteFolder(
                        id = it
                    ).catch {
                        _error.emit(LinksError.NETWORK_ERROR)
                    }.collect {
                        it.onSuccess {
                            _uiState.emit(
                                uiState.value.copy(
                                    isFolderDeleted = true
                                )
                            )
                        }.onFailure {
                            _error.emit(LinksError.NETWORK_ERROR)
                        }
                    }
                }

            }catch (e : Exception){
                _error.emit(LinksError.NETWORK_ERROR)
            }
        }
    }

    fun deleteLink(linkId : Long) {
        viewModelScope.launch {
            try {
                folderId?.let {
                    linkRepository.deleteLink(
                        id = it,
                        articleId = linkId
                    ).catch {
                        _error.emit(LinksError.NETWORK_ERROR)
                    }.collect {
                        it.onSuccess {
                            _uiState.emit(
                                uiState.value.copy(
                                    isLinkDeleted = true
                                )
                            )
                        }.onFailure {
                            _error.emit(LinksError.NETWORK_ERROR)
                        }
                    }
                }

            }catch (e : Exception){
                _error.emit(LinksError.NETWORK_ERROR)
            }
        }
    }
}