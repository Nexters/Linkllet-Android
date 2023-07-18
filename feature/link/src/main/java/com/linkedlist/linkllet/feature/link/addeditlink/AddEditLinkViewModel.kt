package com.linkedlist.linkllet.feature.link.addeditlink

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class Folder(
    val name: String = "",
    val isSelected: Boolean = false
)

data class AddEditLinkUiState(
    val link: String = "",
    val title: String = "",
    val folders: List<Folder> = foldersDummy,
    val isLoading: Boolean = false,
    val isLinkSaved: Boolean = false
)

val foldersDummy = listOf(
    Folder("기본", true),
    Folder("UIUX", false),
    Folder("개발", false),
    Folder("안드", false),
    Folder("서버", false),
    Folder("디자인", false),
    Folder("iOS", false)
)

class AddEditLinkViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(AddEditLinkUiState())
    val uiState: StateFlow<AddEditLinkUiState> = _uiState.asStateFlow()

    fun updateLink(link: String) {
        _uiState.update {
            it.copy(link = link)
        }
    }

    fun updateTitle(title: String) {
        _uiState.update {
            it.copy(title = title)
        }
    }

    fun updateFolder(title: String) {
        _uiState.update {
            it.copy(folders = it.folders.map {
                if (it.name == title) it.copy(isSelected = true)
                else it.copy(isSelected = false)
            })
        }
    }
}