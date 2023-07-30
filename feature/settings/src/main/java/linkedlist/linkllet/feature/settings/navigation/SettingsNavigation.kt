package linkedlist.linkllet.feature.settings.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import linkedlist.linkllet.feature.settings.SettingsScreen

const val settingsRoute = "settings"

fun NavGraphBuilder.Settings(onBack: () -> Unit) {
    composable(route = settingsRoute) {
        SettingsScreen(onBack = { onBack() })
    }
}

fun NavController.navigateToSettings(navOptions: NavOptions? = null) {
    this.navigate(settingsRoute, navOptions)
}
