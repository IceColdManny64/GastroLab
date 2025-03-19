package com.example.gastrolab.ui.screens.LoginScreens

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text

@Composable
fun LoginInterface(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Button(
            onClick = {navController.navigate("loginPasswordScreen")}
        )
        {
            Text("Contraseña olvidada")
        }
        Button(
            onClick = {navController.navigate("signUpScreen")}
        )
        {
            Text("Registrarse")
        }
        Button(
            onClick = {navController.navigate("mainScreen")}
        )
        {
            Text("Pantalla principal")
        }
    }
}