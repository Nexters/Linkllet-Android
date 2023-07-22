package com.linkedlist.linkllet.feature.link.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.linkedlist.linkllet.feature.link.addeditlink.AddEditLinkScreen
import com.linkedlist.linkllet.feature.link.links.LinksScreen

const val FOLDER_ID = "folderId"
const val FOLDER_TITLE = "folderTitle"

const val linksRoute = "links/{${FOLDER_ID}}/{${FOLDER_TITLE}}"
const val addEditLinkRoute = "addEditLink"

fun NavController.navigateToLinks(
    navOptions: NavOptions? = null,
    folderId : Long,
    title : String
) {
    this.navigate("links/${folderId}/${title}", navOptions)
}

fun NavController.navigateToAddEditLink(navOptions: NavOptions? = null) {
    this.navigate(addEditLinkRoute, navOptions)
}

fun NavGraphBuilder.Links(
    navigateAddLink: () -> Unit,
    onBack: () -> Unit,
    onShowSnackbar: suspend (String) -> Boolean,
) {

    composable(
        route = linksRoute,
        arguments = listOf(
            navArgument(FOLDER_ID) {
                type = NavType.LongType
            },
            navArgument(FOLDER_TITLE) {
                type = NavType.StringType
            }
        )
    ) {
        LinksScreen(
            navigateAddLink = navigateAddLink,
            onBack = onBack,
            onShowSnackbar = onShowSnackbar
        )
    }
}

fun NavGraphBuilder.AddEditLink(
    onBack: () -> Unit,
    onShowSnackbar: suspend (String) -> Boolean,
) {
    composable(route = addEditLinkRoute) {
        AddEditLinkScreen(
            onBack = onBack,
            onShowSnackbar = onShowSnackbar
        )
    }
}