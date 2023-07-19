package com.linkedlist.linkllet.feature.link.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.linkedlist.linkllet.feature.link.addeditlink.AddEditLinkScreen


const val linkRoute = "link"
const val addEditLinkRoute = "addEditLink"

fun NavController.navigateToLink(navOptions: NavOptions? = null) {
    this.navigate(linkRoute, navOptions)
}

fun NavController.navigateToAddEditLink(navOptions: NavOptions? = null) {
    this.navigate(addEditLinkRoute, navOptions)
}

//fun NavGraphBuilder.Link(navigateLink: () -> Unit) {
//    composable(route = linkRoute) {
//        HomeScreen(navigateAddLink = navigateAddLink)
//    }
//}

fun NavGraphBuilder.AddEditLink() {
    composable(route = addEditLinkRoute) {
        AddEditLinkScreen()
    }
}