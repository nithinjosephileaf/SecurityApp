package com.nithi.securityauthapp.utility

import android.app.Activity
import android.content.Intent
import android.widget.Toast

fun Activity.showToast(message:String){
    Toast.makeText(this,message, Toast.LENGTH_SHORT).show()
}
fun <T>Activity.startTransaction(name:Class<T>){
    startActivity(Intent(this,name::class.java))
}