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
    titleLarge = TextStyle(
        fontSize = 16.sp,
        fontFamily = pretendard,
        fontWeight = FontWeight.Bold,
        color = Color.Black,
        lineHeight = 19.sp,
    ),
    bodyLarge = TextStyle(
        fontSize = 16.sp,
        fontFamily = pretendard,
        fontWeight = FontWeight.Medium,
        color = Color.Black,
        lineHeight = 18.sp,
    ),
    titleMedium = TextStyle(
        fontSize = 14.sp,
        fontFamily = pretendard,
        fontWeight = FontWeight.Bold,
        color = Color.Black,
        lineHeight = 18.sp,
        letterSpacing = (-0.28).sp,
    ),
    bodyMedium = TextStyle(
        fontSize = 14.sp,
        fontFamily = pretendard,
        fontWeight = FontWeight.Medium,
        color = Color.Black,
        lineHeight = 18.sp,
        letterSpacing = (-0.28).sp,
    ),
    labelMedium = TextStyle(
        fontSize = 14.sp,
        fontFamily = pretendard,
        fontWeight = FontWeight.Medium,
        color = Gray400,
        lineHeight = 18.sp,
        letterSpacing = (-0.28).sp,
    ),
    bodySmall = TextStyle(
        fontSize = 12.sp,
        fontFamily = pretendard,
        fontWeight = FontWeight.Medium,
        color = Color.White,
        lineHeight = 14.sp,
    ),
    labelSmall = TextStyle(
        fontSize = 12.sp,
        fontFamily = pretendard,
        fontWeight = FontWeight.Medium,
        color = Color7B7B7B,
        lineHeight = 14.sp,
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