package linkedlist.linkllet.feature.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.linkedlist.linkllet.core.designsystem.icon.LnkIcon
import com.linkedlist.linkllet.core.designsystem.icon.lnkicon.X
import com.linkedlist.linkllet.core.designsystem.theme.Typography
import com.linkedlist.linkllet.core.ui.LnkAppBar
import com.linkedlist.linkllet.core.ui.LnkButtonWithMargin
import com.linkedlist.linkllet.core.ui.LnkTextFieldWithTitle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedbackScreen(
    viewModel: FeedbackViewModel = hiltViewModel(),
    onBack: () -> Unit,
) {
    val content by viewModel.content.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.eventsFlow.collect { event ->
            when(event) {
                is FeedbackEvent.CloseScreen -> onBack()
            }
        }
    }

    Scaffold(
        containerColor = Color.White,
        topBar = {
            LnkAppBar(
                Modifier.shadow(elevation = 4.dp), // fixme : 임시 그림자
                title = { Text("서비스 의견 보내기", style = Typography.titleMedium) },
                action = {
                    Icon(
                        modifier = Modifier.clickable { onBack() },
                        imageVector = LnkIcon.X, contentDescription = "닫기"
                    )
                }
            )
        }
    ) {
        Column(Modifier.padding(it)) {
            LnkTextFieldWithTitle(
                modifier = Modifier
                    .padding(horizontal = 18.dp)
                    .padding(top = 20.dp),
                title = "의견",
                value = content,
                onValueChange = viewModel::updateContent,
                maxLength = 200,
                maxLines = 5,
                isVisibleMaxLengthNotice = true,
            )
            Spacer(modifier = Modifier.weight(1.0f))
            LnkButtonWithMargin(
                text = "저장하기",
                onClick = viewModel::sendFeedback
            )
        }
    }
}

@Composable
@Preview
fun FeedbackScreenPreview() {
    FeedbackScreen(onBack = {})
}