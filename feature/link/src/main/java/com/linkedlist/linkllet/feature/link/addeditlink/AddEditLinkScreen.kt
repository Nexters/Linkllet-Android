package com.linkedlist.linkllet.feature.link.addeditlink

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.linkedlist.linkllet.core.designsystem.theme.Typography
import com.linkedlist.linkllet.core.ui.LnkAppBar
import com.linkedlist.linkllet.core.ui.LnkButtonWithMargin
import com.linkedlist.linkllet.core.ui.LnkDropdownTextMenu
import com.linkedlist.linkllet.core.ui.LnkTextFieldWithTitle
import com.linkedlist.linkllet.core.ui.onTabClearFocusing

@Composable
fun AddEditLinkScreen(
    modifier: Modifier = Modifier,
    viewModel : AddEditLinkViewModel = hiltViewModel(),
    onBack : () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val error by viewModel.error.collectAsState()
    var dropdownState by remember { mutableStateOf(false) }

    LaunchedEffect(uiState.isLinkSaved){
        if(uiState.isLinkSaved) onBack()
    }

    LaunchedEffect(error){
        if(error == AddEditLinkError.NETWORK_ERROR){
            //snackbar
            viewModel.updateError(AddEditLinkError.READY)
        }
    }


    Column(
        modifier = modifier
            .onTabClearFocusing(
                onClear = {
                    dropdownState = false
                }
            )
    ) {
        LnkAppBar(
            title = {
                Text(text = "링크 저장하기", style = Typography.titleLarge)
            },
            backButton = {

            },
            action = {
                Icon(
                    modifier = Modifier.clickable {
                        onBack()
                    },
                    imageVector = Icons.Rounded.Close, contentDescription = "settings")
            }

        )

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .background(Color.White),
            contentPadding = PaddingValues(
                top = 30.dp, bottom = 40.dp, start = 16.dp, end = 16.dp
            )
        ) {
            item {
                LnkTextFieldWithTitle(
                    modifier = Modifier.fillMaxWidth(),
                    title = "복사한 링크",
                    value = uiState.link,
                    onValueChange = {
                        viewModel.updateLink(it)
                    },
                    isError = error == AddEditLinkError.LINK_BLANK || error == AddEditLinkError.TITLE_LINK_BLANK,
                    hint = "링크를 붙여주세요.",
                    isVisibleMaxLengthNotice = false
                )
                Spacer(modifier = Modifier.size(40.dp))

                LnkTextFieldWithTitle(
                    modifier = Modifier.fillMaxWidth(),
                    title = "링크 제목",
                    value = uiState.title,
                    onValueChange = {
                        viewModel.updateTitle(it)
                    },
                    isError = error == AddEditLinkError.TITLE_BLANK || error == AddEditLinkError.TITLE_LINK_BLANK,
                    hint = "제목을 입력해 주세요.",
                    maxLength = 10,
                    isVisibleMaxLengthNotice = true
                )

                Spacer(modifier = Modifier.size(42.dp))

                Text(
                    modifier = Modifier.padding(start = 8.dp),
                    text = "폴더 선택",
                    style = Typography.titleMedium
                )
                Spacer(modifier.height(16.dp))
                Box(
                    modifier = Modifier.fillMaxSize()
                ){
                    Text(
                        modifier = Modifier.padding(start = 8.dp,top = 59.dp),
                        text = "※ 폴더 미선택시 기본 폴더에 저장됩니다.",
                        style = Typography.labelSmall
                    )
                    LnkDropdownTextMenu(
                        selectedText = uiState.folders.firstOrNull { it.isSelected }?.name ?: "",
                        items = uiState.folders.map { it.name },
                        isFocused = dropdownState,
                        onMenuClick = {
                            dropdownState = !dropdownState
                        },
                        onItemClick = {
                            viewModel.updateFolder(it)
                            dropdownState = false
                        }
                    )
                }

            }

        }
        LnkButtonWithMargin(
            modifier = Modifier.background(Color.White),
            onClick = {
                viewModel.addLink()
            },
            buttonColor = Color.Black,
            text = "저장하기",
            textColor = Color.White
        )
    }

}