package com.linkedlist.linkllet.feature.link.links

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


data class Link(
    val title : String,
    val link : String,
    val date : String
)

data class LinksUiState(
    val links: List<Link> = linksDummy,
    val isLoading: Boolean = false
)

val linksDummy = listOf(
    Link("자소서 참고1","https://brunch.co.kr/@plusx/98brunch.co.kr/@plusx/98","2023.7.8"),
    Link("자소서 참고2","https://brunch.co.kr/@plusx/98brunch.co.kr/@plusx/99","2023.7.9"),
    Link("자소서 참고3","https://brunch.co.kr/@plusx/98brunch.co.kr/@plusx/100","2023.7.10"),
    Link("자소서 참고4","https://brunch.co.kr/@plusx/98brunch.co.kr/@plusx/101","2023.7.11"),
    Link("자소서 참고1","https://brunch.co.kr/@plusx/98brunch.co.kr/@plusx/98","2023.7.8"),
    Link("자소서 참고2","https://brunch.co.kr/@plusx/98brunch.co.kr/@plusx/99","2023.7.9"),
    Link("자소서 참고3","https://brunch.co.kr/@plusx/98brunch.co.kr/@plusx/100","2023.7.10"),
    Link("자소서 참고4","https://brunch.co.kr/@plusx/98brunch.co.kr/@plusx/101","2023.7.11")
)


class LinksViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(LinksUiState())
    val uiState: StateFlow<LinksUiState> = _uiState.asStateFlow()

}