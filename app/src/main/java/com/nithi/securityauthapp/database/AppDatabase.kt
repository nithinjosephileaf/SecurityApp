package com.example.secureauthentication.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.secureauthentication.database.dao.UserDao
import com.nithi.securityauthapp.model.UserSettings


@Database(
    entities = [
        UserSettings::class
    ],
    version = 1,
    exportSchema = false
)

abstract class AuthDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao



    companion object {
        @Volatile
        private var instance: AuthDatabase? = null

        fun getDatabase(context: Context): AuthDatabase =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AuthDatabase::class.java, "auth")
                .fallbackToDestructiveMigration()
                .build()
    }
}
