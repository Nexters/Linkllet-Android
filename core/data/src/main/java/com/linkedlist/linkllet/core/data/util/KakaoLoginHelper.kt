package com.linkedlist.linkllet.core.data.util

import android.content.Context
import com.kakao.sdk.user.UserApiClient

object KakaoLoginHelper {

    fun kakaoLogin(context : Context) {
        UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
            //TODO
        }
    }

    fun kakaoLogout(context : Context) {
        UserApiClient.instance.logout {
            //TODO
        }
    }
}