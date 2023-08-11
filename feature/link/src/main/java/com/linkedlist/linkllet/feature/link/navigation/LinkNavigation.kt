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
const val FOLDER_TYPE = "folderType"

const val linksRoute = "links/{${FOLDER_ID}}/{${FOLDER_TITLE}}/{${FOLDER_TYPE}}"
const val addEditLinkRoute = "addEditLink/{${FOLDER_ID}}"

fun NavController.navigateToLinks(
    navOptions: NavOptions? = null,
    folderId : Long,
    title : String,
    type: String
) {
    this.navigate("links/${folderId}/${title}/${type}", navOptions)
}

fun NavController.navigateToAddEditLink(
    navOptions: NavOptions? = null,
    folderId : Long? = -1
) {
    this.navigate("addEditLink/${folderId}", navOptions)
}

fun NavGraphBuilder.Links(
    navigateAddLink: (Long) -> Unit,
    onBack: () -> Unit,
    onShowSnackbar: suspend (String) -> Unit,
) {

    composable(
        route = linksRoute,
        arguments = listOf(
            navArgument(FOLDER_ID) {
                type = NavType.LongType
            },
            navArgument(FOLDER_TITLE) {
                type = NavType.StringType
            },
            navArgument(FOLDER_TYPE){
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
    navigateAddFolder : () -> Unit,
    onBack: () -> Unit,
    onShowSnackbar: suspend (String) -> Unit,
) {
    composable(
        route = addEditLinkRoute,
        arguments = listOf(
            navArgument(FOLDER_ID) {
                type = NavType.LongType
            }
        )
    ) {
        AddEditLinkScreen(
            navigateAddFolder = navigateAddFolder,
            onBack = onBack,
            onShowSnackbar = onShowSnackbar
        )
    }
}