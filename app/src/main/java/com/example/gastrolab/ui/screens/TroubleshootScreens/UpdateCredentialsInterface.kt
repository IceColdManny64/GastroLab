package com.example.gastrolab.ui.screens.TroubleshootScreens

import android.widget.Toast
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.clasetrabajo.data.viewmodel.LoginViewModel
import com.example.gastrolab.data.model.SessionManager
import com.google.gson.JsonObject
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateCredentialsInterface(
    navController: NavHostController,
    viewModel: LoginViewModel = viewModel(),
    onAuthSuccess: () -> Unit
) {
    val context = LocalContext.current
    val activity = context as FragmentActivity
    val executor = remember { ContextCompat.getMainExecutor(context) }

    var authError by remember { mutableStateOf<String?>(null) }
    var isAuthenticated by remember { mutableStateOf(false) }

    // Datos para actualizar credenciales
    val session = remember { SessionManager(context) }
    val userId = session.getUserId()
    var newEmail by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }
    var isNewPasswordVisible by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    // Configurar prompt de autenticación biométrica o device credential
    val promptInfo = remember {
        BiometricPrompt.PromptInfo.Builder()
            .setTitle("Autenticación requerida")
            .setSubtitle("Por favor autentícate para actualizar credenciales")
            // Permite biometría o PIN/contraseña del dispositivo (fallback)
            .setAllowedAuthenticators(
                BiometricManager.Authenticators.BIOMETRIC_STRONG or
                        BiometricManager.Authenticators.DEVICE_CREDENTIAL
            )
            .build()
    }

    // Crear objeto BiometricPrompt con callbacks
    val biometricPrompt = remember {
        BiometricPrompt(
            activity,
            executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    isAuthenticated = true
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    authError = "Autenticación fallida. Intenta de nuevo."
                }

                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    authError = "Error: $errString"
                }
            }
        )
    }

    // Lanzar prompt de autenticación al cargar la pantalla
    LaunchedEffect(Unit) {
        val biometricManager = BiometricManager.from(context)
        val canAuthenticate = biometricManager.canAuthenticate(
            BiometricManager.Authenticators.BIOMETRIC_STRONG or
                    BiometricManager.Authenticators.DEVICE_CREDENTIAL
        )

        if (canAuthenticate == BiometricManager.BIOMETRIC_SUCCESS) {
            biometricPrompt.authenticate(promptInfo)
        } else {
            authError = "Tu dispositivo no soporta autenticación biométrica ni PIN"
        }
    }

    if (!isAuthenticated) {
        // Mostrar UI de autenticación
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Autenticando...")
            authError?.let {
                Spacer(modifier = Modifier.height(12.dp))
                Text(it, color = MaterialTheme.colorScheme.error)
            }
        }
        return // Salir para no mostrar el formulario hasta autenticar
    }

    // Si autenticación exitosa, mostrar formulario para actualizar credenciales
    fun updateCredentials() {
        if (newEmail.isBlank() || newPassword.isBlank()) {
            Toast.makeText(context, "Completa todos los campos", Toast.LENGTH_SHORT).show()
            return
        }
        if (userId == -1) {
            Toast.makeText(context, "Sesión inválida. Inicia sesión de nuevo.", Toast.LENGTH_SHORT).show()
            return
        }
        coroutineScope.launch {
            val req = JsonObject().apply {
                addProperty("action", "update_credentials")
                addProperty("id", userId)
                addProperty("new_email", newEmail.trim())
                addProperty("new_password", newPassword)
            }
            try {
                val resp = viewModel.api.updateCredentials(req).body()
                if (resp?.get("status")?.asString == "success") {
                    session.saveSession(userId, newEmail.trim())
                    Toast.makeText(context, "Credenciales actualizadas", Toast.LENGTH_SHORT).show()
                    navController.popBackStack()
                } else {
                    Toast.makeText(context, "Error: ${resp?.get("message")?.asString ?: "Desconocido"}", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(context, "Error de red", Toast.LENGTH_SHORT).show()
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Actualizar credenciales") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ) {
                IconButton(
                    modifier = Modifier.weight(1f),
                    onClick = { navController.navigate("mainScreen") }
                ) {
                    Icon(Icons.Default.Home, contentDescription = "Inicio")
                }
                IconButton(
                    modifier = Modifier.weight(1f),
                    onClick = { navController.navigate("searchScreen") }
                ) {
                    Icon(Icons.Default.Search, contentDescription = "Buscar")
                }
                IconButton(
                    modifier = Modifier.weight(1f),
                    onClick = { navController.navigate("notifScreen") }
                ) {
                    Icon(Icons.Default.Notifications, contentDescription = "Notificaciones")
                }
                IconButton(
                    modifier = Modifier.weight(1f),
                    onClick = { navController.navigate("settingsScreen") }
                ) {
                    Icon(Icons.Default.Menu, contentDescription = "Menú")
                }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
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

