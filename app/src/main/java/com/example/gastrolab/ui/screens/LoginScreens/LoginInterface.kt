package com.example.gastrolab.ui.screens.LoginScreens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.clasetrabajo.data.viewmodel.LoginViewModel
import com.example.gastrolab.R
import com.example.gastrolab.data.model.LoginModel
import com.example.gastrolab.data.model.SessionManager
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun LoginInterface(navController: NavHostController, ViewModel: LoginViewModel = viewModel()) {


    val context = LocalContext.current
    val primaryColor = MaterialTheme.colorScheme.primary

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    val systemUiController = rememberSystemUiController()
    val isDarkTheme = isSystemInDarkTheme()

    // ✅ Obten el color fuera del SideEffect
    val statusBarColor = MaterialTheme.colorScheme.background

    // ✅ Configura el color de la barra de estado
    SideEffect {
        systemUiController.setStatusBarColor(
            color = statusBarColor,
            darkIcons = !isDarkTheme
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.secondary)
            .padding(16.dp)
            .statusBarsPadding() // esto reemplaza systemBarsPadding si solo quieres ajustar la barra de estado
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        // LOGO DE LA APP
        androidx.compose.foundation.Image(
            painter = painterResource(id = R.drawable.gastrolab),
            contentDescription = "Logo de la app",
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .padding(bottom = 16.dp)
        )

        // TÍTULO
        Text(
            text = "Bienvenid@ a GastroLab",
            style = TextStyle(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.onBackground,
                        MaterialTheme.colorScheme.onBackground
                    )
                ),
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 32.sp
            )
        )

        Spacer(modifier = Modifier.height(19.dp))

        Text("Inicia Sesión", fontSize = 26.sp, color = MaterialTheme.colorScheme.onBackground)
        Spacer(modifier = Modifier.height(12.dp))
        Text("o únete a", fontSize = 20.sp, color = MaterialTheme.colorScheme.onBackground)
        Spacer(modifier = Modifier.height(25.dp))

        // EMAIL
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email / teléfono") },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.onSecondary
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        // CONTRASEÑA
        OutlinedTextField(
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.onSecondary
            ),
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

        Spacer(modifier = Modifier.height(20.dp))

        // BOTÓN INICIAR SESIÓN
        OutlinedButton(
            onClick = { TryLogin(email, password, context, ViewModel, navController) },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onSecondary
            )
        ) {
            Text("Iniciar sesión", fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "¿Has olvidado tu contraseña?",
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 14.sp,
            modifier = Modifier
                .clickable { navController.navigate("loginPasswordScreen") }
                .padding(4.dp)
        )

        Spacer(modifier = Modifier.height(6.dp))

        // BOTÓN REGISTRO
        OutlinedButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = { navController.navigate("signUpScreen") },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        ) {
            Text("Registrarse")
        }

        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = "¿Sin conexión? mira tus recetas guardadas!",
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 14.sp,
            modifier = Modifier
                .clickable { navController.navigate("loginPasswordScreen") }
                .padding(4.dp)
        )

        Spacer(modifier = Modifier.height(6.dp))
        // BOTÓN INVITADO
        OutlinedButton(
            onClick = { navController.navigate("offScreen") },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = MaterialTheme.colorScheme.onSecondary,
                contentColor = MaterialTheme.colorScheme.secondary
            )
        ) {
            Text("Ver recetas guardadas", fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }
    }
}


fun TryLogin(
    email:String,
    password:String,
    context: Context,
    viewModel: LoginViewModel,
    navController: NavController
){
    if(email.isBlank() || password.isBlank()){
        Toast.makeText(context, "Email o contraseña vacíos", Toast.LENGTH_SHORT).show()
        return
    }
    val session = SessionManager(context)

    viewModel.loginAPI(LoginModel(email = email, password = password)) { json ->
        if (json?.get("login")?.asString == "success") {
            val userId = json.get("id")?.asInt ?: -1
            session.saveSession(userId, email)

            Toast.makeText(context, "Login exitoso", Toast.LENGTH_SHORT).show()
            navController.navigate("mainScreen") {
                popUpTo("loginScreen") { inclusive = true }
            }
        } else if (json?.get("login")?.asString == "failed") {
            Toast.makeText(context, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
        }
        else {
            Toast.makeText(context, "Error de conexion", Toast.LENGTH_SHORT).show()
        }
    }
}
