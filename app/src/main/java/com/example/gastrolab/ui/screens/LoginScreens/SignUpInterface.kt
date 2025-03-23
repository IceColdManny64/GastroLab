package com.example.gastrolab.ui.screens.LoginScreens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpInterface(navController: NavHostController) {
    val primaryColor = MaterialTheme.colorScheme.primary
    val secondaryColor = MaterialTheme.colorScheme.secondary
    var emailOrUsername by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = primaryColor,
                modifier = Modifier
                    .size(28.dp)
                    .clickable { navController.navigate("loginScreen") }
            )

            Spacer(modifier = Modifier.size(10.dp))

            Text(
                text = "Regresar a las opciones de inicio de sesión",
                color = Color.Black,
                fontSize = 16.sp
            )
        }

        Spacer(modifier = Modifier.height(32.dp))



        OutlinedTextField(
            value = emailOrUsername,
            onValueChange = { emailOrUsername = it },
            label = { Text("Correo electrónico o nombre de usuario") },
            modifier = Modifier.fillMaxWidth(),

        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = if (isPasswordVisible) PasswordVisualTransformation() else androidx.compose.ui.text.input.VisualTransformation.None,
            trailingIcon = {
                Text(
                    text = if (isPasswordVisible) "ocultar" else "mostrar",
                    color = primaryColor,
                    modifier = Modifier.clickable { isPasswordVisible = !isPasswordVisible }
                )
            },

        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {

                println("Email/Username: $emailOrUsername, Password: $password")
            },
            modifier = Modifier.fillMaxWidth(),
            colors = androidx.compose.material3.ButtonDefaults.buttonColors(containerColor = primaryColor)
        ) {
            Text("Iniciar sesión", color = Color.White)
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "¿Has olvidado tu contraseña?",
            color = secondaryColor,
            fontSize = 14.sp,
            modifier = Modifier
                .clickable { navController.navigate("loginPasswordScreen") }
                .align(Alignment.CenterHorizontally)
        )
    }
}

