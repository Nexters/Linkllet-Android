package com.linkedlist.linkllet.feature.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.linkedlist.linkllet.core.designsystem.icon.LnkIcon
import com.linkedlist.linkllet.core.designsystem.icon.lnkicon.Clip
import com.linkedlist.linkllet.core.ui.LnkFloatingActionButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun HomeScreen(navigateAddLink: () -> Unit) {
    Scaffold(
        floatingActionButton = {
            LnkFloatingActionButton(
                onClick = { navigateAddLink() }
            ) {
                Icon(
                    imageVector = LnkIcon.Clip,
                    contentDescription = "링크 추가",
                    tint = Color.White
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            Text(
                "My\n" +
                        "Link\n" +
                        "Wallet"
            )
        }
    }
}

@Composable
@Preview
fun HomeScreenPreview() {
    HomeScreen{ }
}