package com.linkedlist.linkllet.feature.link.navigation

import android.content.Intent
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.linkedlist.linkllet.feature.link.addeditlink.AddEditLinkScreen
import com.linkedlist.linkllet.feature.link.links.LinksScreen

const val FOLDER_ID = "folderId"
const val FOLDER_TITLE = "folderTitle"
const val FOLDER_TYPE = "folderType"
const val LINK_URL = "linkUrl"

const val linksRoute = "links/{${FOLDER_ID}}/{${FOLDER_TITLE}}/{${FOLDER_TYPE}}"
const val addEditLinkRoute = "addEditLink/{${FOLDER_ID}}/{${LINK_URL}}"

const val EMPTY_LINK = "EMPTY_LINK"

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
    folderId : Long? = -1,
    linkUrl : String = EMPTY_LINK
) {
    this.navigate("addEditLink/${folderId}/${linkUrl}", navOptions)
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
            },
            navArgument(LINK_URL) {
                type = NavType.StringType
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

fun NavGraphBuilder.AddEditLinkShared(
    navigateAddFolder : () -> Unit,
    onBack: () -> Unit,
    onShowSnackbar: suspend (String) -> Unit,
) {
    composable(
        route = "addEditLink",
        deepLinks = listOf(
            navDeepLink {
                action = Intent.ACTION_SEND
                mimeType = "text/*"
            }
        ),
    ) {
        AddEditLinkScreen(
            navigateAddFolder = navigateAddFolder,
            onBack = onBack,
            onShowSnackbar = onShowSnackbar
        )
    }
}