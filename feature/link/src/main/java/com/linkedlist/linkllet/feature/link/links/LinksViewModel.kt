package com.linkedlist.linkllet.feature.link.links

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.linkedlist.linkllet.core.data.repository.LinkRepository
import com.linkedlist.linkllet.feature.link.model.FolderType
import com.linkedlist.linkllet.feature.link.model.LinkUiModel
import com.linkedlist.linkllet.feature.link.model.toFolderType
import com.linkedlist.linkllet.feature.link.model.toUiModel
import com.linkedlist.linkllet.feature.link.navigation.FOLDER_ID
import com.linkedlist.linkllet.feature.link.navigation.FOLDER_TITLE
import com.linkedlist.linkllet.feature.link.navigation.FOLDER_TYPE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class Event {
    data class ShowLink(val url: String) : Event()
    data class Error(val message: String) : Event()

    object FolderDeleted : Event()
    object LinkDeleted : Event()
}

data class LinksUiState(
    val folderTitle: String = "",
    val folderType: FolderType,
    val links: List<LinkUiModel> = emptyList(),
    val isLoading: Boolean = false
)


@HiltViewModel
class LinksViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val linkRepository: LinkRepository
) : ViewModel() {

    val folderId = savedStateHandle.get<Long?>(
        key = FOLDER_ID
    )
    private val folderTitle = savedStateHandle.get<String?>(
        key = FOLDER_TITLE
    )
    private val folderType = savedStateHandle.get<String?>(
        key = FOLDER_TYPE
    )

    private val _uiState = MutableStateFlow(
        LinksUiState(
            folderTitle = folderTitle ?: "",
            folderType = folderType.toFolderType()
        )
    )
    val uiState: StateFlow<LinksUiState> = _uiState.asStateFlow()

    private val _eventsFlow: MutableSharedFlow<Event> = MutableSharedFlow()
    val eventsFlow = _eventsFlow.asSharedFlow()

    fun fetchLinks() {
        if(folderId == null) return

        linkRepository.getLinks(folderId)
            .onEach { links ->
                _uiState.update {
                    uiState.value.copy(
                        links = links.map { link ->
                            link.toUiModel()
                        }
                    )
                }
            }
            .catch {
                _eventsFlow.emit(Event.Error(it.message ?: "에러가 발생했어요."))
            }
            .launchIn(viewModelScope)
    }

    fun deleteFolder() {
        if (folderId == null) return

        linkRepository.deleteFolder(
            id = folderId
        ).onEach {
            _eventsFlow.emit(Event.FolderDeleted)
        }.catch {
            _eventsFlow.emit(Event.Error(it.message ?: "에러가 발생했어요."))
        }.launchIn(viewModelScope)
    }

    fun deleteLink(linkId: Long) {
        if (folderId == null) return

        linkRepository.deleteLink(
            id = folderId,
            articleId = linkId
        ).onEach {
            fetchLinks()
            _eventsFlow.emit(Event.LinkDeleted)
        }.catch {
            _eventsFlow.emit(Event.Error(it.message ?: "에러가 발생했어요."))
        }.launchIn(viewModelScope)
    }

    fun selectLink(url: String) {
        viewModelScope.launch {
            _eventsFlow.emit(Event.ShowLink(url))
        }
    }
}