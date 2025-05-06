package com.example.clasetrabajo.data.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clasetrabajo.data.network.RetrofitClient
import com.example.gastrolab.data.model.LoginModel
import com.google.gson.JsonObject
import kotlinx.coroutines.launch

//this class communicates with the API and receives a response
class LoginViewModel: ViewModel() {
    val api = RetrofitClient.api
    //the question mark indicates that the function may receive a JsonObject or may not
    //the lack of it could result in the program having critical problems for not receiving the expected
    //object
    fun loginAPI(login_model: LoginModel, onResult: (JsonObject?) -> Unit){
        viewModelScope.launch{
            try {
                val response = api.login(login_model)
                if(response.isSuccessful){
                    val jsonResponse = response.body()
                    Log.d("debug", "${response.body()}" /* or jsonResponse.toString()*/)
                    onResult(jsonResponse)
                }else{
                    Log.d("debug", "ERROR: ${response.body()}")
                    onResult(null)
                }
            }catch (exception: Exception){
                Log.d("debug", "API CALL FAILED: $exception")
                onResult(null)
            }
        }
    }
    fun createUser(login_model: LoginModel, onResult: (Response:JsonObject?) -> Unit){
        viewModelScope.launch{
            try {
                val response = api.loginCreate(login_model)
                if(response.isSuccessful){
                    val jsonResponse = response.body()
                    Log.d("debug", "$jsonResponse" /* or jsonResponse.toString()*/)
                    onResult(jsonResponse)
                }else{
                    Log.d("debug", "ERROR: ${response.errorBody()?.string()}")
                    onResult(null)
                }
            }catch (exception: Exception){
                Log.d("debug", "API CALL FAILED: $exception")
                onResult(null)
            }
        }
    }
}