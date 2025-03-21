package com.example.gastrolab.ui.screens.LoginScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun LoginInterface(navController: NavHostController) {
    val primaryColor = MaterialTheme.colorScheme.primary

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(primaryColor)
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {

        Text(
            "Inicia Sesión",
            fontSize = 26.sp,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(12.dp))

        Text(
            "o únete a",
            fontSize = 20.sp,
            color = Color.White.copy(alpha = 0.8f)
        )

        Spacer(modifier = Modifier.height(20.dp))

        LoginButton(text = "Inicia sesión con Google")
        Spacer(modifier = Modifier.height(16.dp))

        LoginButton(text = "Inicia sesión con Apple")
        Spacer(modifier = Modifier.height(16.dp))

        LoginButton(text = "Inicia sesión con Facebook")
        Spacer(modifier = Modifier.height(24.dp))


        Divider(color = Color.White)
        Spacer(modifier = Modifier.height(24.dp))

        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var rememberPass by remember { mutableStateOf(false) }

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email o teléfono") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = rememberPass, onCheckedChange = { rememberPass = it })
            Text("Recordarme", color = Color.White , modifier = Modifier.padding(start = 8.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text("Más información", color = MaterialTheme.colorScheme.error, fontSize = 12.sp)
        }
        Spacer(modifier = Modifier.height(8.dp))


        Text(
            text = "¿Has olvidado tu contraseña?",
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
            fontSize = 14.sp,
            modifier = Modifier
                .clickable { navController.navigate("loginPasswordScreen") }
                .padding(8.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { navController.navigate("loginPasswordScreen") }
        )
        {
            Text("Contraseña olvidada")

        }
        Button(
            onClick = { navController.navigate("signUpScreen") }
        )
        {
            Text("Registrarse")
        }
        Button(
            onClick = { navController.navigate("mainScreen") }
        )
        {
            Text("Pantalla principal")
        }
    }
}

@Composable
fun LoginButton(text: String) {
    OutlinedButton(
        onClick = {  },
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)

    ) {

        Text(
            text = text,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}

