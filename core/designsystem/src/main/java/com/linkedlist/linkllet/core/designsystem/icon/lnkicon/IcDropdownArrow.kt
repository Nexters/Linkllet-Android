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

public val LnkIcon.IcDropdownArrow: ImageVector
    get() {
        if (_icDropdownArrow != null) {
            return _icDropdownArrow!!
        }
        _icDropdownArrow = Builder(name = "IcDropdownArrow", defaultWidth = 14.0.dp, defaultHeight =
                8.0.dp, viewportWidth = 14.0f, viewportHeight = 8.0f).apply {
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF000000)),
                    strokeLineWidth = 2.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(13.0f, 7.0f)
                lineTo(7.0f, 2.0f)
                lineTo(1.0f, 7.0f)
            }
        }
        .build()
        return _icDropdownArrow!!
    }

private var _icDropdownArrow: ImageVector? = null

@Composable
@Preview
fun IcDropdownArrowPreview() {
    Icon(imageVector = LnkIcon.IcDropdownArrow, contentDescription = "")
}