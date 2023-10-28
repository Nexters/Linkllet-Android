package linkedlist.linkllet.feature.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.linkedlist.linkllet.core.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class FeedbackEvent {
    object CloseScreen : FeedbackEvent()
}

@HiltViewModel
class FeedbackViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    private val _content = MutableStateFlow("")
    val content = _content.asStateFlow()

    private val _eventsFlow = MutableSharedFlow<FeedbackEvent>()
    val eventsFlow: SharedFlow<FeedbackEvent> = _eventsFlow.asSharedFlow()

    fun updateContent(newContent: String) {
        _content.value = newContent
    }

    fun sendFeedback() {
        authRepository.addFeedback(content = content.value)
            .onEach {
                _eventsFlow.emit(FeedbackEvent.CloseScreen)
            }
            .launchIn(viewModelScope)
    }
}