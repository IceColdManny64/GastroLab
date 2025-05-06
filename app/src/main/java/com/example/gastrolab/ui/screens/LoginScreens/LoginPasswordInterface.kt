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
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.gastrolab.ui.theme.FreshGreen
import com.example.gastrolab.ui.theme.LightSalmon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginPasswordInterface(navController: NavHostController) {
    var email by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Logo con degradado
        Text(
            text = "GastroLab",
            style = TextStyle(
                brush = Brush.verticalGradient(
                    listOf(FreshGreen, LightSalmon)
                ),
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 32.sp
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Botón de volver
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clickable { navController.navigate("loginScreen") }
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Regresar",
                tint = FreshGreen,
                modifier = Modifier.size(28.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Volver", color = FreshGreen, fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Contraseña olvidada",
            style = MaterialTheme.typography.headlineSmall.copy(color = Color.Black)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Ingresa tu nombre de usuario o correo electrónico aquí y te enviaremos instrucciones sobre cómo restablecerla.",
            color = Color.Black,
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(28.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email", color = Color.Black) },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = LightSalmon,
                unfocusedBorderColor = LightSalmon.copy(alpha = 0.6f),
                cursorColor = LightSalmon
            ),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                println("Correo electrónico enviado a: $email")
            },
            modifier = Modifier
                .fillMaxWidth(0.7f),
            colors = ButtonDefaults.buttonColors(
                containerColor = FreshGreen,
                contentColor = Color.Black
            )
        ) {
            Text("Enviar")
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "¿Ya tienes cuenta? Conectarse",
            color = Color.Black,
            fontSize = 15.sp,
            modifier = Modifier
                .clickable { navController.navigate("loginScreen") }
                .padding(8.dp),
        )
    }
}
