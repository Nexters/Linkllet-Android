package com.linkedlist.linkllet.core.login

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.linkedlist.linkllet.core.login.di.DataStoreModule.LoginDataStore
import com.linkedlist.linkllet.core.login.entity.UserData
import com.linkedlist.linkllet.core.login.helper.KakaoLoginHelper
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private const val LOGIN_TYPE = "login_type"


enum class LoginType {
    KAKAO, GUEST, LOGOUT
}

class LoginManager @Inject constructor(
    @LoginDataStore private val preferenceDataStore: DataStore<Preferences>,
    @ApplicationContext private val applicationContext: Context,
    private val kakaoLoginHelper: KakaoLoginHelper
) {
    private val loginTypePreferencesKey = stringPreferencesKey(LOGIN_TYPE)
    private var user : UserData? = null

    val deviceId: String get() = user?.id ?: ""
    val isLoggedIn : Boolean get() = user != null
    fun getLoginType() : Flow<LoginType> =
        preferenceDataStore.data.map { preferences ->
            LoginType.valueOf(preferences[loginTypePreferencesKey] ?: LoginType.LOGOUT.name)
        }


    suspend fun setLoginState(loginType: LoginType) {
        preferenceDataStore.edit { preferences ->
            preferences[loginTypePreferencesKey] = loginType.name
        }
    }

    suspend fun loginWithKakao(
        context : Context
    ) : Flow<Result<Unit>> = flow {
        kakaoLoginHelper.login(context = context)
            .onSuccess {
                user = UserData(it,LoginType.KAKAO)
                setLoginState(LoginType.KAKAO)
                emit(Result.success(Unit))
            }.onFailure {
                emit(Result.failure(it))
            }
    }

    @SuppressLint("HardwareIds")
    suspend fun loginAsGuest() : Flow<Result<Unit>> = flow {
        try {
            user = UserData(
                Settings.Secure.getString(applicationContext.contentResolver, Settings.Secure.ANDROID_ID),
                LoginType.GUEST
            )
            setLoginState(LoginType.GUEST)
            emit(Result.success(Unit))
        }catch (e:Exception){
            emit(Result.failure(e))
        }
    }

    fun logout() : Flow<Unit> = flow {
        if(user?.type == LoginType.KAKAO){
            kakaoLoginHelper.logout().onSuccess {
                clearCachedUserData()
                emit(Unit)
            }
        }else {
            clearCachedUserData()
            emit(Unit)
        }
    }

    private suspend fun clearCachedUserData() {
        user = null
        setLoginState(LoginType.LOGOUT)
    }
}