package com.linkedlist.linkllet.core.ui

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.linkedlist.linkllet.core.designsystem.icon.LnkIcon
import com.linkedlist.linkllet.core.designsystem.icon.lnkicon.Clip

@Composable
fun LnkFloatingActionButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    content: @Composable () -> Unit,
) {
    FloatingActionButton(
        modifier = modifier,
        shape = RoundedCornerShape(100.dp),
        containerColor = Color.Black,
        onClick = { onClick() },
    ) {
        content()
    }
}

@Preview
@Composable
fun LnkFloatingActionButtonPreview() {
    LnkFloatingActionButton(
        onClick = {}
    ) {
        Icon(
            imageVector = LnkIcon.Clip,
            contentDescription = "링크 추가",
            tint = Color.White
        )
    }
}
