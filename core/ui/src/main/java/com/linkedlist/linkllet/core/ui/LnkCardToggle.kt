package com.linkedlist.linkllet.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.linkedlist.linkllet.core.designsystem.icon.LnkIcon
import com.linkedlist.linkllet.core.designsystem.icon.lnkicon.List
import com.linkedlist.linkllet.core.designsystem.icon.lnkicon.Rotation
import com.linkedlist.linkllet.core.designsystem.theme.Gray100
import com.linkedlist.linkllet.core.designsystem.theme.Gray400

/**
 * onChanged 함수가 호출되면 왼쪽이면 0, 오른쪽이면 1을 인자로 넘겨줍니다.
 */
@Composable
fun LnkCardToggle(
    modifier: Modifier = Modifier,
    onChanged: (Boolean) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }

    Surface(
        modifier = modifier
            .width(70.dp)
            .height(40.dp),
        shape = RoundedCornerShape(6.dp),
    ) {
        Box(
            modifier = Modifier
                .background(color = Gray100)
                .fillMaxHeight(),
            contentAlignment = Alignment.Center,
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
            ) {
                RoundedIcon(
                    imageVector = LnkIcon.Rotation,
                    contentDescription = "회전식 보기",
                    onClick = {
                        expanded = false
                        onChanged(false)
                    },
                    selected = !expanded,
                )
                RoundedIcon(
                    imageVector = LnkIcon.List,
                    contentDescription = "펼쳐진 보기",
                    onClick = {
                        expanded = true
                        onChanged(true)
                    },
                    selected = expanded,
                )
            }
        }
    }
}

@Composable
internal fun RoundedIcon(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    contentDescription: String,
    onClick: () -> Unit,
    selected: Boolean,
) {
    Surface(
        shape = RoundedCornerShape(5.dp),
        // todo : 그림자 넣기
    ) {
        Icon(
            modifier = Modifier
                .background(color = if (selected) Color.White else Gray100)
                .clickable { onClick() },
            imageVector = imageVector,
            contentDescription = contentDescription,
            tint = if (selected) Color.Black else Gray400,
        )
    }
}
