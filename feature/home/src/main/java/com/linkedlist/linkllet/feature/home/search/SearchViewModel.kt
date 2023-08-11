package com.linkedlist.linkllet.feature.home.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SearchUiState(
    val keyword: String,
)

@HiltViewModel
class SearchViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(SearchUiState(keyword = ""))
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    fun updateKeyword(newKeyword: String) {
        _uiState.update {
            uiState.value.copy(keyword = newKeyword)
        }
    }

    fun search() {
        viewModelScope.launch {

        }
    }
}