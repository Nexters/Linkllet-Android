package com.linkedlist.linkllet

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.linkedlist.linkllet.core.data.repository.AuthRepository
import com.linkedlist.linkllet.core.login.LoginManager
import com.linkedlist.linkllet.core.login.LoginType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class Event {
    data class NavigateToAddEditLink(val url: String) : Event()
}

sealed interface MainUiState {
    object Loading : MainUiState
    object LoginSuccess : MainUiState

    object LoginFailed : MainUiState
}

@HiltViewModel
class MainViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val loginManager: LoginManager
) : ViewModel() {

    private val _clipboardUrl = MutableStateFlow("")
    val clipboardUrl: StateFlow<String> = _clipboardUrl.asStateFlow()

    private val _eventsFlow: MutableSharedFlow<Event> = MutableSharedFlow()
    val eventsFlow = _eventsFlow.asSharedFlow()

    private val _uiState = MutableStateFlow<MainUiState>(MainUiState.Loading)
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    private var isStartedBySharedLink = false

    fun getIsStartedBySharedLink(): Boolean = isStartedBySharedLink

    fun isLoggedIn() : Boolean = loginManager.isLoggedIn

    fun setIsStartedBySharedLink(isStarted: Boolean) {
        isStartedBySharedLink = isStarted
    }



    fun checkLoginType(context: Context){
        viewModelScope.launch {
            loginManager.getLoginType()
                .collectLatest {
                    when(it){
                        LoginType.KAKAO -> {
                            loginWithKakao(context)
                        }
                        LoginType.GUEST -> {
                            loginAsGuest()
                        }
                        LoginType.LOGOUT -> {
                            _uiState.emit(MainUiState.LoginFailed)
                        }
                    }
                }
        }
    }

    private suspend fun loginWithKakao(context: Context){
        loginManager.loginWithKakao(context)
            .collectLatest {
                it.onSuccess {
                    signUp()
                }.onFailure {
                    _uiState.emit(MainUiState.LoginFailed)
                }
            }
    }

    private suspend fun loginAsGuest(){
        loginManager.loginAsGuest()
            .collectLatest {
                it.onSuccess {
                    signUp()
                }.onFailure {
                    _uiState.emit(MainUiState.LoginFailed)
                }
            }
    }

    private fun signUp() {
        authRepository.signUp()
            .onEach {
                _uiState.emit(MainUiState.LoginSuccess)
            }
            .catch {
                _uiState.emit(MainUiState.LoginFailed)
            }
            .launchIn(viewModelScope)
    }

    fun navigateToAddEditLink() {
        viewModelScope.launch {
            _eventsFlow.emit(Event.NavigateToAddEditLink(clipboardUrl.value))
        }
    }

    fun updateClipboardUrl(url: String) {
        viewModelScope.launch {
            if (isValidUrl(url))
                _clipboardUrl.emit(url)
        }
    }

    private fun isValidUrl(str: String?): Boolean {
        return try {
            val uri = Uri.parse(str)
            return uri?.scheme == "http" || uri?.scheme == "https"
        } catch (e: Exception) {
            false
        }

    }
}