package com.nithi.securityauthapp.di

import com.example.secureauthentication.utils.Decryptor
import com.example.secureauthentication.utils.Encryptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object EncryptionModule {
    @Provides
    fun provideEncryptor():Encryptor{
        return Encryptor()
    }

    @Provides
    fun provideDecryptor():Decryptor{
        return Decryptor()
    }
}