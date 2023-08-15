package com.linkedlist.linkllet

import android.content.Intent
import android.os.Bundle
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

    val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LinklletTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LnkApp()
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
}