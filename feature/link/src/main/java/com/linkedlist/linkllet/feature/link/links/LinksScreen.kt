package com.linkedlist.linkllet.feature.link.links

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.linkedlist.linkllet.core.designsystem.icon.LnkIcon
import com.linkedlist.linkllet.core.designsystem.icon.lnkicon.Clip
import com.linkedlist.linkllet.core.designsystem.theme.Color878787
import com.linkedlist.linkllet.core.designsystem.theme.Typography
import com.linkedlist.linkllet.core.ui.LinkItem
import com.linkedlist.linkllet.core.ui.LnkAppBar
import com.linkedlist.linkllet.core.ui.LnkDialog
import com.linkedlist.linkllet.core.ui.LnkFloatingActionButton


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LinksScreen(
    navigateAddLink: () -> Unit,
    onBack: () -> Unit,
    onShowSnackbar: suspend (String) -> Boolean,
    viewModel: LinksViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    var dropdownState by remember { mutableStateOf(false) }

    var dialogFolderState by remember { mutableStateOf(false) }
    var dialogLinkState by remember { mutableStateOf<Long?>(null) }

    LaunchedEffect(true) {
        viewModel.fetchLinks()
    }

    LaunchedEffect(key1 = uiState.isFolderDeleted) {
        if(uiState.isFolderDeleted)
            onBack()
    }

    LaunchedEffect(key1 = uiState.isLinkDeleted) {
        if(uiState.isLinkDeleted){
            viewModel.fetchLinks()
            onShowSnackbar("링크를 삭제했어요.")
        }

    }

    LnkDialog(
        text = "폴더를 삭제할건가요?",
        visible = dialogFolderState ,
        onDismissRequest = {
            dialogFolderState = !dialogFolderState
        },
        onOk = {
            viewModel.deleteFolder()
        }
    )

    LnkDialog(
        text = "링크를 삭제할건가요?",
        visible = dialogLinkState != null,
        onDismissRequest = {
            dialogLinkState = null
        },
        onOk = {
            dialogLinkState?.let {
                viewModel.deleteLink(it)
            }

        }
    )

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
        floatingActionButtonPosition = FabPosition.End,
        topBar = {
            LnkAppBar(
                title = {
                    Text(
                        text = uiState.folderTitle,
                        style = Typography.titleLarge,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                    )
                },
                backButton = {
                    Icon(
                        modifier = Modifier.clickable {
                            onBack()
                        },
                        imageVector = Icons.Rounded.ArrowBack, contentDescription = "back"
                    )
                },
                action = {
                    Box(){
                        Text(
                            modifier = Modifier
                                .clickable {
                                    dropdownState = !dropdownState
                                },
                            text = "편집",
                            style = Typography.titleMedium
                        )
                        DropdownMenu(
                            modifier = Modifier.background(Color.White),
                            expanded = dropdownState,
                            onDismissRequest = { dropdownState = !dropdownState }) {
                            DropdownMenuItem(text = {
                                Text(text = "폴더 삭제하기")
                            }, onClick = {
                                dialogFolderState = true
                                dropdownState = !dropdownState
                            })
                        }
                    }


                },
                modifier = Modifier.shadow(elevation = 4.dp), // fixme : 임시 그림자
            )
        }
    ) { padding ->
        Box(modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
            .padding(padding)) {
            if(uiState.links.isNotEmpty()){
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(start = 16.dp, end = 20.dp, top = 20.dp),
                    state = rememberLazyListState()
                ) {
                    items(uiState.links) {
                        LinkItem(
                            title = it.title,
                            link = it.link,
                            date = it.date,
                            onDelete = {
                                dialogLinkState = it.id
                            })
                        Spacer(modifier = Modifier.size(8.dp))
                    }
                }
            }else {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = "링크를 저장해 주세요.",
                    style = Typography.labelMedium,
                    color = Color878787
                )
            }

        }

    }
}