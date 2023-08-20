package com.linkedlist.linkllet.ui

import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.linkedlist.linkllet.Event
import com.linkedlist.linkllet.MainViewModel
import com.linkedlist.linkllet.core.designsystem.theme.ColorCC000000
import com.linkedlist.linkllet.core.designsystem.theme.Typography
import com.linkedlist.linkllet.feature.link.navigation.navigateToAddEditLink
import com.linkedlist.linkllet.navigation.LnkNavHost
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LnkApp(
    appState: LnkAppState = rememberLnkAppState(),
    mainViewModel: MainViewModel
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val height = LocalConfiguration.current.screenHeightDp

    LaunchedEffect(Unit) {
        mainViewModel.clipboardUrl.collect {

            if (it.isNotBlank()) {
                if (!mainViewModel.getIsStartedBySharedLink()) { // 공유하기로 시작된 경우라면 스낵바를 띄우지 않는다.
                    snackbarHostState.showSnackbar(
                        message = "복사된 링크가 있어요!",
                        actionLabel = SnackBarActionLabel.SAVED_LINK.name,
                        duration = SnackbarDuration.Long
                    )
                } else {
                    mainViewModel.setIsStartedBySharedLink(false)
                }
            }

        }
    }

    LaunchedEffect(Unit) {
        mainViewModel.eventsFlow.collect {
            when (it) {
                is Event.NavigateToAddEditLink -> {
                    appState.navController.navigateToAddEditLink(linkUrl = Uri.encode(it.url))
                }
            }
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(
                modifier = Modifier
                    .padding(bottom = (height - 80).dp)
                    .height(60.dp),
                hostState = snackbarHostState,
                snackbar = { snackbarData ->

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = ColorCC000000
                        )
                    ) {
                        Box(
                            Modifier
                                .fillMaxSize()
                                .padding(vertical = 16.dp, horizontal = 20.dp)
                        ) {
                            Text(
                                modifier = Modifier
                                    .align(
                                        if (snackbarData.visuals.actionLabel == SnackBarActionLabel.SAVED_LINK.name) Alignment.CenterStart
                                        else Alignment.Center
                                    ),
                                text = snackbarData.visuals.message,
                                color = Color.White,
                                style = Typography.bodySmall
                            )
                            if (snackbarData.visuals.actionLabel == SnackBarActionLabel.SAVED_LINK.name)
                                Text(
                                    modifier = Modifier
                                        .align(Alignment.CenterEnd)
                                        .clickable {
                                            snackbarData.dismiss()
                                            mainViewModel.navigateToAddEditLink()
                                        },
                                    text = SnackBarActionLabel.SAVED_LINK.text,
                                    color = Color.White,
                                    style = Typography.bodySmall,
                                    textDecoration = TextDecoration.Underline
                                )
                        }

                    }
                }
            )
        }
    ) { innerPadding ->
        LnkNavHost(
            appState = appState,
            modifier = Modifier.padding(innerPadding),
            onShowSnackbar = {
                val job = coroutineScope.launch {
                    snackbarHostState.showSnackbar(
                        message = it,
                        duration = SnackbarDuration.Short
                    )
                }

                coroutineScope.launch {
                    delay(2000L)
                    job.cancel()
                }
            },
            onCancelSnackbar = {
                snackbarHostState.currentSnackbarData?.dismiss()
            }
        )
    }
}

enum class SnackBarActionLabel(val text: String = "") {
    SAVED_LINK("링크 저장하기")
}

