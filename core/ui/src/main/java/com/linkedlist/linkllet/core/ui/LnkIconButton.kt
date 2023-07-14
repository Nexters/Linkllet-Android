package com.linkedlist.linkllet.core.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun LnkIconButton(
    modifier : Modifier = Modifier,
    innerPadding : Dp = 0.dp,
    onClick : () -> Unit,
    content : @Composable () -> Unit
){
    Box(
        modifier = modifier
            .clip(CircleShape)
            .clickable {
                onClick()
            }
            .padding(innerPadding)
    ){
        content()
    }
}