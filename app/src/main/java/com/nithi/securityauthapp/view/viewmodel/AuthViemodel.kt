package com.nithi.securityauthapp.view.viewmodel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nithi.securityauthapp.repository.AuthRepository
import com.nithi.securityauthapp.utility.ResponseState
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AuthViemodel @Inject constructor(private val repository: AuthRepository) :ViewModel() {


    private val _userDate= MutableLiveData<ResponseState<Boolean>>()
    val userData:LiveData<ResponseState<Boolean>> = _userDate
    @RequiresApi(Build.VERSION_CODES.M)
    fun saveUserDetail(user:String,userName:String,password:String,){
        viewModelScope.launch {
            repository.saveUserDetails(user,userName,password).collect{
               _userDate.value=it
            }

        }
    }
    @RequiresApi(Build.VERSION_CODES.M)
    fun checkUser(userName:String,password: String){
        viewModelScope.launch{
            repository.signInUser(userName,password).collect{
                _userDate.value=it
            }
        }

    }



}