package com.linkedlist.linkllet.core.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.linkedlist.linkllet.core.designsystem.theme.ColorBCBCBC
import com.linkedlist.linkllet.core.designsystem.theme.ColorE0E0E0
import com.linkedlist.linkllet.core.designsystem.theme.ColorF4F4F4
import com.linkedlist.linkllet.core.designsystem.theme.Typography

@Composable
fun LnkTextFieldWithTitle(
    modifier: Modifier = Modifier,
    title : String,
    hint : String = "",
    value : String,
    isError : Boolean = false,
    onValueChange: (String) -> Unit,
    maxLines : Int = 1,
    maxLength : Int = Int.MAX_VALUE,
    isVisibleMaxLengthNotice : Boolean = false
) {
    Column(
        modifier = modifier
    ) {
        Text(
            modifier = Modifier.padding(start = 8.dp),
            text = title,
            style = Typography.titleMedium
        )
        Spacer(modifier.height(16.dp))
        LnkBasicTextFiled(
            modifier = modifier,
            value = value,
            onValueChange = onValueChange,
            isError = isError,
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
                    modifier = Modifier.padding(start = 8.dp),
                    text = "※ 최대 ${maxLength}자까지 입력할 수 있어요.",
                    style = Typography.labelSmall
                )
                Spacer(modifier = modifier
                    .wrapContentHeight()
                    .weight(1f))
                Text(
                    modifier = Modifier.padding(start = 8.dp),
                    text = "${value.length} / ${maxLength}",
                    style = Typography.labelSmall
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
    maxLength: Int = 10,
    focusRequester: FocusRequester = FocusRequester()
    ) {


    var isFocused by remember { mutableStateOf(false) }


    BasicTextField(
        modifier = modifier
            .focusRequester(focusRequester)
            .onFocusChanged {
                isFocused = it.hasFocus
        },
        value = value,
        maxLines = maxLines,
        onValueChange = {
            if(it.length > maxLength) onValueChange(value)
            else onValueChange(it)
        },
        textStyle = Typography.bodyMedium,
        decorationBox = { innerTextField ->
            OutlinedCard(
                border = BorderStroke(
                    width = 2.dp,
                    color =
                        if(isFocused) ColorE0E0E0
                        else if(isError) Color.Red else ColorF4F4F4
                ),
                colors = CardDefaults.cardColors(
                    containerColor = if(isFocused) Color.White else ColorF4F4F4
                )

            ){
                Box(
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 16.dp)
                ){
                    if(value.isEmpty())
                        Text(
                            text = hint,
                            style = Typography.labelMedium,
                            color = ColorBCBCBC
                        )
                    innerTextField()
                }

            }
        }
    )
}

@Composable
fun Modifier.onTabClearFocusing(
    focusManager : FocusManager? = LocalFocusManager.current,
    onClear : () -> Unit = {}
) : Modifier {
    return this.pointerInput(Unit){
        detectTapGestures {
            focusManager?.clearFocus()
            onClear()
        }
    }
}