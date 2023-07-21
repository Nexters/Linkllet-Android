package com.linkedlist.linkllet.core.designsystem.icon.lnkicon

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Round
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.linkedlist.linkllet.core.designsystem.icon.LnkIcon

public val LnkIcon.Settings: ImageVector
    get() {
        if (_settings != null) {
            return _settings!!
        }
        _settings = Builder(name = "Settings", defaultWidth = 40.0.dp, defaultHeight = 40.0.dp,
                viewportWidth = 40.0f, viewportHeight = 40.0f).apply {
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF000000)),
                    strokeLineWidth = 2.0f, strokeLineCap = Round, strokeLineJoin =
                    StrokeJoin.Companion.Round, strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(20.0f, 23.0f)
                curveTo(21.7f, 23.0f, 23.0f, 21.7f, 23.0f, 20.0f)
                curveTo(23.0f, 18.3f, 21.7f, 17.0f, 20.0f, 17.0f)
                curveTo(18.3f, 17.0f, 17.0f, 18.3f, 17.0f, 20.0f)
                curveTo(17.0f, 21.7f, 18.3f, 23.0f, 20.0f, 23.0f)
                close()
            }
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF000000)),
                    strokeLineWidth = 2.0f, strokeLineCap = Round, strokeLineJoin =
                    StrokeJoin.Companion.Round, strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(21.0001f, 12.1999f)
                curveTo(20.5001f, 13.2999f, 19.4001f, 13.2999f, 18.9001f, 12.1999f)
                curveTo(17.9001f, 10.3999f, 16.1001f, 10.9999f, 14.7001f, 11.9999f)
                curveTo(13.4001f, 12.6999f, 13.2001f, 14.3999f, 14.1001f, 15.4999f)
                curveTo(14.6001f, 16.3999f, 13.7001f, 17.1999f, 12.9001f, 16.9999f)
                curveTo(11.6001f, 16.9999f, 10.6001f, 18.0999f, 10.6001f, 19.2999f)
                curveTo(10.5001f, 20.9999f, 10.8001f, 22.8999f, 12.9001f, 22.9999f)
                curveTo(13.8001f, 22.7999f, 14.6001f, 23.5999f, 14.1001f, 24.4999f)
                curveTo(13.3001f, 25.5999f, 13.5001f, 27.2999f, 14.7001f, 27.9999f)
                curveTo(16.1001f, 28.9999f, 17.9001f, 29.5999f, 19.0001f, 27.7999f)
                curveTo(19.5001f, 26.6999f, 20.6001f, 26.6999f, 21.1001f, 27.7999f)
                curveTo(22.2001f, 29.5999f, 24.0001f, 28.9999f, 25.4001f, 27.9999f)
                curveTo(26.7001f, 27.2999f, 26.9001f, 25.5999f, 26.0001f, 24.4999f)
                curveTo(25.5001f, 23.5999f, 26.4001f, 22.7999f, 27.2001f, 22.9999f)
                curveTo(28.5001f, 22.9999f, 29.5001f, 21.8999f, 29.5001f, 20.6999f)
                curveTo(29.6001f, 18.9999f, 29.3001f, 17.0999f, 27.2001f, 16.9999f)
                curveTo(26.3001f, 17.1999f, 25.5001f, 16.3999f, 26.0001f, 15.4999f)
                curveTo(26.8001f, 14.3999f, 26.6001f, 12.6999f, 25.4001f, 11.9999f)
                curveTo(23.9001f, 10.9999f, 22.1001f, 10.3999f, 21.0001f, 12.1999f)
                close()
            }
        }
        .build()
        return _settings!!
    }

private var _settings: ImageVector? = null

@Composable
@Preview
fun SettingsPreview() {
    Icon(imageVector = LnkIcon.Settings, contentDescription = "")
}