package linkedlist.linkllet.feature.settings

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

sealed class SettingModel {
    data class Item(
        val name: String,
        val action: () -> Unit,
    ) : SettingModel()

    object Divider : SettingModel()
}

data class SettingsUiState(
    val settings: List<SettingModel>,
    val dialogVisibility: Boolean = false,
)

@HiltViewModel
class SettingsViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(
        SettingsUiState(
            settings = listOf(
                SettingModel.Item(name = "알림 설정", ::showDialog),
                SettingModel.Item(name = "사용 방법", ::showDialog),
                SettingModel.Item(name = "서비스 의견 보내기", ::showDialog),
                SettingModel.Divider,
                SettingModel.Item(name = "링크 휴지통", ::showDialog),
                SettingModel.Divider,
                SettingModel.Item(name = "제작자 소개", ::showDialog),
                SettingModel.Item(name = "현재 버전", ::showDialog),
            )
        )
    )
    val uiState: StateFlow<SettingsUiState> = _uiState.asStateFlow()

    fun showDialog() {
        _uiState.update {
            uiState.value.copy(
                dialogVisibility = true
            )
        }
    }

    fun hideDialog() {
        _uiState.update {
            uiState.value.copy(
                dialogVisibility = false
            )
        }
    }
}