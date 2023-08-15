package com.linkedlist.linkllet.core.ui

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.linkedlist.linkllet.core.designsystem.theme.blue400
import com.linkedlist.linkllet.core.designsystem.theme.blue500
import com.linkedlist.linkllet.core.designsystem.theme.blue600

private val colorMap = mapOf(
    0 to blue400,
    1 to blue500,
    2 to blue600,
)

@Composable
fun LnkExpandedCard(
    modifier: Modifier,
    folders: List<FolderModel>,
    navigateToLinks: (Long, String, String) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    val animationDuration = 500
    val offsetAnimation: Dp by animateDpAsState(
        if (expanded) (-135).dp else (-200).dp, tween(animationDuration), label = ""
    )

    LaunchedEffect(Unit) {
        expanded = true
    }

    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(offsetAnimation),
        reverseLayout = true,
    ) {
        items(folders.size) { index ->
            val folder = folders[index]
            LnkFolder(
                modifier = Modifier
                    .zIndex(-index.toFloat())
                    .clickable {
                        navigateToLinks(folder.folderId, folder.name, folder.type)
                    },
                color = colorMap[index % 3]!!,
                name = folder.name,
                totalItems = folder.totalItems
            )
        }
    }
}