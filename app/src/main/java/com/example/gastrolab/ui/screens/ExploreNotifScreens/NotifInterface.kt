package com.example.gastrolab.ui.screens.ExploreNotifScreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun NotifInterface(navController: NavHostController) {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun NotifInterface(navController: NavHostController) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "Notificaciones",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    },
                    actions = {
                        IconButton(onClick = { }) {
                            androidx.compose.material3.Icon(
                                imageVector = Icons.Filled.AccountCircle,
                                contentDescription = "Cuenta",
                                tint = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,  // Primario
                        titleContentColor = MaterialTheme.colorScheme.onPrimary // Texto en primario
                    )
                )
            },
            bottomBar = {
                BottomAppBar(
                    modifier = Modifier.fillMaxWidth(),
                    containerColor = MaterialTheme.colorScheme.primary,  // Primario
                    contentColor = MaterialTheme.colorScheme.onPrimary   // Íconos en barra inferior
                ) {
                    IconButton(
                        modifier = Modifier.weight(1f),
                        onClick = { navController.navigate("mainScreen") },
                    ) {
                        androidx.compose.material3.Icon(
                            imageVector = Icons.Filled.Home,
                            contentDescription = "Inicio",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                    IconButton(
                        modifier = Modifier.weight(1f),
                        onClick = { navController.navigate("searchScreen") },
                    ) {
                        androidx.compose.material3.Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "Buscar",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                    IconButton(
                        modifier = Modifier.weight(1f),
                        onClick = { navController.navigate("notifScreen") },
                    ) {
                        androidx.compose.material3.Icon(
                            imageVector = Icons.Filled.Notifications,
                            contentDescription = "Notificaciones",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                    IconButton(
                        modifier = Modifier.weight(1f),
                        onClick = { navController.navigate("settingsScreen") },
                    ) {
                        androidx.compose.material3.Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Menú",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { },
                    shape = CircleShape,
                    containerColor = MaterialTheme.colorScheme.secondary,  // Secundario
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    androidx.compose.material3.Icon(
                        Icons.Default.Add,
                        contentDescription = "Agregar",
                        tint = MaterialTheme.colorScheme.onSecondary // Ícono en el FAB
                    )
                }
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "No hay mensajes en tu bandeja de entrada",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = "¡Aquí verás los mensajes que recibas de la Comunidad!",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.secondary,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}