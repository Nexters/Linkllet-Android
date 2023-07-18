package com.linkedlist.linkllet.core.designsystem.icon.lnkicon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Round
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.linkedlist.linkllet.core.designsystem.icon.LnkIcon

public val LnkIcon.Clip: ImageVector
    get() {
        if (_clip != null) {
            return _clip!!
        }
        _clip = Builder(name = "Clip", defaultWidth = 36.0.dp, defaultHeight = 36.0.dp,
                viewportWidth = 36.0f, viewportHeight = 36.0f).apply {
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFFffffff)),
                    strokeLineWidth = 1.5f, strokeLineCap = Round, strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(13.9705f, 10.579f)
                lineTo(9.2198f, 17.4488f)
                curveTo(7.2463f, 20.3133f, 7.2245f, 23.8086f, 10.645f, 26.5694f)
                curveTo(14.0656f, 29.3302f, 18.0087f, 28.0484f, 19.9089f, 25.5834f)
                lineTo(27.035f, 15.7233f)
                curveTo(27.7476f, 14.4908f, 29.4104f, 11.7793f, 26.3224f, 9.0678f)
                curveTo(23.4553f, 6.5502f, 21.0966f, 8.5748f, 19.9089f, 10.0538f)
                curveTo(18.9588f, 11.237f, 15.5541f, 16.2985f, 13.9705f, 18.6813f)
                curveTo(13.4955f, 19.3387f, 12.8304f, 20.9491f, 13.9705f, 22.1323f)
                curveTo(15.1875f, 23.3952f, 17.0064f, 22.2134f, 17.5336f, 21.3928f)
                curveTo(18.0607f, 20.5723f, 20.384f, 17.3666f, 21.5717f, 15.7233f)
            }
        }
        .build()
        return _clip!!
    }

private var _clip: ImageVector? = null
