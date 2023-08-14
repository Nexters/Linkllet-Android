package com.linkedlist.linkllet.feature.home.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.linkedlist.linkllet.core.data.model.Folder
import com.linkedlist.linkllet.core.data.model.Link
import com.linkedlist.linkllet.core.data.repository.LinkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SearchUiState(
    val keyword: String,
    val folders: List<Folder> = emptyList(),
    val links: List<Link> = emptyList(),
)

sealed class SearchState {
    object Loading : SearchState()
    object BeforeToSearch : SearchState()
    object Empty : SearchState()
    object Success : SearchState()
    object Error : SearchState()
}

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val linkRepository: LinkRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(SearchUiState(keyword = ""))
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    private val _state: MutableStateFlow<SearchState> = MutableStateFlow(SearchState.BeforeToSearch)
    val state: StateFlow<SearchState> = _state.asStateFlow()

    fun updateKeyword(newKeyword: String) {
        _uiState.update {
            uiState.value.copy(keyword = newKeyword)
        }
    }

    fun search() {
        val query = uiState.value.keyword
        if(query.length < 2) return

        viewModelScope.launch {
            linkRepository.search(query)
                .onStart {
                    _state.value = SearchState.Loading
                }
                .collect { result ->
                    result.onSuccess { links ->
                        _uiState.update {
                            it.copy(links = links)
                        }

                        _state.value =
                            if(links.isNotEmpty()) SearchState.Success
                            else SearchState.Empty
                    }
                }
        }
    }
}