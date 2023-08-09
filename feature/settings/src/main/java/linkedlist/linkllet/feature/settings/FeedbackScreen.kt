package linkedlist.linkllet.feature.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.linkedlist.linkllet.core.designsystem.icon.LnkIcon
import com.linkedlist.linkllet.core.designsystem.icon.lnkicon.X
import com.linkedlist.linkllet.core.designsystem.theme.Typography
import com.linkedlist.linkllet.core.ui.LnkAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedbackScreen(
    onBack: () -> Unit,
) {
    Scaffold(
        containerColor = Color.White,
        topBar = {
            LnkAppBar(
                Modifier.shadow(elevation = 4.dp), // fixme : 임시 그림자
                title = { Text("설정", style = Typography.titleMedium) },
                action = {
                    Icon(
                        modifier = Modifier.clickable { onBack() },
                        imageVector = LnkIcon.X, contentDescription = "닫기"
                    )
                }
            )
        }
    ) {
        Box(modifier = Modifier.padding(it))
    }
}