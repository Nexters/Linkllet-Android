package com.linkedlist.linkllet.core.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntRect
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupPositionProvider
import com.linkedlist.linkllet.core.designsystem.icon.LnkIcon
import com.linkedlist.linkllet.core.designsystem.icon.lnkicon.BtnRadio
import com.linkedlist.linkllet.core.designsystem.icon.lnkicon.IcDropdownArrow

@Composable
fun LnkDropdownTextMenu(
    modifier: Modifier = Modifier,
    selectedText: String,
    items: List<String> = emptyList(),
    isFocused: Boolean = false,
    onMenuClick: () -> Unit = {},
    onItemClick: (String) -> Unit = {}
) {
    OutlinedCard(
        modifier = modifier.wrapContentHeight(),
        shape = RoundedCornerShape(12.dp)
    ) {
        LnkDropdownTextItem(
            itemText = selectedText,
            isHeader = true,
            isFocused = isFocused,
            onSelect = {
                onMenuClick()
            }
        )

        AnimatedVisibility(visible = isFocused && items.isNotEmpty()) {
            Divider()
            LazyColumn(
                modifier = Modifier.height(
                    (49 * (if (items.size > 4) 4 else items.size)).dp
                )
            ) {
                items(items) {
                    LnkDropdownTextItem(
                        itemText = it,
                        selected = selectedText == it,
                        onSelect = {
                            onItemClick(it)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun LnkDropdownTextItem(
    modifier: Modifier = Modifier,
    itemText: String,
    isHeader: Boolean = false,
    isFocused: Boolean = false,
    selected: Boolean = false,
    onSelect: (String) -> Unit = {}
) {
    Row(
        modifier = modifier
            .height(49.dp)
            .clickable {
                onSelect(itemText)
            }
            .padding(vertical = 16.dp, horizontal = 16.dp)
    ) {
        Text(
            modifier = Modifier
                .wrapContentHeight()
                .weight(1f),
            text = itemText,
            fontSize = 12.sp
        )
        Spacer(modifier = Modifier.width(32.dp))
        if (!isHeader && !selected) {
            Spacer(modifier = Modifier.size(16.dp))
        } else {
            Icon(
                modifier = Modifier
                    .rotate(
                        if (isHeader && !isFocused) 180f else 0f
                    )
                    .size(16.dp),
                imageVector = if (isHeader) LnkIcon.IcDropdownArrow
                else LnkIcon.BtnRadio,
                contentDescription = null
            )

        }
    }
}

@Preview
@Composable
fun LnkDropdownTextMenuPreview() {
    var selectedText by remember { mutableStateOf("기본") }
    var isFocused by remember { mutableStateOf(false) }
    MaterialTheme() {
        LnkDropdownTextMenu(
            selectedText = selectedText,
            onItemClick = {
                selectedText = it
                isFocused = false
            },
            isFocused = isFocused,
            onMenuClick = {
                isFocused = !isFocused
            },
            items = listOf("기본", "UIUX", "개발", "서버", "디자인", "안드", "iOS")
        )
    }
}