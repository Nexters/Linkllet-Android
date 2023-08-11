package com.linkedlist.linkllet.feature.home.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.linkedlist.linkllet.core.ui.LnkAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    onBack: () -> Unit,
) {
    Scaffold(
        topBar = { LnkAppBar(
            title = { Text("검색") }
        ) }
    ) { padding ->
        Box(modifier = Modifier.padding(padding))
    }
}