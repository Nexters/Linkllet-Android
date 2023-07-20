package com.linkedlist.linkllet.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.linkedlist.linkellet.core.data.repository.LinkRepository
import com.linkedlist.linkllet.core.ui.FolderModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeUiState(
    val folders: List<FolderModel> = emptyList(),
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val linkRepository: LinkRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        fetchFolders()
    }

    private fun fetchFolders() {
        viewModelScope.launch {
            linkRepository.getFolders().collectLatest { resultFolders ->

                // todo : onClick과 totalItems 넣어줘야 한다.
                val newFolders =
                    resultFolders.getOrNull()?.map { FolderModel(name = it.name, onClick = {}, totalItems = 10) } ?: emptyList()

                _uiState.update {
                    it.copy(folders = newFolders)
                }
            }
        }
    }
}