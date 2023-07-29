package linkedlist.linkllet.feature.settings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.linkedlist.linkllet.core.designsystem.theme.Typography
import com.linkedlist.linkllet.core.ui.LnkAppBar
import com.linkedlist.linkllet.core.ui.SettingItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen() {
    val items = listOf("현재 버전")

    Scaffold(
        containerColor = Color.White,
        topBar = {
            LnkAppBar(
                Modifier.shadow(elevation = 4.dp), // fixme : 임시 그림자
                title = { Text("설정", style = Typography.titleMedium) },
            )
        }
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding)) {
            items(items) {
                SettingItem(
                    name = it,
                    onClick = {  }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    SettingsScreen()
}