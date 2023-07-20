package com.linkedlist.linkllet.feature.link.links

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.linkedlist.linkllet.core.designsystem.icon.LnkIcon
import com.linkedlist.linkllet.core.designsystem.icon.lnkicon.Clip
import com.linkedlist.linkllet.core.ui.LinkItem
import com.linkedlist.linkllet.core.ui.LnkFloatingActionButton


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LinksScreen(
    navigateAddLink: () -> Unit,
    viewModel : LinksViewModel = LinksViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        floatingActionButton = {
            LnkFloatingActionButton(
                onClick = { navigateAddLink() }
            ) {
                Icon(
                    imageVector = LnkIcon.Clip,
                    contentDescription = "링크 추가",
                    tint = Color.White
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) { padding ->
        Box(modifier = Modifier.padding(padding)){
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(start = 16.dp, end = 20.dp,top = 20.dp)
            ) {
                items(uiState.links){
                    LinkItem(title = it.title, link = it.link, date = it.date)
                    Spacer(modifier = Modifier.size(8.dp))
                }
            }
        }

    }
}