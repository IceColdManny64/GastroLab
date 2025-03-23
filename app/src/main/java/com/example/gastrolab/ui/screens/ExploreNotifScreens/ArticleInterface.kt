package com.example.gastrolab.ui.screens.ExploreNotifScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.gastrolab.R
import com.example.gastrolab.ui.theme.GastroLabTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleInterface(navController: NavController) {
    GastroLabTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "GastroLab",
                            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                            fontSize = 20.sp,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    },
                    actions = {
                        IconButton(onClick = { /* Acción de cuenta */ }) {
                            androidx.compose.material3.Icon(
                                imageVector = Icons.Filled.AccountCircle,
                                contentDescription = "Cuenta",
                                tint = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = MaterialTheme.colorScheme.onPrimary
                    )
                )
            },
            bottomBar = {
                BottomAppBar(
                    modifier = Modifier.fillMaxWidth(),
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
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
            }
        ) { innerPadding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp)
                    .background(MaterialTheme.colorScheme.background),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Título del artículo
                item {
                    Text(
                        text = "La pizza: Un manjar de Italia",
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }

                // Imagen principal
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp)
                            .background(MaterialTheme.colorScheme.primaryContainer),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.pizza),
                            contentDescription = "Imagen principal"
                        )
                    }
                }

                // Texto del artículo principal (desde strings.xml)
                item {
                    Text(
                        text = stringResource(id = R.string.article_description),
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                // Segunda sección: imagen secundaria y texto
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.Top
                    ) {
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = stringResource(id = R.string.secondary_description),
                                color = MaterialTheme.colorScheme.onSurface,
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Image(
                            painter = painterResource(id = R.drawable.pizza),
                            contentDescription = "Imagen secundaria",
                            modifier = Modifier
                                .size(150.dp)
                                .background(MaterialTheme.colorScheme.surfaceVariant)
                        )
                    }
                }

                // Área para comentarios o notas
                item {
                    OutlinedTextField(
                        value = "",
                        onValueChange = {},
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp),
                        shape = RoundedCornerShape(8.dp),
                        placeholder = {
                            Text(
                                text = stringResource(id = R.string.comment_placeholder),
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    )
                }

                // Botón "Ver receta" con navegación a RecipeInterface
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Button(
                            onClick = {
                                navController.navigate("recipeScreen")
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.tertiary
                            )
                        ) {
                            Text(
                                "Ver receta ➤",
                                fontSize = 14.sp,
                                color = MaterialTheme.colorScheme.onTertiary
                            )
                        }
                    }
                }
            }
        }
    }
}


