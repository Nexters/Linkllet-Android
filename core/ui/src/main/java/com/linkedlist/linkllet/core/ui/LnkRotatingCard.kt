package com.linkedlist.linkllet.core.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
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
fun LnkRotatingCard(
    modifier: Modifier,
    folders: List<FolderModel>,
    navigateToLinks: (Long, String, String) -> Unit,
) {
    val visibleSize = folders.size.coerceAtMost(3)
    val listState = rememberLazyListState()
    val currentIndex by remember { derivedStateOf { listState.firstVisibleItemIndex } }
    val offset by remember { derivedStateOf { listState.firstVisibleItemScrollOffset } }
    val minimumScale = 0.5f
    val pixelRatio = 2.625 // fixme 기기에서 받아서 사용하기

    Text("$currentIndex, $offset")
//    val animationDuration = 500

    LazyColumn(
        modifier = modifier,
        state = listState,
        verticalArrangement = Arrangement.spacedBy((-135).dp),
        reverseLayout = true,
    ) {
        items(folders.size) { index ->
            val folder = folders[index]
            LnkFolder(
                modifier = Modifier
                    .zIndex(-index.toFloat())
                    .graphicsLayer {
                        // currentIndex + 3 요소의 위치를 도착지점에 고정시키기
                        if(index == currentIndex + 3) {
                            val itemHeight = 65 * pixelRatio // 이만큼 미리 이동 시켜놓기
                            translationY = itemHeight.toFloat() - offset

                            val progress = offset / itemHeight
                            val scale = (0.5 * progress).toFloat() + minimumScale
                            scaleX = scale
                            scaleY = scale

                            alpha = progress.toFloat()
                        }
                    }
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
