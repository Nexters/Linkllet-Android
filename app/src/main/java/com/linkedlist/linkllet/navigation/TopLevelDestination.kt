package com.linkedlist.linkllet.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.ui.graphics.vector.ImageVector

// TODO : 디자인이 확정되면 icon 및 네비게이션 정보가 수정되어야 합니다.
enum class TopLevelDestination(
    val icon: ImageVector? = null,
) {
    HOME(
        icon = Icons.Rounded.Home
    ),
    SETTINGS(
        icon = Icons.Rounded.Settings
    ),
    ADD_LINK(
        icon = Icons.Rounded.Add
    ),
    FOLDER(),
    LINK_LIST(),
    ADD_FOLDER(),
}