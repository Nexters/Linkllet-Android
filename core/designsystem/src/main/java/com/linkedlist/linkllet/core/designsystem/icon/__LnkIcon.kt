package com.linkedlist.linkllet.core.designsystem.icon

import androidx.compose.ui.graphics.vector.ImageVector
import com.linkedlist.linkllet.core.designsystem.icon.lnkicon.BtnRadio
import com.linkedlist.linkllet.core.designsystem.icon.lnkicon.IcDropdownArrow
import kotlin.collections.List as ____KtList

public object LnkIcon

private var __LnkIcons: ____KtList<ImageVector>? = null

public val LnkIcon.LnkIcons: ____KtList<ImageVector>
  get() {
    if (__LnkIcons != null) {
      return __LnkIcons!!
    }
    __LnkIcons= listOf(BtnRadio, IcDropdownArrow)
    return __LnkIcons!!
  }
