package com.linkedlist.linkllet.feature.link

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.linkedlist.linkllet.core.ui.LnkButton
import com.linkedlist.linkllet.core.ui.LnkDropdownTextMenu
import com.linkedlist.linkllet.core.ui.LnkTextFieldWithTitle

@Composable
fun LinkDetailScreen(
    modifier: Modifier = Modifier
) {
    var link by remember { mutableStateOf("") }
    var title by remember { mutableStateOf("") }
    var selectedText by remember { mutableStateOf("기본") }
    var dropdownState by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
    ) {
        LazyColumn(
            contentPadding = PaddingValues(
                top = 30.dp, bottom = 40.dp, start = 16.dp, end = 16.dp
            )
        ) {
            item {
                LnkTextFieldWithTitle(
                    title = "복사한 링크",
                    value = link,
                    onValueChange = {
                        link = it
                    },
                    hint = "링크를 붙여주세요.",
                    isVisibleMaxLengthNotice = false
                )
                Spacer(modifier = Modifier.size(40.dp))

                LnkTextFieldWithTitle(
                    title = "링크 제목",
                    value = title,
                    onValueChange = {
                        title = it
                    },
                    hint = "제목을 입력해 주세요.",
                    maxLength = 10,
                    isVisibleMaxLengthNotice = true
                )

                Spacer(modifier = Modifier.size(42.dp))

                Text(text = "폴더 선택")
                Spacer(modifier.height(16.dp))
                DropdownMenu(expanded = dropdownState, onDismissRequest = { /*TODO*/ }) {
                    
                }
                LnkDropdownTextMenu(
                    selectedText = selectedText,
                    items = listOf("기본","UIUX","개발","안드","서버","디자인","iOS"),
                    isFocused = dropdownState,
                    onMenuClick = {
                        dropdownState = !dropdownState
                    },
                    onItemClick = {
                        selectedText = it
                        dropdownState = false
                    },
                    onDismissRequest = {
                        dropdownState = false
                    }
                )
            }


        }
        LnkButton(
            modifier = Modifier.weight(1f),
            onClick = {

            },
            buttonColor = Color.Black,
            text = "저장하기",
            textColor = Color.White
        )
    }

}