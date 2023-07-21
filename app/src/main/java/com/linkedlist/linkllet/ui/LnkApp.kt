package com.linkedlist.linkllet.ui

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
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.linkedlist.linkllet.core.designsystem.theme.ColorCC000000
import com.linkedlist.linkllet.core.designsystem.theme.Typography
import com.linkedlist.linkllet.core.ui.LnkSnackbarHost
import com.linkedlist.linkllet.navigation.LnkNavHost
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LnkApp(
    appState: LnkAppState = rememberLnkAppState(),
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val height = LocalConfiguration.current.screenHeightDp

    Scaffold(
        snackbarHost = {
            SnackbarHost(
                modifier = Modifier
                    .padding(bottom = (height - 80).dp)
                    .height(60.dp)
                ,
                hostState = snackbarHostState,
                snackbar = {
                    Card(
                        modifier =Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = ColorCC000000
                        )
                    ) {
                        Box(
                            Modifier.fillMaxSize().padding(vertical = 16.dp, horizontal = 20.dp)
                        ) {
                            Text(
                                modifier = Modifier.align(Alignment.Center),
                                text = it.visuals.message,
                                color = Color.White,
                                style = Typography.bodySmall
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
                snackbarHostState.showSnackbar(
                    message = it,
                    duration = SnackbarDuration.Short,

                ) == SnackbarResult.ActionPerformed
            }
        )
    }

}