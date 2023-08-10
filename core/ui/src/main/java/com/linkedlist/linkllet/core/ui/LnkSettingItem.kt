package com.linkedlist.linkllet.core.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.linkedlist.linkllet.core.designsystem.icon.LnkIcon
import com.linkedlist.linkllet.core.designsystem.icon.lnkicon.ArrowRight
import com.linkedlist.linkllet.core.designsystem.theme.Gray600

@Composable
fun SettingItem(
    modifier: Modifier = Modifier,
    name: String,
    onClick: () -> Unit,
    active: Boolean = true,
) {
    Row(modifier = modifier
        .padding(horizontal = 24.dp)
        .clickable { onClick() }
        .height(48.dp)
        .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        val color = if(active) Color.Black else Gray600

        Text(name, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = color)
        Icon(
            modifier = Modifier.size(16.dp),
            imageVector = LnkIcon.ArrowRight,
            contentDescription = name,
            tint = color
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SettingItemPreview() {
    SettingItem(name = "현재 버전", onClick = {})
}