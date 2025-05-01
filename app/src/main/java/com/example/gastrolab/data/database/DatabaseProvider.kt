package com.example.clasetrabajo.data.database

import android.content.Context
import androidx.room.Room

object DatabaseProvider{
    private var instance: AppDatabase? = null

    fun getDatabase(context: Context): AppDatabase{
        return instance ?: synchronized(this){
            val db = Room.databaseBuilder(
                context.applicationContext,
                //if database does not exist, it will be created with the name
                //shown above between quotation marks
                AppDatabase::class.java, "app-db"
            ).build()
            instance = db
            db
        }
    }
}