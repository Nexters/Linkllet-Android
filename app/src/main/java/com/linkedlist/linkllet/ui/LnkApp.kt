package com.linkedlist.linkllet.ui

import androidx.compose.runtime.Composable
import com.linkedlist.linkllet.navigation.LnkNavHost

@Composable
fun LnkApp(
    appState: LnkAppState = rememberLnkAppState(),
) {
    LnkNavHost(appState = appState)
}