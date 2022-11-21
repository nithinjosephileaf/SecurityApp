package com.nithi.securityauthapp.view.viewmodel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nithi.securityauthapp.repository.AuthRepository
import com.nithi.securityauthapp.utility.ResponseState
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AuthViemodel @Inject constructor(private val repository: AuthRepository) :ViewModel() {


    private val _userDate=MutableStateFlow<ResponseState<Boolean>?>(null)
    val userData=_userDate.asStateFlow()
    @RequiresApi(Build.VERSION_CODES.M)
    fun saveUserDetail(user:String,userName:String,password:String,){
        viewModelScope.launch(Dispatchers.IO) {
            repository.saveUserDetails(user,userName,password).collect{
               _userDate.value=it
            }

        }
    }
    @RequiresApi(Build.VERSION_CODES.M)
    fun checkUser(userName:String,password: String){
        viewModelScope.launch(Dispatchers.IO) {
            repository.signInUser(userName,password).collect{
                _userDate.value=it
            }
        }

    }
}