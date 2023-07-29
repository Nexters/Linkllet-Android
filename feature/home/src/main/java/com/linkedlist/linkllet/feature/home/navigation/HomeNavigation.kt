package com.linkedlist.linkllet.feature.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.linkedlist.linkllet.feature.home.HomeScreen

const val homeRoute = "home"

fun NavController.navigateToHome(navOptions: NavOptions? = null) {
    this.navigate(homeRoute, navOptions)
}

fun NavGraphBuilder.Home(
    navigateAddLink: () -> Unit,
    navigateToAddEdit: () -> Unit,
    navigateToLinks: (Long,String,String) -> Unit,
    navigateToSettings: () -> Unit,
    onShowSnackbar: suspend (String) -> Unit,
) {
    composable(route = homeRoute) {
        HomeScreen(
            navigateToAddLink = navigateAddLink,
            navigateToAddEditFolder = navigateToAddEdit,
            navigateToLinks = navigateToLinks,
            navigateToSettings = navigateToSettings,
            onShowSnackbar = onShowSnackbar,
        )
    }
}