package com.nithi.securityauthapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "user")
data class UserSettings(

    @PrimaryKey(autoGenerate = true)
    var id:Int?=null,
    var user:String,
    val userName: String,
    val password:String,
    val userDetailIv:String
)
