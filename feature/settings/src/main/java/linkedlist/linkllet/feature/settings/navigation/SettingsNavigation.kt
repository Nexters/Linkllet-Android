package linkedlist.linkllet.feature.settings.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import linkedlist.linkllet.feature.settings.SettingsScreen

const val settingsRoute = "settings"

fun NavGraphBuilder.Settings() {
    composable(route = settingsRoute) {
        SettingsScreen()
    }
}
