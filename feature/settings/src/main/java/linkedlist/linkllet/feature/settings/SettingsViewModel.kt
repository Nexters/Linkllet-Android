package linkedlist.linkllet.feature.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.linkedlist.linkllet.core.login.LoginManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
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

sealed class Event() {
    object SendFeedback : Event()
    object Logout : Event()
}

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val loginManager : LoginManager
) : ViewModel() {
    private val _uiState = MutableStateFlow(
        SettingsUiState(
            settings = listOf(
                SettingModel.Item(name = "알림 설정", ::showDialog),
                SettingModel.Item(name = "사용 방법", ::showDialog),
                SettingModel.Item(
                    name = "서비스 의견 보내기",
                    action = { emitEvent(Event.SendFeedback) }
                ),
                SettingModel.Divider,
                SettingModel.Item(name = "링크 휴지통", ::showDialog),
                SettingModel.Divider,
                SettingModel.Item(name = "제작자 소개", ::showDialog),
                SettingModel.Item(name = "현재 버전", ::showDialog),
                SettingModel.Item(name = "로그아웃",
                    action = { logout() } )
            )
        )
    )
    val uiState: StateFlow<SettingsUiState> = _uiState.asStateFlow()

    private val _eventsFlow = MutableSharedFlow<Event>()
    val eventFlow: SharedFlow<Event> = _eventsFlow.asSharedFlow()

    private fun logout(){
        viewModelScope.launch {
            loginManager.logout().collectLatest {
                emitEvent(Event.Logout)
            }
        }
    }

    fun emitEvent(event: Event) {
        viewModelScope.launch {
            _eventsFlow.emit(event)
        }
    }

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