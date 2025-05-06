package com.example.clasetrabajo.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.gastrolab.data.model.RecipeEntity

@Dao
interface RecipeDao {
    @Query("SELECT * FROM RecipeEntity")
    suspend fun getAll(): List<RecipeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(recipe: RecipeEntity)

    @Delete
    suspend fun delete(recipe: RecipeEntity)

    @Query("SELECT * FROM RecipeEntity WHERE id = :id")
    suspend fun getById(id: Int): RecipeEntity? // Nueva función
}