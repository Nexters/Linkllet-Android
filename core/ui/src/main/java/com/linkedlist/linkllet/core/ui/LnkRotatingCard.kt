package com.linkedlist.linkllet.core.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.SnapLayoutInfoProvider
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LnkRotatingCard(
    modifier: Modifier,
    folders: List<FolderModel>,
    navigateToLinks: (Long, String, String) -> Unit,
) {
    // todo : 원형큐처럼 돌리기
    val visibleSize = folders.size.coerceAtMost(3) // todo : 폴더 수가 3개 미만일 때 처리하기
    val listState = rememberLazyListState()
    val currentIndex by remember { derivedStateOf { listState.firstVisibleItemIndex } }
    val offset by remember { derivedStateOf { listState.firstVisibleItemScrollOffset } }
    val minimumScale = 0.5f

    val snappingLayout = remember(listState) { SnapLayoutInfoProvider(listState) }
    val snapFlingBehavior = rememberSnapFlingBehavior(snappingLayout)

    LazyColumn(
        modifier = modifier,
        state = listState,
        verticalArrangement = Arrangement.spacedBy((-135).dp),
        reverseLayout = true,
        flingBehavior = snapFlingBehavior,
    ) {
        items(folders.size) { index ->
            val folder = folders[index]

            LnkFolder(
                modifier = Modifier
                    .zIndex(-index.toFloat())
                    .graphicsLayer {
                        val itemHeight = folderHeight.dp.toPx()
                        val marginHeight = 65.dp.toPx()
                        val progress = offset / marginHeight

                        // fixme : 아예 렌더링 안 하는 게 가장 좋을 것 같다. 왠지 모르겠지만 아예 렌더링 안 하면 화면이 이상해짐
                        // fixme : 투명해진 카드가 클릭되는 것을 방지하기 위함
                        if (index < currentIndex) {
                            alpha = 0f
                            translationY = itemHeight
                        }

                        // offset이 증가함에 따라 사라져야 한다.
                        if (index == currentIndex) {
                            alpha = 1 - progress
                        }

                        // offset이 증가함에 따라 나타나야 한다.
                        // currentIndex + 3 요소의 위치를 도착지점에 고정시키기
                        if (index == currentIndex + 3) {
                            // 도착지점에 미리 갖다 놓고, offset을 통해 스크롤이 내려가도 고정시키기
                            translationY = marginHeight - offset

                            val scale = (0.5 * progress).toFloat() + minimumScale
                            scaleX = scale
                            scaleY = scale

                            alpha = progress
                        }

                        if(index > currentIndex + 3) {
                            alpha = 0f
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
