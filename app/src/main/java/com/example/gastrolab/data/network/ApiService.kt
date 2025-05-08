package com.example.clasetrabajo.data.network

import com.example.gastrolab.data.model.LoginModel
import com.example.gastrolab.data.model.RecipeModel
import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @POST("login.php") //POST login
    suspend fun login(@Body user: LoginModel): Response<JsonObject>

    @POST("register.php") //POST login create account
    suspend fun loginCreate(@Body user: LoginModel): Response<JsonObject>

    @GET("recipe.php") //get all recipes
    suspend fun getRecipes(): Response<List<RecipeModel>>

    @GET("recipe.php")//get one recipe
    suspend fun getRecipe(@Query("id") id: Int): Response<RecipeModel>

    @POST("updateCredentials.php")
    suspend fun verifyEmail(@Body request: JsonObject): Response<JsonObject>

    @POST("updateCredentials.php")
    suspend fun updateCredentials(@Body request: JsonObject): Response<JsonObject>

    @POST("updateCredentials.php")
    suspend fun verifyCurrentPassword(@Body request: JsonObject): Response<JsonObject>

}
