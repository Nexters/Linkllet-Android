package linkedlist.linkllet.feature.settings.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import linkedlist.linkllet.feature.settings.SettingsScreen

const val settingsRoute = "settings"

fun NavGraphBuilder.Settings(
    onBack: () -> Unit,
    navigateToFeedback: () -> Unit,
    navigateToLogin : () -> Unit
    ) {
    composable(route = settingsRoute) {
        SettingsScreen(
            onBack = onBack,
            navigateToFeedback = navigateToFeedback,
            navigateToLogin = navigateToLogin
        )
    }
}

fun NavController.navigateToSettings(navOptions: NavOptions? = null) {
    this.navigate(settingsRoute, navOptions)
}
