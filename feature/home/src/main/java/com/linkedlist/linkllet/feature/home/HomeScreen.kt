package com.linkedlist.linkllet.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.linkedlist.linkllet.core.designsystem.icon.LnkIcon
import com.linkedlist.linkllet.core.designsystem.icon.lnkicon.AddFolder
import com.linkedlist.linkllet.core.designsystem.icon.lnkicon.Clip
import com.linkedlist.linkllet.core.designsystem.icon.lnkicon.HomeBackground
import com.linkedlist.linkllet.core.designsystem.icon.lnkicon.Linkllet
import com.linkedlist.linkllet.core.designsystem.icon.lnkicon.Search
import com.linkedlist.linkllet.core.designsystem.icon.lnkicon.Settings
import com.linkedlist.linkllet.core.ui.LnkAppBar
import com.linkedlist.linkllet.core.ui.LnkCardToggle
import com.linkedlist.linkllet.core.ui.LnkExpandedCard
import com.linkedlist.linkllet.core.ui.LnkFloatingActionButton
import com.linkedlist.linkllet.core.ui.LnkRotatingCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToAddLink: () -> Unit,
    navigateToAddEditFolder: () -> Unit,
    navigateToLinks: (Long, String, String) -> Unit,
    navigateToSettings: () -> Unit,
    navigateToSearch: () -> Unit,
    onShowSnackbar: suspend (String) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchFolders()

        viewModel.eventsFlow.collect {
            when (it) {
                is Event.Error -> onShowSnackbar(it.message)
            }
        }
    }

    Scaffold(
        containerColor = Color.White,
        topBar = {
            LnkAppBar(
                leadingButton = { LnkCardToggle(onChanged = viewModel::expandCard) },
                action = {
                    HomeActions(
                        navigateToSearch,
                        navigateToAddEditFolder,
                        navigateToSettings
                    )
                },
            )
        },
        floatingActionButton = {
            LnkFloatingActionButton(
                onClick = { navigateToAddLink() }
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
        Box(
            modifier = Modifier.padding(padding)
        ) {
            Icon(
                // fixme : 이미지 크기 가로 길이에 맞춰 세로 길이가 결정되어야 함.
                modifier = Modifier
                    .fillMaxWidth(1.0f)
                    .aspectRatio(1.213f)
                    .padding(top = 25.dp, start = 18.dp, end = 18.dp),
                imageVector = LnkIcon.HomeBackground, contentDescription = "배경",
                tint = if(uiState.expanded) Color.Black.copy(alpha = 0.1f) else Color.Black
            )

            Box(
                modifier = Modifier.fillMaxHeight(),
                contentAlignment = Alignment.BottomCenter,
            ) {
                if(uiState.expanded) {
                    LnkExpandedCard(
                        modifier = Modifier.padding(horizontal = 5.dp, vertical = 30.dp),
                        folders = uiState.folders,
                        navigateToLinks = navigateToLinks,
                    )
                } else {
                    LnkRotatingCard(
                        modifier = Modifier.padding(horizontal = 5.dp, vertical = 30.dp)
                            .height((200 + 130).dp),
                        folders = uiState.folders,
                        navigateToLinks = navigateToLinks,
                    )
                }
            }
        }
    }
}

@Composable
fun HomeActions(
    navigateToSearch: () -> Unit,
    navigateToAddEditFolder: () -> Unit,
    navigateToSettings: () -> Unit,
) {
    Row {
        Icon(
            modifier = Modifier.clickable {
                navigateToSettings()
            },
            imageVector = LnkIcon.Settings,
            contentDescription = "설정",
        )
        Icon(
            modifier = Modifier.clickable {
                navigateToSearch()
            },
            imageVector = LnkIcon.Search,
            contentDescription = "검색",
        )
        Icon(
            modifier = Modifier.clickable {
                navigateToAddEditFolder()
            },
            imageVector = LnkIcon.AddFolder,
            contentDescription = "폴더 추가",
        )
    }
}

@Composable
@Preview
fun HomeScreenPreview() {
    HomeScreen(
        navigateToAddLink = {},
        navigateToAddEditFolder = {},
        navigateToLinks = { _, _, _ -> },
        navigateToSettings = {},
        navigateToSearch = {},
        onShowSnackbar = { _ -> true },
    )
}