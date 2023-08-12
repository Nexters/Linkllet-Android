package com.linkedlist.linkllet.feature.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.linkedlist.linkllet.feature.home.search.SearchScreen

const val searchRoute = "search"

fun NavController.navigateToSearch(navOptions: NavOptions? = null) {
    this.navigate(searchRoute, navOptions)
}

fun NavGraphBuilder.Search(onBack: () -> Unit) {
    composable(route = searchRoute) {
        SearchScreen(
            onBack = onBack,
        )
    }
}