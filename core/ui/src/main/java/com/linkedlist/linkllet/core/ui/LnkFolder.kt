package com.linkedlist.linkllet.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LnkFolder(
    modifier: Modifier = Modifier,
    color: Color,
    name: String,
    totalItems: Int,
) {
    val totalItemsBorder = Color(0x4dffffff)
    val totalItemsBackground = Color(0x33ffffff)

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(165.dp),
        shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp),
    ) {
        Row(
            Modifier
                .background(color = color)
                .fillMaxHeight()
                .padding(start = 28.dp, top = 26.dp, end = 32.dp)
        ) {
            Text(
                name,
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.weight(1.0f))
            Surface(
                shape = RoundedCornerShape(
                    topStart = 8.dp,
                    topEnd = 8.dp,
                    bottomStart = 8.dp,
                    bottomEnd = 8.dp
                ),
            ) {
                Box(
                    modifier = Modifier
                        .border(
                            width = 1.dp, color = totalItemsBorder,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .background(color = color),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        totalItems.toString(),
                        color = Color.White,
                        modifier = Modifier
                            .background(
                                color = totalItemsBackground
                            )
                            .padding(horizontal = 8.dp, vertical = 4.dp),
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}

@Composable
@Preview
fun LnkFolderPreview() {
    LnkFolder(
        color = Color.Gray,
        name = "기본",
        totalItems = 10,
    )
}