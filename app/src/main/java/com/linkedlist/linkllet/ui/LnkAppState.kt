package com.linkedlist.linkllet.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun rememberLnkAppState(
    navController: NavHostController = rememberNavController()
): LnkAppState {
    return remember(
        navController
    ) {
        LnkAppState(navController)
    }
}

@Stable
class LnkAppState(
    val navController: NavHostController,
)