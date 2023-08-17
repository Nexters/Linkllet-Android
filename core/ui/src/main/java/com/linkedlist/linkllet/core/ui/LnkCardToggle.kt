package com.linkedlist.linkllet.core.ui

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

/**
 * onChanged 함수가 호출되면 왼쪽이면 0, 오른쪽이면 1을 인자로 넘겨줍니다.
 */
@Composable
fun LnkCardToggle(
    modifier: Modifier = Modifier,
    onChanged: (Boolean) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }

    Button(onClick = {
        expanded = !expanded
        onChanged(expanded)
    }) {
        Text("토글")
    }
}