package com.nithi.securityauthapp

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.secureauthentication.utils.Decryptor
import com.example.secureauthentication.utils.Encryptor
import com.nithi.securityauthapp.repository.AuthRepository
import com.nithi.securityauthapp.utility.PreferenceManager
import com.nithi.securityauthapp.utility.ResponseState
import com.nithi.securityauthapp.view.viewmodel.AuthViemodel
import getOrAwaitValue
import junit.framework.TestCase
import kotlinx.coroutines.*
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AuthViewmodelTest : TestCase() {
    private lateinit var authViemodel: AuthViemodel
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var preferenceManager: PreferenceManager


    @Before
    public override fun setUp() {
        super.setUp()
        val context = ApplicationProvider.getApplicationContext<Context>()
         sharedPreferences = context.getSharedPreferences(
            "SECURE_PREF", Context.MODE_PRIVATE
        )
        preferenceManager= PreferenceManager(sharedPreferences)
        val authRepository = AuthRepository(
            encryptor = Encryptor(),
            decryptor = Decryptor(),
            Application(),
            preferenceManager
        )
        authViemodel= AuthViemodel(repository = authRepository)
    }
    @Test
    fun testDat() {
        GlobalScope.launch(Dispatchers.Main) {
            authViemodel.saveUserDetail("test","test1","123")
            val  result=authViemodel.userData.getOrAwaitValue().takeIf {
                when(it){
                    is ResponseState.Error -> TODO()
                    is ResponseState.Failed -> TODO()
                    is ResponseState.Success ->  it.data==true
                }
            }
            assert(result!=null)

        }


    }
}