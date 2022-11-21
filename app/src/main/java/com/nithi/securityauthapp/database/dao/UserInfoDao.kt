package com.example.secureauthentication.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nithi.securityauthapp.model.UserSettings

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(characters: UserSettings):Long

    @Query("SELECT * FROM user")
    fun getUserData(): UserSettings?


}
