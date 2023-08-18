package com.linkedlist.linkllet.core.designsystem.icon.lnkicon

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.linkedlist.linkllet.core.designsystem.icon.LnkIcon

public val LnkIcon.Rotation: ImageVector
    get() {
        if (_rotation != null) {
            return _rotation!!
        }
        _rotation = Builder(name = "Rotation", defaultWidth = 30.0.dp, defaultHeight = 30.0.dp,
                viewportWidth = 30.0f, viewportHeight = 30.0f).apply {
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF000000)),
                    strokeLineWidth = 2.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(7.0f, 14.0f)
                lineTo(23.0f, 14.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 24.0f, 15.0f)
                lineTo(24.0f, 21.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 23.0f, 22.0f)
                lineTo(7.0f, 22.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 6.0f, 21.0f)
                lineTo(6.0f, 15.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 7.0f, 14.0f)
                close()
            }
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF000000)),
                    strokeLineWidth = 2.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(6.0f, 9.0f)
                horizontalLineTo(24.0f)
            }
        }
        .build()
        return _rotation!!
    }

private var _rotation: ImageVector? = null

@Composable
@Preview
fun RotationPreview() {
    Icon(imageVector = LnkIcon.Rotation, contentDescription = "")
}