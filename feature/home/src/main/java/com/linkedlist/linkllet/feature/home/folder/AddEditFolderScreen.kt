package com.linkedlist.linkllet.feature.home.folder

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.linkedlist.linkllet.core.designsystem.icon.LnkIcon
import com.linkedlist.linkllet.core.designsystem.icon.lnkicon.X
import com.linkedlist.linkllet.core.ui.LnkAppBar
import com.linkedlist.linkllet.core.ui.LnkButtonWithMargin
import com.linkedlist.linkllet.core.ui.LnkDialog
import com.linkedlist.linkllet.core.ui.LnkIconButton
import com.linkedlist.linkllet.core.ui.LnkTextFieldWithTitle
import com.linkedlist.linkllet.core.ui.onTabClearFocusing
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditFolderScreen(
    onBack: () -> Unit,
    viewModel: AddEditFolderViewModel = hiltViewModel(),
    onShowSnackbar: suspend (String) -> Boolean,
) {
    val uiState by viewModel.uiState.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    BackHandler(enabled = true) {
        coroutineScope.launch {
            viewModel.navigateUp()
        }
    }

    LnkDialog(
        text = "작성한 내용이 삭제됩니다.\n작성을 취소할 건가요?",
        visible = uiState.cancelDialogVisibility,
        onDismissRequest = viewModel::hideCancelDialog,
        onOk = { onBack() }
    )

    LaunchedEffect(key1 = Unit) {
        viewModel.eventsFlow.collect {
            when (it) {
                is Event.CloseScreen -> onBack()
                is Event.ShowToast -> {
                    onShowSnackbar(it.message)
                }
                else -> {}
            }
        }
    }

    Scaffold(
        containerColor = Color.White,
        modifier = Modifier.onTabClearFocusing(),
        topBar = {
            LnkAppBar(
                title = { AppBarTitle() },
                action = { Close(onBack = viewModel::navigateUp) },
                modifier = Modifier.shadow(elevation = 4.dp), // fixme : 임시 그림자
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier.background(color = Color.White)
        ) {
            Box(
                Modifier
                    .padding(padding)
                    .padding(top = 30.dp)
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
            ) {
                LnkTextFieldWithTitle(
                    modifier = Modifier.fillMaxWidth(),
                    title = "폴더 제목", value = uiState.folderName,
                    onValueChange = viewModel::updateFolderName,
                    isVisibleMaxLengthNotice = true,
                    maxLength = 10,
                    isError = uiState.error,
                    hint = "제목을 입력해 주세요."
                )
            }
            Spacer(modifier = Modifier.weight(1.0f))
            LnkButtonWithMargin(
                onClick = viewModel::addFolder,
                text = "저장하기",
                isEmphasized = uiState.folderName.isNotEmpty(),
            )
        }
    }
}

@Composable
fun AppBarTitle() {
    Text("폴더 추가하기", fontWeight = FontWeight.Bold, fontSize = 16.sp)
}

@Composable
fun Close(onBack: () -> Unit) {
    LnkIconButton(onClick = onBack) {
        Icon(imageVector = LnkIcon.X, contentDescription = "닫기")
    }
}

@Composable
@Preview
fun AddEditFolderScreenPreview() {
    AddEditFolderScreen(onBack = {}, onShowSnackbar = { _: String -> true })
}