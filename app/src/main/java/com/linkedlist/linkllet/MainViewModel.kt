package com.linkedlist.linkllet

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class Event {
    data class NavigateToAddEditLink(val url: String) : Event()
}

@HiltViewModel
class MainViewModel @Inject constructor(

) : ViewModel() {

    private val _clipboardUrl = MutableStateFlow("")
    val clipboardUrl: StateFlow<String> = _clipboardUrl.asStateFlow()

    private val _eventsFlow: MutableSharedFlow<Event> = MutableSharedFlow()
    val eventsFlow = _eventsFlow.asSharedFlow()

    var sharedLinkStartFlag = false

    fun navigateToAddEditLink(){
        viewModelScope.launch {
            _eventsFlow.emit(Event.NavigateToAddEditLink(clipboardUrl.value))
        }
    }

    fun updateClipboardUrl(url : String){
        viewModelScope.launch {
            if(isValidUrl(url))
                _clipboardUrl.emit(url)
        }
    }
    private fun isValidUrl(str : String?) : Boolean {
        return try {
            val uri = Uri.parse(str)
            return uri?.scheme == "http" || uri?.scheme == "https"
        }catch(e:Exception){
            false
        }

    }
}