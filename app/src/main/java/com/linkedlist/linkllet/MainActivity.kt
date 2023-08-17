package com.linkedlist.linkllet

import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.linkedlist.linkllet.core.designsystem.theme.LinklletTheme
import com.linkedlist.linkllet.ui.LnkApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkIntent()
        setContent {
            LinklletTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LnkApp(mainViewModel = viewModel)
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent?) { // 이미 앱이 실행 중일 때(인스턴스 존재할 때) 처리
        super.onNewIntent(intent)
        if(!intent?.getStringExtra(Intent.EXTRA_TEXT).isNullOrBlank()){
            finish()
            startActivity(intent)
        }
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if(hasFocus){
            (getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager).let {
                if(it.hasPrimaryClip()){
                    if(it.primaryClip?.getItemAt(0)?.uri != null ){
                        viewModel.updateClipboardUrl("${it.primaryClip?.getItemAt(0)?.uri?.toString()}")
                    }else if(it.primaryClip?.getItemAt(0)?.text != null )  {
                        viewModel.updateClipboardUrl("${it.primaryClip?.getItemAt(0)?.text}")
                    }
                }
            }
        }
    }

    private fun checkIntent() { // 공유하기로 시작된 앱인지 체크하는 플래그 값 설정
        if(!intent?.getStringExtra(Intent.EXTRA_TEXT).isNullOrBlank()){
            viewModel.sharedLinkStartFlag = true
        }
    }
}
