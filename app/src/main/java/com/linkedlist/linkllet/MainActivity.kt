package com.linkedlist.linkllet

import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.linkedlist.linkllet.core.designsystem.theme.LinklletTheme
import com.linkedlist.linkllet.ui.LnkApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val splashScreen = installSplashScreen()

        splashScreen.setKeepOnScreenCondition {
            when (viewModel.uiState.value) {
                MainUiState.Loading -> {
                    true
                }
                MainUiState.LoginFailed -> {
                    false
                }
                is MainUiState.LoginSuccess -> {
                    checkIntent()
                    false
                }
            }
        }

        viewModel.checkLoginType(context = this)

        setContent {
            val uiState by viewModel.uiState.collectAsState()

            LinklletTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    when(uiState){
                        is MainUiState.LoginSuccess,
                        MainUiState.LoginFailed -> {
                            LnkApp(mainViewModel = viewModel)
                        }

                        else -> {
                            // TODO Loading 화면 있으면 좋을 듯?
                        }
                    }

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
        if(!hasFocus) return
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

    private fun checkIntent() { // 공유하기로 시작된 앱인지 체크하는 플래그 값 설정
        if(!intent?.getStringExtra(Intent.EXTRA_TEXT).isNullOrBlank()){
            viewModel.setIsStartedBySharedLink(true)
        }
    }
}
