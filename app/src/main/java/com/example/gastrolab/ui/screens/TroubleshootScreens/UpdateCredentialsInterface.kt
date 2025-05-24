package com.example.gastrolab.ui.screens.TroubleshootScreens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.clasetrabajo.data.viewmodel.LoginViewModel
import com.example.gastrolab.R
import com.google.gson.JsonObject
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateCredentialsInterface(
    navController: NavHostController,
    viewModel: LoginViewModel = viewModel()
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    var currentEmail by remember { mutableStateOf("") }
    var currentPassword by remember { mutableStateOf("") }
    var newEmail by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }
    var isCurrentPasswordVisible by remember { mutableStateOf(false) }
    var isNewPasswordVisible by remember { mutableStateOf(false) }
    var userId by remember { mutableStateOf<Int?>(null) }
    var emailVerified by remember { mutableStateOf(false) }
    var passwordVerified by remember { mutableStateOf(false) }

    fun showToast(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    fun verifyEmail() {
        if (currentEmail.isBlank()) {
            showToast("Ingresa tu email actual")
            return
        }

        coroutineScope.launch {
            val request = JsonObject().apply {
                addProperty("action", "verify_email")
                addProperty("current_email", currentEmail.trim())
            }

            try {
                val response = viewModel.api.verifyEmail(request)
                val body = response.body()
                if (response.isSuccessful && body?.get("exists")?.asBoolean == true) {
                    userId = body.get("id")?.asInt
                    emailVerified = true
                    showToast("Email verificado")
                } else {
                    showToast("Email no encontrado")
                }
            } catch (e: Exception) {
                showToast("Error al verificar email")
                Log.e("UpdateCred", "Email error", e)
            }
        }
    }

    fun verifyCurrentPassword() {
        if (currentPassword.isBlank()) {
            showToast("Ingresa tu contraseña actual")
            return
        }

        if (userId == null) {
            showToast("ID de usuario inválido")
            return
        }

        coroutineScope.launch {
            val request = JsonObject().apply {
                addProperty("action", "verify_password")
                addProperty("id", userId!!)
                addProperty("current_password", currentPassword)
            }

            try {
                val response = viewModel.api.verifyCurrentPassword(request)
                val body = response.body()
                if (response.isSuccessful && body != null && body.get("valid")?.asBoolean == true) {
                    passwordVerified = true
                    showToast("Contraseña correcta")
                } else {
                    showToast("Contraseña incorrecta")
                }
            } catch (e: Exception) {
                showToast("Error verificando contraseña")
                Log.e("UpdateCred", "Password error", e)
            }
        }
    }

    fun updateCredentials() {
        if (newEmail.isBlank() || newPassword.isBlank()) {
            showToast("Completa los campos")
            return
        }

        if (userId == null) {
            showToast("ID de usuario inválido")
            return
        }

        coroutineScope.launch {
            val request = JsonObject().apply {
                addProperty("action", "update_credentials")
                addProperty("id", userId!!)
                addProperty("new_email", newEmail.trim())
                addProperty("new_password", newPassword)
            }

            try {
                val response = viewModel.api.updateCredentials(request)
                val body = response.body()
                if (response.isSuccessful && body?.get("status")?.asString == "success") {
                    showToast("Credenciales actualizadas")
                    navController.popBackStack()
                } else {
                    val msg = body?.get("message")?.asString ?: "Error desconocido"
                    showToast("Error: $msg")
                }
            } catch (e: Exception) {
                showToast("Error de conexión")
                Log.e("UpdateCred", "Update error", e)
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.height(80.dp),
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.secondary
                ),
                title = {
                    val gastroGradient = listOf(
                        MaterialTheme.colorScheme.tertiary,
                        MaterialTheme.colorScheme.surface,
                        MaterialTheme.colorScheme.tertiary
                    )
                    Text(
                        text = stringResource(R.string.app_name),
                        style = TextStyle(brush = Brush.verticalGradient(colors = gastroGradient)),
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 25.sp
                    )
                },
                actions = {
                    IconButton(onClick = { navController.navigate("accountScreen") }) {
                        Icon(imageVector = Icons.Filled.AccountCircle, contentDescription = "Account icon")
                    }
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                modifier = Modifier.fillMaxWidth().height(80.dp),
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ) {
                IconButton(
                    modifier = Modifier.weight(1f),
                    onClick = { navController.navigate("mainScreen") },
                ) {
                    Icon(
                        imageVector = Icons.Filled.Home,
                        contentDescription = "Inicio",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
                IconButton(
                    modifier = Modifier.weight(1f),
                    onClick = { navController.navigate("searchScreen") },
                ) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Buscar",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
                IconButton(
                    modifier = Modifier.weight(1f),
                    onClick = { navController.navigate("notifScreen") },
                ) {
                    Icon(
                        imageVector = Icons.Filled.Notifications,
                        contentDescription = "Notificaciones",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
                IconButton(
                    modifier = Modifier.weight(1f),
                    onClick = { navController.navigate("settingsScreen") },
                ) {
                    Icon(
                        imageVector = Icons.Filled.Menu,
                        contentDescription = "Menú",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            when {
                !emailVerified -> {
                    OutlinedTextField(
                        value = currentEmail,
                        onValueChange = { currentEmail = it },
                        label = { Text("Email actual") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(Modifier.height(16.dp))
                    Button(
                        onClick = { verifyEmail() },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Verificar Email")
                    }
                }

                !passwordVerified -> {
                    OutlinedTextField(
                        value = currentPassword,
                        onValueChange = { currentPassword = it },
                        label = { Text("Contraseña actual") },
                        visualTransformation = if (isCurrentPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        trailingIcon = {
                            IconButton(onClick = { isCurrentPasswordVisible = !isCurrentPasswordVisible }) {
                                Icon(
                                    imageVector = if (isCurrentPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                    contentDescription = "Visibilidad"
                                )
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(Modifier.height(16.dp))
                    Button(
                        onClick = { verifyCurrentPassword() },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Verificar Contraseña")
                    }
                }

                else -> {
                    OutlinedTextField(
                        value = newEmail,
                        onValueChange = { newEmail = it },
                        label = { Text("Nuevo email") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(Modifier.height(8.dp))
                    OutlinedTextField(
                        value = newPassword,
                        onValueChange = { newPassword = it },
                        label = { Text("Nueva contraseña") },
                        visualTransformation = if (isNewPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        trailingIcon = {
                            IconButton(onClick = { isNewPasswordVisible = !isNewPasswordVisible }) {
                                Icon(
                                    imageVector = if (isNewPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                    contentDescription = "Visibilidad"
                                )
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(Modifier.height(16.dp))
                    Button(
                        onClick = { updateCredentials() },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Actualizar Credenciales")
                    }
                }
            }
        }
    }
}
