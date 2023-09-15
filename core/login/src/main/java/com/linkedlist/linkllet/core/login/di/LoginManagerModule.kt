package com.linkedlist.linkllet.core.login.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.linkedlist.linkllet.core.login.LoginManager
import com.linkedlist.linkllet.core.login.helper.KakaoLoginHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object LoginManagerModule {



    @Singleton
    @Provides
    fun provideLoginManager(
        @DataStoreModule.LoginDataStore preferenceDataStore: DataStore<Preferences>,
        @ApplicationContext applicationContext: Context,
        kakaoLoginHelper: KakaoLoginHelper
    ) : LoginManager {
        return LoginManager(
            preferenceDataStore,applicationContext, kakaoLoginHelper
        )
    }
}