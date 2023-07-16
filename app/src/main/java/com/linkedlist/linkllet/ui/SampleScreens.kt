package com.linkedlist.linkllet.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.linkedlist.linkllet.core.designsystem.theme.LinklletTheme

// TODO : 임시 file. 나중에 삭제.

const val settingsRoute = "settings"

fun NavGraphBuilder.Settings() {
    composable(route = settingsRoute) {
        SampleSettingsScreen()
    }
}

@Composable
fun SampleSettingsScreen() {
    Greeting("Settings!")
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LinklletTheme {
        Greeting("Android")
    }
}