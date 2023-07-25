package com.linkedlist.linkllet.feature.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.linkedlist.linkllet.feature.home.folder.AddEditFolderScreen

const val addEditFolderRoute = "addEditFolder"

fun NavController.navigateToAddEditFolder(navOptions: NavOptions? = null) {
    this.navigate(addEditFolderRoute, navOptions)
}

fun NavGraphBuilder.AddEditFolder(
    onBack: () -> Unit,
    onShowSnackbar: suspend (String) -> Boolean,
) {
    composable(route = addEditFolderRoute) {
        AddEditFolderScreen(onBack = onBack, onShowSnackbar = onShowSnackbar)
    }
}