package com.linkedlist.linkllet.feature.home.folder

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.linkedlist.linkllet.core.designsystem.icon.LnkIcon
import com.linkedlist.linkllet.core.designsystem.icon.lnkicon.X
import com.linkedlist.linkllet.core.ui.LnkAppBar
import com.linkedlist.linkllet.core.ui.LnkButton
import com.linkedlist.linkllet.core.ui.LnkIconButton
import com.linkedlist.linkllet.core.ui.LnkTextFieldWithTitle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditFolderScreen(
    onBack: () -> Unit,
    viewModel: AddEditFolderViewModel = hiltViewModel(),
) {
    val folderName by viewModel.folderName.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.eventFlow.collect {
            when (it) {
                is Event.CloseScreen -> onBack()
                else -> {}
            }
        }
    }

    Scaffold(
        topBar = {
            LnkAppBar(
                title = { AppBarTitle() },
                action = { Close(onBack = onBack) },
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
                    title = "폴더 제목", value = folderName,
                    onValueChange = viewModel::updateFolderName,
                    isVisibleMaxLengthNotice = true,
                    maxLength = 10,
                )
            }
            Spacer(modifier = Modifier.weight(1.0f))
            LnkButton(
                modifier = Modifier
                    .padding(horizontal = 24.dp, vertical = 14.dp)
                    .height(50.dp),
                onClick = viewModel::addFolder,
                buttonColor = Color.Black,
                text = "저장하기",
                textColor = Color.White,
            )
        }
    }
}

@Composable
fun AppBarTitle() {
    Text("폴더 추가하기")
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
    AddEditFolderScreen(onBack = {})
}