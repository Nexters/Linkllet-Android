package com.linkedlist.linkllet.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.linkedlist.linkllet.ui.LnkAppState
import com.linkedlist.linkllet.ui.Home
import com.linkedlist.linkllet.ui.Settings
import com.linkedlist.linkllet.ui.homeRoute

@Composable
fun LnkNavHost(
    appState: LnkAppState,
    modifier: Modifier = Modifier,
    startDestination: String = homeRoute,
) {
    val navController = appState.navController
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
    ) {
        Home()
        Settings()
    }
}
