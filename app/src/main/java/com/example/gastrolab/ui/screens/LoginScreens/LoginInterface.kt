package com.example.gastrolab.ui.screens.LoginScreens

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.TextStyle
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginInterface(navController: NavHostController) {
    val primaryColor = MaterialTheme.colorScheme.primary
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var isConfirmPasswordVisible by remember { mutableStateOf(false) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {

        Text(
            text = "GastroLab",
            style = TextStyle(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.tertiary,
                        MaterialTheme.colorScheme.surface,
                        MaterialTheme.colorScheme.tertiary
                    )
                ),
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 32.sp
            ),
            modifier = Modifier
        )

        Spacer(modifier = Modifier.height(19.dp))

        Text(
            "Inicia Sesión",
            fontSize = 26.sp,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(12.dp))

        Text(
            "o únete a",
            fontSize = 20.sp,
            color = Color.Black.copy(alpha = 0.8f)
        )

        Spacer(modifier = Modifier.height(25.dp))

        LoginButton(
            text = "Inicia sesión con Google",
            backgroundColor = MaterialTheme.colorScheme.primaryContainer,
            borderColor = MaterialTheme.colorScheme.background
        )
        Spacer(modifier = Modifier.height(16.dp))

        LoginButton(
            text = "Inicia sesión con Apple",
            backgroundColor = MaterialTheme.colorScheme.secondaryContainer,
            borderColor = MaterialTheme.colorScheme.secondary
        )
        Spacer(modifier = Modifier.height(16.dp))

        LoginButton(
            text = "Inicia sesión con Facebook",
            backgroundColor = MaterialTheme.colorScheme.tertiaryContainer,
            borderColor = MaterialTheme.colorScheme.tertiary
        )
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
            modifier = Modifier.fillMaxWidth(),

            )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = if (isPasswordVisible) PasswordVisualTransformation() else androidx.compose.ui.text.input.VisualTransformation.None,
            trailingIcon = {
                Text(
                    text = if (isPasswordVisible) "mostrar" else "ocultar",
                    color = primaryColor,
                    modifier = Modifier.clickable { isPasswordVisible = !isPasswordVisible }
                )
            },
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = rememberPass, onCheckedChange = { rememberPass = it })
            Text("Recordarme", color = Color.Black, modifier = Modifier.padding(start = 8.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text("Más información", color = Color.Red, fontSize = 12.sp)
        }
        Spacer(modifier = Modifier.height(8.dp))


        Text(
            text = "¿Has olvidado tu contraseña?",
            color = Color.Black,
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

    }
}

@Composable
fun LoginButton(text: String, backgroundColor: Color, borderColor: Color) {
    OutlinedButton(
        onClick = { },
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = backgroundColor
        ),
        border = BorderStroke(2.dp, borderColor)
    ) {
        Text(
            text = text,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
    }
}