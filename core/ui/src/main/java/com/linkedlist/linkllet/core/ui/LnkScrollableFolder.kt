package com.linkedlist.linkllet.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.linkedlist.linkllet.core.designsystem.icon.LnkIcon
import com.linkedlist.linkllet.core.designsystem.icon.lnkicon.Plus

// todo : model을 둘 위치 및 네이밍 컨벤션 논의 필요
data class FolderModel(
    val folderId: Long,
    val name: String,
    val totalItems: Int,
)

// todo : 디자인시스템에 컬러 추가 후 수정
private val colorMap = mapOf(
    0 to Color(0xFF779CFF),
    1 to Color(0xFF4F7EFE),
    2 to Color(0xFF3467F0),
)
private val addButtonBackground = Color(0xCCDAE3FB)

@Composable
fun LnkScrollableFolder(
    modifier: Modifier = Modifier,
    folders: List<FolderModel>,
    addFolder: () -> Unit,
    navigateToLinks: (Long,String) -> Unit,
) {
    Box(
        modifier = modifier
            .verticalScroll(rememberScrollState())
    ) {
        Surface(
            color = Color.Transparent,
            modifier = Modifier
                .fillMaxWidth()
                .height(165.dp)
                .clickable { addFolder() },
            shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(addButtonBackground)
                    .padding(top = 18.dp),
                contentAlignment = Alignment.TopCenter
            ) {
                Icon(
                    modifier = Modifier.size(28.dp),
                    imageVector = LnkIcon.Plus,
                    contentDescription = "폴더 추가",
                    tint = Color(0xFF779CFF),
                )
            }
        }

        RecursiveLnkScrollableFolder(
            modifier = Modifier.padding(top = 65.dp),
            folders = folders,
            colorKey = 0,
            navigateToLinks = navigateToLinks,
        )
    }
}

@Composable
internal fun RecursiveLnkScrollableFolder(
    modifier: Modifier = Modifier,
    folders: List<FolderModel>,
    colorKey: Int,
    navigateToLinks: (Long,String) -> Unit,
) {
    if (folders.isEmpty()) return

    val currentFolder = folders.first()

    Box(modifier.clickable {
        navigateToLinks(currentFolder.folderId,currentFolder.name)
    }) {
        LnkFolder(
            color = colorMap[colorKey] ?: Color.Gray,
            name = currentFolder.name,
            totalItems = currentFolder.totalItems,
        )
        if (folders.size > 1) {
            RecursiveLnkScrollableFolder(
                modifier = Modifier.padding(top = 65.dp),
                folders = folders.subList(1, folders.size),
                colorKey = (colorKey + 1) % 3,
                navigateToLinks = navigateToLinks,
            )
        }
    }
}

@Preview
@Composable
private fun LnkScrollableFolderPreview() {
    val sampleList = listOf(
        FolderModel(name = "기본", totalItems = 10, folderId = 1),
        FolderModel(name = "폴더1", totalItems = 11, folderId = 2),
        FolderModel(name = "폴더2", totalItems = 12, folderId = 3),
    )

    LnkScrollableFolder(folders = sampleList, addFolder = {}, navigateToLinks = {_,_ ->})
}