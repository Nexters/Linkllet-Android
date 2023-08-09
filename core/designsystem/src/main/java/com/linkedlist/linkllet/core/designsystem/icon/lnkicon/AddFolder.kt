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

public val LnkIcon.AddFolder: ImageVector
    get() {
        if (_addFolder != null) {
            return _addFolder!!
        }
        _addFolder = Builder(name = "AddFolder", defaultWidth = 40.0.dp, defaultHeight = 40.0.dp,
                viewportWidth = 40.0f, viewportHeight = 40.0f).apply {
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF000000)),
                    strokeLineWidth = 2.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(10.0f, 13.5f)
                verticalLineTo(26.5f)
                curveTo(10.0f, 27.6046f, 10.8954f, 28.5f, 12.0f, 28.5f)
                horizontalLineTo(28.0f)
                curveTo(29.1046f, 28.5f, 30.0f, 27.6046f, 30.0f, 26.5f)
                verticalLineTo(15.8448f)
                curveTo(30.0f, 14.7403f, 29.1046f, 13.8448f, 28.0f, 13.8448f)
                horizontalLineTo(21.6769f)
                curveTo(21.1001f, 13.8448f, 20.5514f, 13.5958f, 20.1716f, 13.1617f)
                lineTo(19.3156f, 12.1832f)
                curveTo(18.9358f, 11.749f, 18.3871f, 11.5f, 17.8103f, 11.5f)
                horizontalLineTo(12.0f)
                curveTo(10.8954f, 11.5f, 10.0f, 12.3954f, 10.0f, 13.5f)
                close()
            }
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF000000)),
                    strokeLineWidth = 2.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(20.0f, 17.0f)
                lineTo(20.0f, 25.0f)
            }
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF000000)),
                    strokeLineWidth = 2.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(24.0f, 21.0f)
                lineTo(16.0f, 21.0f)
            }
        }
        .build()
        return _addFolder!!
    }

private var _addFolder: ImageVector? = null

@Composable
@Preview
fun ArrowFolderPreview() {
    Icon(imageVector = LnkIcon.AddFolder, contentDescription = "")
}