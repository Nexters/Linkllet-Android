package com.linkedlist.linkllet.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.linkedlist.linkllet.feature.home.navigation.AddEditFolder
import com.linkedlist.linkllet.feature.home.navigation.Home
import com.linkedlist.linkllet.feature.home.navigation.Search
import com.linkedlist.linkllet.feature.home.navigation.homeRoute
import com.linkedlist.linkllet.feature.home.navigation.navigateToAddEditFolder
import com.linkedlist.linkllet.feature.home.navigation.navigateToSearch
import com.linkedlist.linkllet.feature.link.navigation.AddEditLink
import com.linkedlist.linkllet.feature.link.navigation.AddEditLinkShared
import com.linkedlist.linkllet.feature.link.navigation.Links
import com.linkedlist.linkllet.feature.link.navigation.navigateToAddEditLink
import com.linkedlist.linkllet.feature.link.navigation.navigateToLinks
import com.linkedlist.linkllet.ui.LnkAppState
import linkedlist.linkllet.feature.settings.navigation.Feedback
import linkedlist.linkllet.feature.settings.navigation.Settings
import linkedlist.linkllet.feature.settings.navigation.navigateToFeedback
import linkedlist.linkllet.feature.settings.navigation.navigateToSettings

@Composable
fun LnkNavHost(
    appState: LnkAppState,
    modifier: Modifier = Modifier,
    startDestination: String = homeRoute,
    onShowSnackbar: suspend (String) -> Unit,
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
            navigateToLinks = { id, title,type ->
                navController.navigateToLinks(folderId = id,title = title,type = type )
            },
            navigateToSettings = navController::navigateToSettings,
            navigateToSearch = navController::navigateToSearch,
            onShowSnackbar = onShowSnackbar
        )
        AddEditLink(
            navigateAddFolder = navController::navigateToAddEditFolder,
            onBack = {
                navController.navigateUp()
            },
            onShowSnackbar = onShowSnackbar
        )
        AddEditLinkShared(
            navigateAddFolder = navController::navigateToAddEditFolder,
            onBack = {
                navController.navigateUp()
            },
            onShowSnackbar = onShowSnackbar
        )
        AddEditFolder(
            onBack = {
                navController.navigateUp()
            },
            onShowSnackbar = onShowSnackbar
        )
        Links(
            navigateAddLink = { folderId ->
                navController.navigateToAddEditLink(folderId = folderId)
            },
            onBack = {
                navController.navigateUp()
            },
            onShowSnackbar = onShowSnackbar
        )
        Settings(
            onBack = navController::navigateUp,
            navigateToFeedback = navController::navigateToFeedback,
        )
        Feedback(onBack = navController::navigateUp)
        Search(onBack = navController::navigateUp)
    }
}
