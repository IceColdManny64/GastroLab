package com.example.clasetrabajo.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.clasetrabajo.data.dao.RecipeDao
import com.example.gastrolab.data.model.RecipeEntity


@Database(entities = [RecipeEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao

}