package com.example.gastrolab.ui.screens.LoginScreens

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.TextStyle
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.lifecycle.viewmodel.compose.viewModel
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
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.gastrolab.data.model.LoginModel
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.clasetrabajo.data.viewmodel.LoginViewModel
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginInterface(navController: NavHostController, ViewModel: LoginViewModel = viewModel()) {
    val context = LocalContext.current
    val primaryColor = MaterialTheme.colorScheme.primary
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var isConfirmPasswordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {

        Text(
            text = "GastroLab",
            style = TextStyle(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.onBackground,
                        MaterialTheme.colorScheme.onBackground,
                        MaterialTheme.colorScheme.onBackground
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
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(12.dp))

        Text(
            "o únete a",
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(25.dp))

        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email / teléfono") },
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
        Spacer(modifier = Modifier.height(40.dp))

        OutlinedButton(
            onClick = { TryLogin(email, password, context, ViewModel, navController) },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.onSecondary
            ),
            border = BorderStroke(2.dp, Color.Red)
        ) {
            Text(
                text = "Iniciar sesión",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
        Spacer(modifier = Modifier.height(8.dp))


        Text(
            text = "¿Has olvidado tu contraseña?",
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 14.sp,
            modifier = Modifier
                .clickable { navController.navigate("loginPasswordScreen") }
                .padding(8.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { navController.navigate("loginPasswordScreen") },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onSecondary
            )
        )
        {
            Text("Contraseña olvidada")

        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { navController.navigate("signUpScreen") },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onSecondary
            )
        ) {
            Text("Registrarse")
        }


        OutlinedButton(
            onClick = { navController.navigate("mainScreen") },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = MaterialTheme.colorScheme.onPrimary,
                contentColor = MaterialTheme.colorScheme.primary
            ),
            border = BorderStroke(2.dp, Color.Red)
        ) {
            Text(
                text = "Modo invitado/sin conexión",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
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
    if(email == "" || password == ""){
        Toast.makeText(
            context,
            "email or password cannot be empty",
            Toast.LENGTH_SHORT
        ).show()
    } else {
        val login_model = LoginModel(email = email, password = password)
        viewModel.loginAPI(login_model){ jsonResponse ->
            val loginStatus = jsonResponse?.get("login")?.asString
            Log.d("debug", "LOGIN STATUS: $loginStatus")
            if(loginStatus == "success"){
                Toast.makeText(context, "Login successful", Toast.LENGTH_SHORT).show()
                navController.navigate("mainScreen")
            } else {
                Toast.makeText(
                    context,
                    "Failed login, check your credentials",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}