package com.example.gastrolab.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class RecipeModel(
    var id: Int = 0,
    var title: String = "",
    var description: String = "",
    var imageURL: String = "",
    var preparetime: String = "",
    var likerate: Int = 0,
    var category: String = "",
    var difficulty: String = ""
)



@Entity
data class RecipeEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo (name = "title") val title: String,
    @ColumnInfo (name = "description") val description: String,
    @ColumnInfo (name = "imageURL") val imageURL: String,
    @ColumnInfo (name = "preparetime") val preparetime: String,
    @ColumnInfo (name = "likerate") val likerate: Int,
    @ColumnInfo (name = "category") val category: String,
    @ColumnInfo (name = "difficulty") val difficulty: String,
)

fun RecipeModel.toRecipeEntity(): RecipeEntity {
    return RecipeEntity(
        id = this.id,
        title = this.title,
        description = this.description,
        imageURL = this.imageURL,
        preparetime = this.preparetime,
        likerate = this.likerate,
        category = this.category,
        difficulty = this.difficulty
    )
}