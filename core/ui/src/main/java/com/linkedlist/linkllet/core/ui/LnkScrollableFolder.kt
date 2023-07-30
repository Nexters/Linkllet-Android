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
import com.linkedlist.linkllet.core.designsystem.theme.blue400
import com.linkedlist.linkllet.core.designsystem.theme.blue500
import com.linkedlist.linkllet.core.designsystem.theme.blue600

// todo : model을 둘 위치 및 네이밍 컨벤션 논의 필요
data class FolderModel(
    val folderId: Long,
    val name: String,
    val type : String,
    val totalItems: Int,
)

// todo : 디자인시스템에 컬러 추가 후 수정
private val colorMap = mapOf(
    0 to blue400,
    1 to blue500,
    2 to blue600,
)
private val addButtonBackground = Color(0xCCDAE3FB)

@Composable
fun LnkScrollableFolder(
    modifier: Modifier = Modifier,
    folders: List<FolderModel>,
    addFolder: () -> Unit,
    navigateToLinks: (Long, String,String) -> Unit,
) {
    Box(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(top = 240.dp)
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

        LnkScrollableFolders(
            modifier = Modifier.padding(top = 65.dp),
            folders = folders,
            navigateToLinks = navigateToLinks,
        )
    }
}

@Composable
internal fun LnkScrollableFolders(
    modifier: Modifier = Modifier,
    folders: List<FolderModel>,
    navigateToLinks: (Long, String,String) -> Unit,
) {
    if (folders.isEmpty()) return

    Box(modifier = modifier) {
        folders.forEachIndexed { index, it ->
            LnkFolder(
                Modifier
                    .padding(top = 65.dp * index)
                    .clickable {
                    navigateToLinks(it.folderId, it.name,it.type)
                },
                color = colorMap[index % 3] ?: Color.Gray,
                name = it.name,
                totalItems = it.totalItems,
            )
        }
    }
}

@Preview
@Composable
private fun LnkScrollableFolderPreview() {
    val sampleList = listOf(
        FolderModel(name = "기본", totalItems = 10, folderId = 1, type = "DEFAULT"),
        FolderModel(name = "폴더1", totalItems = 11, folderId = 2, type = "PERSONALIZED"),
        FolderModel(name = "폴더2", totalItems = 12, folderId = 3, type = "PERSONALIZED"),
    )

    LnkScrollableFolder(folders = sampleList, addFolder = {}, navigateToLinks = { _, _,_ -> })
}