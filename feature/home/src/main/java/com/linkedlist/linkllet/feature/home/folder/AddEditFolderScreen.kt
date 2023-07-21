package com.linkedlist.linkllet.feature.home.folder

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
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
) {
    Scaffold(
        topBar = {
            LnkAppBar(
                title = { AppBarTitle() },
                action = { Close(onBack = onBack) }
            )
        }
    ) { padding ->
        Column {
            Box(
                Modifier
                    .padding(padding)
                    .fillMaxWidth(),
            ) {
                LnkTextFieldWithTitle(
                    title = "폴더 제목", value = "",
                    onValueChange = {}
                )
            }
            Spacer(modifier = Modifier.weight(1.0f))
            LnkButton(
                onClick = { },
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