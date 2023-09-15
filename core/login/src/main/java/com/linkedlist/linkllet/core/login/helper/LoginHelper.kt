package com.linkedlist.linkllet.core.login.helper

import android.content.Context

// 다른 소셜 로그인 추가를 고려하여 interface
interface LoginHelper {

    suspend fun login(
        context: Context
    ) : Result<String>

    suspend fun logout() : Result<Unit>
}