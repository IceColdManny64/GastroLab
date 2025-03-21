package com.example.gastrolab.ui.screens.MainScreens

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun SettingsInterface(navController: NavHostController) {
    Button(
        onClick = {navController.navigate("reportProblem")}
    )
    {
        Text("Go to Report Screen")
    }
}