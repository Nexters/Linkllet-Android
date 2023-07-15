package com.linkedlist.linkllet.core.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntRect
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupPositionProvider

@Composable
fun LnkDropdownTextMenu(
    modifier: Modifier = Modifier,
    selectedText: String,
    items: List<String> = emptyList(),
    isFocused: Boolean = false,
    onMenuClick: () -> Unit = {},
    onItemClick: (String) -> Unit = {},
    onDismissRequest : () -> Unit = {}
) {
    Popup(
        onDismissRequest = onDismissRequest,
        popupPositionProvider = object : PopupPositionProvider {
            override fun calculatePosition(
                anchorBounds: IntRect,
                windowSize: IntSize,
                layoutDirection: LayoutDirection,
                popupContentSize: IntSize
            ): IntOffset {
                return IntOffset(0, 0)
            }

        }) {
        OutlinedCard(
            modifier = modifier.wrapContentHeight(),
            shape = RoundedCornerShape(12.dp)
        ) {
            LnkDropdownTextItem(
                itemText = selectedText,
                isHeader = true,
                onSelect = {
                    onMenuClick()
                }
            )

            AnimatedVisibility(visible = isFocused && items.isNotEmpty()) {
                Divider()
                LazyColumn(
                    modifier = Modifier.height(
                        (54 * (if (items.size > 4) 4 else items.size)).dp
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
}

@Composable
fun LnkDropdownTextItem(
    modifier: Modifier = Modifier,
    itemText: String,
    isHeader: Boolean = false,
    selected: Boolean = false,
    onSelect: (String) -> Unit = {}
) {
    Row(
        modifier = modifier
            .wrapContentHeight()
            .clickable {
                onSelect(itemText)
            }
            .padding(vertical = 16.dp, horizontal = 16.dp)
    ) {
        Text(
            modifier = Modifier
                .wrapContentHeight()
                .weight(1f),
            text = itemText
        )
        Spacer(modifier = Modifier.width(32.dp))
        Icon(
            imageVector = if (isHeader) Icons.Filled.ArrowDropDown
            else if (selected) Icons.Filled.CheckCircle
            else Icons.Filled.Add,
            contentDescription = null
        )
    }
}

@Preview
@Composable
fun LnkDropdownTextMenuPreview() {
    MaterialTheme() {
        LnkDropdownTextMenu(selectedText = "기본")
    }
}