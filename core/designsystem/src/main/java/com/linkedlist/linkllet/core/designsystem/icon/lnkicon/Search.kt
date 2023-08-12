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
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.linkedlist.linkllet.core.designsystem.icon.LnkIcon

public val LnkIcon.Search: ImageVector
    get() {
        if (_search != null) {
            return _search!!
        }
        _search = Builder(name = "Search", defaultWidth = 40.0.dp, defaultHeight = 40.0.dp,
                viewportWidth = 40.0f, viewportHeight = 40.0f).apply {
            group {
                path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF000000)),
                        strokeLineWidth = 2.0f, strokeLineCap = Round, strokeLineJoin =
                        StrokeJoin.Companion.Round, strokeLineMiter = 4.0f, pathFillType =
                        NonZero) {
                    moveTo(18.5f, 26.0f)
                    curveTo(22.6421f, 26.0f, 26.0f, 22.6421f, 26.0f, 18.5f)
                    curveTo(26.0f, 14.3579f, 22.6421f, 11.0f, 18.5f, 11.0f)
                    curveTo(14.3579f, 11.0f, 11.0f, 14.3579f, 11.0f, 18.5f)
                    curveTo(11.0f, 22.6421f, 14.3579f, 26.0f, 18.5f, 26.0f)
                    close()
                }
                path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF000000)),
                        strokeLineWidth = 2.0f, strokeLineCap = Round, strokeLineJoin =
                        StrokeJoin.Companion.Round, strokeLineMiter = 4.0f, pathFillType =
                        NonZero) {
                    moveTo(24.0f, 24.0f)
                    lineTo(30.0f, 30.0f)
                }
            }
        }
        .build()
        return _search!!
    }

private var _search: ImageVector? = null

@Composable
@Preview
fun SearchPreview() {
    Icon(imageVector = LnkIcon.Search, contentDescription = "")
}