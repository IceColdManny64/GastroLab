package com.example.gastrolab.ui.screens.LoginScreens

import android.content.Context
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.clasetrabajo.data.viewmodel.LoginViewModel
import com.example.gastrolab.data.model.LoginModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpInterface(navController: NavHostController, ViewModel: LoginViewModel = viewModel()) {
    val primaryColor = MaterialTheme.colorScheme.primary
    val secondaryColor = MaterialTheme.colorScheme.secondary
    var email by remember { mutableStateOf("") }
//    var emailOrUsername by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    val context = LocalContext.current
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
                text = "Regresar a opciones de inicio de sesión",
                color = Color.Black,
                fontSize = 16.sp
            )
        }

        Spacer(modifier = Modifier.height(32.dp))


        Text(
            text = "Registrarse",
            color = Color.Black,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(35.dp))

        Text(
            text = "Correo electrónico",
            color = Color.Black,
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Correo electrónico") },
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(16.dp))


        Text(
            text = "Contraseña",
            color = Color.Black,
            fontSize = 16.sp
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
                    text = if (isPasswordVisible) "mostrar" else "ocultar",
                    color = primaryColor,
                    modifier = Modifier.clickable { isPasswordVisible = !isPasswordVisible }
                )
            },
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val account = LoginModel(email = email, password = password)
                TryCreate(account, context, ViewModel, navController)
                // Lógica para registrar al usuario
                println("Email/Username: $email, Password: $password")
            },
            modifier = Modifier.fillMaxWidth(),
            colors = androidx.compose.material3.ButtonDefaults.buttonColors(containerColor = primaryColor)
        ) {
            Text("Registrarse", color = Color.White)
        }

        Spacer(modifier = Modifier.height(32.dp))


//        Text(
//            text = "¿Has olvidado tu contraseña?",
//            color = secondaryColor,
//            fontSize = 15.sp,
//            modifier = Modifier
//                .clickable { navController.navigate("loginPasswordScreen") }
//                .align(Alignment.CenterHorizontally)
//        )
    }
}

fun TryCreate(
    acc: LoginModel,
    context: Context,
    viewModel: LoginViewModel,
    navController: NavController
){
    if (
        acc.email.isEmpty() ||
        acc.password.isEmpty()
    ) {
        Toast.makeText(context, "None of the fields can be empty", Toast.LENGTH_SHORT).show()
        return
    } else {
        val acc = LoginModel(email = acc.email, password = acc.password)
        viewModel.createUser(acc) { jsonResponse ->
            val createUsStatus = jsonResponse?.get("store")?.asString
            if (createUsStatus == "success") {
                Toast.makeText(context, "Account created successfully", Toast.LENGTH_SHORT).show()
                navController.navigate("mainScreen")
            } else {
                Toast.makeText(context, "Error creating account", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

