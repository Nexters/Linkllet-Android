package com.linkedlist.linkllet.core.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun LnkTextFieldWithTitle(
    modifier: Modifier = Modifier,
    title : String,
    hint : String = "",
    value : String,
    onValueChange: (String) -> Unit,
    maxLines : Int = 1,
    maxLength : Int = Int.MAX_VALUE,
    isVisibleMaxLengthNotice : Boolean = false
) {
    Column(
        modifier = modifier
    ) {
        Text(text = title)
        Spacer(modifier.height(16.dp))
        LnkBasicTextFiled(
            modifier = modifier,
            value = value,
            onValueChange = onValueChange,
            isError = true,
            hint = hint,
            maxLength = maxLength,
            maxLines = maxLines
        )
        Spacer(modifier = Modifier.height(10.dp))
        if(isVisibleMaxLengthNotice){
            Row(
                modifier = Modifier.fillMaxWidth()
            ){
                Text(
                    text = "최대 ${maxLength}자까지 입력할 수 있어요."
                )
                Spacer(modifier = modifier.wrapContentHeight().weight(1f))
                Text(
                    text = "${value.length} / ${maxLength}"
                )
            }
        }
    }
}

@Composable
fun LnkBasicTextFiled(
    modifier: Modifier = Modifier,
    value : String,
    onValueChange : (String) -> Unit,
    hint : String = "",
    isError : Boolean = false,
    maxLines: Int = 1,
    maxLength: Int = 10
) {
    BasicTextField(
        modifier = modifier,
        value = value,
        maxLines = maxLines,
        onValueChange = {
            if(it.length > maxLength) onValueChange(value)
            else onValueChange(it)
        },
        decorationBox = { innerTextField ->
            OutlinedCard(
                border = BorderStroke(
                    width = if(isError) 1.dp else 0.dp,
                    color = if(isError) Color.Red else Color.Transparent
                )
            ){
                Box(
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 16.dp)
                ){
                    if(value.isEmpty())
                        Text(
                            text = hint,
                            color = Color.LightGray
                        )
                    innerTextField()
                }

            }
        }
    )
}