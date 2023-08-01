package com.linkedlist.linkllet.feature.link.model

import com.linkedlist.linkllet.core.data.model.Folder

data class FolderUiModel(
    val id : Long,
    val folderType : FolderType,
    val name: String = "",
    val isSelected: Boolean = false
)

enum class FolderType {
    DEFAULT, PERSONALIZED
}

fun Folder.toFolderUiModel(selected : Boolean = false) : FolderUiModel {
    return FolderUiModel(
        id = id,
        name = name,
        folderType = type.toFolderType(),
        isSelected = selected
    )
}

fun String?.toFolderType() : FolderType {
    return try {
        FolderType.valueOf(this ?: FolderType.DEFAULT.name)
    }catch (e: Exception){
        FolderType.DEFAULT
    }
}