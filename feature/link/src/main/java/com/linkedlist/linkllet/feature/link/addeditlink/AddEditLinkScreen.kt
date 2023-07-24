package com.linkedlist.linkllet.feature.link.addeditlink

import androidx.activity.compose.BackHandler
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
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.linkedlist.linkllet.core.designsystem.theme.Typography
import com.linkedlist.linkllet.core.ui.LnkAppBar
import com.linkedlist.linkllet.core.ui.LnkButtonWithMargin
import com.linkedlist.linkllet.core.ui.LnkDialog
import com.linkedlist.linkllet.core.ui.LnkDropdownTextMenu
import com.linkedlist.linkllet.core.ui.LnkTextFieldWithTitle
import com.linkedlist.linkllet.core.ui.onTabClearFocusing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditLinkScreen(
    modifier: Modifier = Modifier,
    viewModel : AddEditLinkViewModel = hiltViewModel(),
    onBack : () -> Unit,
    onShowSnackbar: suspend (String) -> Boolean,
) {
    val uiState by viewModel.uiState.collectAsState()
    val error by viewModel.error.collectAsState()
    val snackbarState by viewModel.snackbarState.collectAsState()
    var dropdownState by remember { mutableStateOf(false) }

    var dialogState by remember { mutableStateOf(false) }

    val focusManager = LocalFocusManager.current

    BackHandler() {
        dialogState = true
    }

    LnkDialog(
        text = "작성한 내용이 취소됩니다. \n작성을 취소할건가요?",
        visible = dialogState,
        onDismissRequest = {
            dialogState = !dialogState
        },
        onOk = {
            onBack()
        }
    )


    LaunchedEffect(uiState.isLinkSaved){
        if(uiState.isLinkSaved) onBack()
    }

    LaunchedEffect(key1 = error, block = {
        when(error){
            AddEditLinkError.NETWORK_ERROR -> {
                viewModel.updateSnackbarState("링크를 저장할 수 없어요.")
                viewModel.updateIsButtonEmphasized(false)
            }
            else -> {

            }
        }
    })

    LaunchedEffect(snackbarState){
        if(snackbarState.isNotBlank()){
            onShowSnackbar(snackbarState)
            viewModel.updateSnackbarState("")
        }
    }
    Scaffold(
        modifier = Modifier.onTabClearFocusing(
            onClear = {
                dropdownState = false
            }
        ),
        topBar = {
            LnkAppBar(
                title = {
                    Text(text = "링크 저장하기", style = Typography.titleLarge)
                },
                backButton = {

                },
                action = {
                    Icon(
                        modifier = Modifier.clickable {
                            dialogState = !dialogState
                        },
                        imageVector = Icons.Rounded.Close, contentDescription = "settings")
                },
                modifier = Modifier.shadow(elevation = 4.dp), // fixme : 임시 그림자

            )
        }
    ) { it ->
        Column(
            modifier = modifier
                .padding(it)

        ) {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .background(Color.White),
                contentPadding = PaddingValues(
                    top = 30.dp, bottom = 40.dp, start = 16.dp, end = 16.dp
                ),
                state = rememberLazyListState()
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
                        isVisibleMaxLengthNotice = false,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Next
                        )
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
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Done
                        ),
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
                    focusManager.clearFocus()
                    viewModel.addLink()
                },
                buttonColor = Color.Black,
                text = "저장하기",
                textColor = Color.White,
                isEmphasized = uiState.isButtonEmphasized
            )
        }
    }



}