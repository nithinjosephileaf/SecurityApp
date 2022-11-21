package com.nithi.securityauthapp.di

import android.content.Context
import com.example.secureauthentication.database.AuthDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context): AuthDatabase = AuthDatabase.getDatabase(appContext)
    @Singleton
    @Provides
    fun provideUserInfoDao(db: AuthDatabase) = db.userDao()
}