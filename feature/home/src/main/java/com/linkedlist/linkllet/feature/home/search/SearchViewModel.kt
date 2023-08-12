package com.linkedlist.linkllet.feature.home.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.linkedlist.linkllet.core.data.model.Folder
import com.linkedlist.linkllet.core.data.model.Link
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SearchUiState(
    val keyword: String,
    val folders: List<Folder> = emptyList(),
    val links: List<Link> = emptyList(),
)

@HiltViewModel
class SearchViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(SearchUiState(keyword = ""))
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()

    private val _beforeSearch = MutableStateFlow(true)
    val beforeSearch: StateFlow<Boolean> = _beforeSearch.asStateFlow()

    val empty: Flow<Boolean> = beforeSearch.map {
        !beforeSearch.value && uiState.value.folders.isEmpty() && uiState.value.links.isEmpty()
    }

    fun updateKeyword(newKeyword: String) {
        _uiState.update {
            uiState.value.copy(keyword = newKeyword)
        }
    }

    fun search() {
        viewModelScope.launch {
            _beforeSearch.value = false
        }
    }
}