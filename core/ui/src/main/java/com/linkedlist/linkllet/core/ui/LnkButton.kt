package com.linkedlist.linkllet.core.ui

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.linkedlist.linkllet.core.designsystem.theme.ColorC0C0C0
import com.linkedlist.linkllet.core.designsystem.theme.ColorE9E9E9
import com.linkedlist.linkllet.core.designsystem.theme.Typography


@Composable
fun LnkButtonWithMargin(
    modifier: Modifier = Modifier,
    text: String,
    buttonColor : Color = Color.Black,
    textColor : Color = Color.White,
    unemphasizedButtonColor : Color = ColorE9E9E9,
    unemphasizedTextColor : Color = ColorC0C0C0,
    isEmphasized : Boolean = true,
    enabled : Boolean = true,
    horizontalMargin: Dp = 24.dp,
    verticalMargin: Dp = 14.dp,
    pressedHorizontalMargin: Dp = 30.dp,
    pressedVerticalMargin: Dp = 17.dp,
    onClick: () -> Unit
) {
    var isButtonClicked by remember { mutableStateOf(false) }

    val horizon by animateDpAsState(
        targetValue = if (isButtonClicked) pressedHorizontalMargin else horizontalMargin
    )
    val vertical by animateDpAsState(
        targetValue = if (isButtonClicked) pressedVerticalMargin else verticalMargin
    )

    Box(
        modifier = modifier
            .padding(
                horizontal = horizon,
                vertical = vertical
            )
    ) {
        LnkButton(
            modifier = modifier,
            onClick = onClick,
            text = text,
            buttonColor = if(isEmphasized) buttonColor else unemphasizedButtonColor ,
            textColor = if(isEmphasized) textColor else unemphasizedTextColor,
            onPressing = {
                isButtonClicked = true
            },
            onPressed = {
                isButtonClicked = false
            }
        )
    }
}

@Composable
fun LnkButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    buttonColor : Color,
    text: String,
    textColor : Color,
    enabled : Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    onPressing: () -> Unit = {},
    onPressed: () -> Unit = {}
) {
    val isPressed by interactionSource.collectIsPressedAsState()

    val verticalPadding by animateDpAsState(
        targetValue = if (isPressed) 13.dp else 16.dp
    )

    LaunchedEffect(isPressed) {
        if (isPressed) onPressing()
        else onPressed()
    }

    Button(
        modifier = modifier,
        interactionSource = interactionSource,
        shape = RoundedCornerShape(12.dp),
        contentPadding = PaddingValues(vertical = verticalPadding),
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonColor
        ),
        enabled = enabled,
        onClick = {
            onClick()
        },
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                style = Typography.bodyMedium,
                text = text,
                color = textColor
            )
        }
    }
}