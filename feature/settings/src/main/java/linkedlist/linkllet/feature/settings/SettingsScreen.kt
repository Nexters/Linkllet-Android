package linkedlist.linkllet.feature.settings

import android.content.pm.PackageManager
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import com.linkedlist.linkllet.core.designsystem.theme.Gray200
import com.linkedlist.linkllet.core.designsystem.theme.Typography
import com.linkedlist.linkllet.core.ui.LnkAppBar
import com.linkedlist.linkllet.core.ui.LnkDialog
import com.linkedlist.linkllet.core.ui.SettingItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel(),
    onBack: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()

    LnkDialog(
        text = "해당 기능은 현재 준비중입니다 :)",
        visible = uiState.dialogVisibility,
        onDismissRequest = viewModel::hideDialog,
    )

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
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding)) {
            items(uiState.settings) {
                if(it is SettingModel.Item) {
                    SettingItem(
                        name = it.name,
                        onClick = viewModel::showDialog
                    )
                } else {
                    Divider(
                        modifier = Modifier.height(10.dp),
                        color = Gray200,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    SettingsScreen(onBack = {})
}