package com.linkedlist.linkllet.feature.login.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.linkedlist.linkllet.feature.login.LoginScreen

const val loginRoute = "login"

fun NavController.navigateToLogin(
    navOptions: NavOptions? = null,
) {
    this.navigate(loginRoute, navOptions)
}

fun NavGraphBuilder.Login(
    navigateLogin: () -> Unit,
    onBack: () -> Unit = {},
    onShowSnackbar: suspend (String) -> Unit = {},
) {

    composable(
        route = loginRoute,
    ) {
        LoginScreen()
    }
}