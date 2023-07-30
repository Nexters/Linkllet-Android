package com.linkedlist.linkllet

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.linkedlist.linkllet.core.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _isSignedUp = MutableStateFlow(false)
    val isSignedUp : StateFlow<Boolean> = _isSignedUp.asStateFlow()

    fun signUp() {
        viewModelScope.launch {
            try {
                authRepository.signUp()
                    .catch {
                        // TODO Error Handling
                    }.collect {
                        it.onSuccess {
                            if(it){
                                _isSignedUp.emit(true)
                            }else {
                                _isSignedUp.emit(false)
                            }
                        }.onFailure {
                            // TODO Error Handling
                        }
                    }
            }catch (e : Exception){
                // TODO Error Handling
            }
        }
    }
}