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
    fun getAll(): List<RecipeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(account: RecipeEntity)

    @Delete
    fun delete(account: RecipeEntity)
}