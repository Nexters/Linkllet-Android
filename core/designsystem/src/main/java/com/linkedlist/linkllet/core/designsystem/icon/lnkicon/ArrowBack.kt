package com.linkedlist.linkllet.core.designsystem.icon.lnkicon

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.linkedlist.linkllet.core.designsystem.icon.LnkIcon

public val LnkIcon.ArrowBack: ImageVector
    get() {
        if (_arrowBack != null) {
            return _arrowBack!!
        }
        _arrowBack = Builder(name = "ArrowBack", defaultWidth = 28.0.dp, defaultHeight = 28.0.dp, viewportWidth
                = 28.0f, viewportHeight = 28.0f).apply {
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF000000)),
                    strokeLineWidth = 2.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(18.0f, 6.0f)
                lineTo(10.0f, 14.0f)
                lineTo(18.0f, 22.0f)
            }
        }
        .build()
        return _arrowBack!!
    }

private var _arrowBack: ImageVector? = null

@Composable
@Preview
fun ArrowBackPreview() {
    Icon(imageVector = LnkIcon.ArrowBack, contentDescription = "")
}