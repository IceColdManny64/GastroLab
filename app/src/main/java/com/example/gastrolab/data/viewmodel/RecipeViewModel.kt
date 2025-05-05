package com.example.clasetrabajo.data.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clasetrabajo.data.network.RetrofitClient
import com.example.gastrolab.data.model.RecipeModel
import kotlinx.coroutines.launch
import retrofit2.Response

class RecipeViewModel: ViewModel() {
    val api = RetrofitClient.api

    fun getRecipes(onResult: (Response<List<RecipeModel>>) -> Unit){
        viewModelScope.launch{
            try {
                val response = api.getRecipes()
                Log.d("debug", response.toString())
                onResult(response)
            } catch (exception: Exception){
                Log.d("debug", "API ERROR: $exception")
            }
        }
    }
    fun getRecipe(id: Int, onResult:(Response<RecipeModel>) -> Unit ) {
        viewModelScope.launch {

            try {
                val response = api.getRecipe(id)
                Log.d("debug", response.toString())
                onResult(response)
            } catch (exception: Exception) {
                Log.d("debug", "API ERROR: $exception")
            }
        }
    }

}