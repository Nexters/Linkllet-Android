package com.linkedlist.linkllet.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.linkedlist.linkllet.feature.home.navigation.AddEditFolder
import com.linkedlist.linkllet.feature.home.navigation.Home
import com.linkedlist.linkllet.feature.home.navigation.homeRoute
import com.linkedlist.linkllet.feature.home.navigation.navigateToAddEditFolder
import com.linkedlist.linkllet.feature.link.navigation.AddEditLink
import com.linkedlist.linkllet.feature.link.navigation.navigateToAddEditLink
import com.linkedlist.linkllet.ui.LnkAppState
import com.linkedlist.linkllet.ui.Settings

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
        Home(
            navigateAddLink = navController::navigateToAddEditLink,
            navigateToAddEdit = navController::navigateToAddEditFolder,
        )
        AddEditLink(
            onBack = {
                navController.navigateUp()
            }
        )
        AddEditFolder(
            onBack = {
                navController.navigateUp()
            }
        )
        Settings()
    }
}
