package linkedlist.linkllet.feature.settings.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import linkedlist.linkllet.feature.settings.FeedbackScreen

const val feedbackRoute = "feedback"

fun NavGraphBuilder.Feedback(onBack: () -> Unit) {
    composable(route = feedbackRoute) {
        FeedbackScreen(onBack = onBack)
    }
}

fun NavController.navigateToFeedback(navOptions: NavOptions? = null) {
    this.navigate(feedbackRoute, navOptions)
}
