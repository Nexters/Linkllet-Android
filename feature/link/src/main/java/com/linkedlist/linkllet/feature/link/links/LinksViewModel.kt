package com.linkedlist.linkllet.feature.link.links

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.linkedlist.linkellet.core.data.repository.LinkRepository
import com.linkedlist.linkllet.feature.link.navigation.FOLDER_ID
import com.linkedlist.linkllet.feature.link.navigation.FOLDER_TITLE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.TimeZone
import javax.inject.Inject

sealed class Event {
    data class ShowLink(val url: String) : Event()
}

data class Link(
    val id : Long,
    val title : String,
    val link : String,
    val date : String
)

data class LinksUiState(
    val folderTitle : String = "",
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

    val folderId = savedStateHandle.get<Long?>(
        key = FOLDER_ID
    )
    private val folderTitle = savedStateHandle.get<String?>(
        key = FOLDER_TITLE
    )

    private val _uiState = MutableStateFlow(LinksUiState(folderTitle = folderTitle ?: ""))
    val uiState: StateFlow<LinksUiState> = _uiState.asStateFlow()

    private val _error = MutableStateFlow<LinksError>(LinksError.READY)
    val error : StateFlow<LinksError> = _error.asStateFlow()

    private val _eventsFlow: MutableSharedFlow<Event> = MutableSharedFlow()
    val eventsFlow = _eventsFlow.asSharedFlow()

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
                                            date = formatDateString(it.createAt)
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

    fun selectLink(url : String) {
        viewModelScope.launch {
            _eventsFlow.emit(Event.ShowLink(url))
        }
    }

    @SuppressLint("SimpleDateFormat")
    fun formatDateString(input: String): String { /// 수정 필요.
        try {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            dateFormat.timeZone = TimeZone.getTimeZone("UTC")
            val date = dateFormat.parse(input)

            // 원하는 출력 형식으로 포맷팅할 SimpleDateFormat을 생성합니다.
            val outputFormat = SimpleDateFormat("yyyy.MM.dd")

            // Date 객체를 원하는 형식으로 포맷팅합니다.
            return outputFormat.format(date)
        }catch (e: Exception){
            return ""
        }
    }
}