package com.linkedlist.linkllet.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun LnkAppBar(
    modifier: Modifier = Modifier,
    title: @Composable () -> Unit,
    backButton: @Composable () -> Unit = {},
    action: @Composable () -> Unit = {}
) {
    Box(
        modifier = modifier
            .background(color = Color.White)
            .padding(horizontal = 18.dp)
            .fillMaxWidth()
            .height(60.dp),
        contentAlignment = Alignment.Center,
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            backButton()
            Spacer(modifier = Modifier.weight(1.0f))
            action()
        }
        title()
    }
}

@Preview
@Composable
fun LnkAppBarPreview() {
    LnkAppBar(
        title = { Text("Linkllet") },
        backButton = {
            Icon(
                imageVector = Icons.Rounded.ArrowBack,
                contentDescription = "back"
            )
        },
        action = {
            Icon(imageVector = Icons.Rounded.Settings, contentDescription = "settings")
        },
    )
}

@Preview
@Composable
fun LnkAppBarPreview2() {
    LnkAppBar(
        title = { Text("Linkllet") },
        action = {
            Icon(imageVector = Icons.Rounded.Settings, contentDescription = "settings")
        },
    )
}