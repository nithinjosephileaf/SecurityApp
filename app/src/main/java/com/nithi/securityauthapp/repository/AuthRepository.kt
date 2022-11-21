package com.nithi.securityauthapp.repository

import android.app.Application
import android.os.Build
import android.util.Base64
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.secureauthentication.database.dao.UserDao
import com.example.secureauthentication.utils.Decryptor
import com.example.secureauthentication.utils.Encryptor
import com.nithi.securityauthapp.R
import com.nithi.securityauthapp.model.UserSettings
import com.nithi.securityauthapp.utility.PreferenceManager
import com.nithi.securityauthapp.utility.ResponseState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.math.log

@RequiresApi(Build.VERSION_CODES.M)
class AuthRepository @Inject constructor(
    private val encryptor: Encryptor,
    private val decryptor: Decryptor,
    val applicationContext: Application,
    private val pref: PreferenceManager
) {

   suspend fun saveUserDetails(
        user: String,
        userName: String,
        password: String): Flow<ResponseState<Boolean>> {
        return flow<ResponseState<Boolean>> {
            try {
                val (encryptUserDetail, iv) = encryptString(password)
                pref.encryptedData(encryptUserDetail)
                pref.saveUserName(userName)
                pref.saveName(user)
                pref.setIv(iv);

                emit(ResponseState.Success(true))

            } catch (e: Exception) {
                emit(ResponseState.Failed(e))
            }


        }.flowOn(Dispatchers.IO)

    }



    fun signInUser(userName: String, password: String):Flow<ResponseState<Boolean>> {
        return flow<ResponseState<Boolean>> {
            if(!pref.getEncrypte().isNullOrEmpty()){
                val decryptedstring=decryptString(pref.getEncrypte()!!,pref.getIv()!!)
                val  user=pref.getUserName()
                if (user.equals(userName) && password.equals(decryptedstring)){
                   emit(ResponseState.Success(true))
                }else{
                    emit(ResponseState.Success(false))
                }
            }else{
                emit(ResponseState.Success(false))
            }
        }.flowOn(Dispatchers.IO)

    }

    private fun encryptString(value: String): Pair<String, String> {
        val byteArray = encryptor.encryptText(applicationContext.getString(R.string.alias), value)
        return Pair(
            Base64.encodeToString(byteArray, Base64.DEFAULT),
            Base64.encodeToString(encryptor.iv, Base64.DEFAULT)
        )
    }

    private fun decryptString(encryptedString: String, iv: String): String {
        return decryptor.decryptData(
            applicationContext.getString(R.string.alias),
            Base64.decode(encryptedString, Base64.DEFAULT),
            Base64.decode(iv, Base64.DEFAULT)
        )

    }
}