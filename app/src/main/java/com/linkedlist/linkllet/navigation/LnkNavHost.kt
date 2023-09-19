package com.linkedlist.linkllet.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.navOptions
import com.linkedlist.linkllet.feature.home.navigation.AddEditFolder
import com.linkedlist.linkllet.feature.home.navigation.Home
import com.linkedlist.linkllet.feature.home.navigation.Search
import com.linkedlist.linkllet.feature.home.navigation.homeRoute
import com.linkedlist.linkllet.feature.home.navigation.navigateToAddEditFolder
import com.linkedlist.linkllet.feature.home.navigation.navigateToHome
import com.linkedlist.linkllet.feature.home.navigation.navigateToSearch
import com.linkedlist.linkllet.feature.link.navigation.AddEditLink
import com.linkedlist.linkllet.feature.link.navigation.AddEditLinkShared
import com.linkedlist.linkllet.feature.link.navigation.Links
import com.linkedlist.linkllet.feature.link.navigation.navigateToAddEditLink
import com.linkedlist.linkllet.feature.link.navigation.navigateToLinks
import com.linkedlist.linkllet.feature.login.navigation.Login
import com.linkedlist.linkllet.feature.login.navigation.loginRoute
import com.linkedlist.linkllet.feature.login.navigation.navigateToLogin
import com.linkedlist.linkllet.ui.LnkAppState
import linkedlist.linkllet.feature.settings.navigation.Feedback
import linkedlist.linkllet.feature.settings.navigation.Settings
import linkedlist.linkllet.feature.settings.navigation.navigateToFeedback
import linkedlist.linkllet.feature.settings.navigation.navigateToSettings

@Composable
fun LnkNavHost(
    appState: LnkAppState,
    modifier: Modifier = Modifier,
    startDestination: String = loginRoute,
    onShowSnackbar: suspend (String) -> Unit,
    onCancelSnackbar: () -> Unit
) {
    val navController = appState.navController
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
    ) {
        Login(
            navigateHome = {
                navController.navigateToHome(navOptions = navOptions {
                    popUpTo(homeRoute){
                        inclusive = true
                    }
                })
            }
        )
        Home(
            navigateAddLink = {
                onCancelSnackbar()
                navController.navigateToAddEditLink()
            },
            navigateToAddEdit = {
                onCancelSnackbar()
                navController.navigateToAddEditFolder()

            },
            navigateToLinks = { id, title, type ->
                onCancelSnackbar()
                navController.navigateToLinks(folderId = id, title = title, type = type)
            },
            navigateToSettings = {
                onCancelSnackbar()
                navController.navigateToSettings()
            } ,
            navigateToSearch =  {
                onCancelSnackbar()
                navController.navigateToSearch()
            },
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
            navigateToLogin = {
                navController.navigateToLogin(navOptions {
                    popUpTo(navController.graph.id){
                        inclusive =true
                    }
                })
            }
        )
        Feedback(onBack = navController::navigateUp)
        Search(onBack = navController::navigateUp)
    }
}
