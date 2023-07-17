package com.linkedlist.linkllet.core.designsystem.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.linkedlist.linkllet.core.designsystem.R

// Set of Material typography styles to start with
val Typography = Typography(

    titleMedium = TextStyle(
        fontSize = 14.sp,
        fontFamily = pretendard,
        fontWeight = FontWeight(700),
        color = Color.Black,
    ),
    bodyMedium = TextStyle(
        fontSize = 14.sp,
        fontFamily = pretendard,
        fontWeight = FontWeight(500),
        color = Color.Black,
    ),
    labelMedium = TextStyle(
        fontSize = 14.sp,
        fontFamily = pretendard,
        fontWeight = FontWeight(500),
        color = ColorBCBCBC
    ),
    bodySmall = TextStyle(
        fontSize = 12.sp,
        fontFamily = pretendard,
        fontWeight = FontWeight(500),
        color = Color(0xFFFFFFFF)
    ),
    labelSmall = TextStyle(
        fontSize = 12.sp,
        fontFamily = pretendard,
        fontWeight = FontWeight(500),
        color = Color7B7B7B,
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */

)