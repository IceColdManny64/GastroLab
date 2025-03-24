package com.example.gastrolab.ui.screens.RecipeSearchScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.gastrolab.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeInterface(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Enchiladas Verdes",
                        fontWeight = FontWeight.Bold,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Volver",
                            tint = MaterialTheme.colorScheme.tertiary
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary, // Color de fondo
                    titleContentColor = MaterialTheme.colorScheme.onPrimary // Color del texto
                )
            )
        },
        bottomBar = {
            // Barra de navegación inferior
            BottomAppBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ) {
                IconButton(modifier = Modifier.weight(1f), onClick = { navController.navigate("mainScreen") }) {
                    Icon(imageVector = Icons.Filled.Home, contentDescription = "")
                }
                IconButton(modifier = Modifier.weight(1f), onClick = { navController.navigate("searchScreen") }) {
                    Icon(imageVector = Icons.Filled.Search, contentDescription = "")
                }
                IconButton(modifier = Modifier.weight(1f), onClick = { navController.navigate("notifScreen") }) {
                    Icon(imageVector = Icons.Filled.Notifications, contentDescription = "")
                }
                IconButton(modifier = Modifier.weight(1f), onClick = { navController.navigate("settingsScreen") }) {
                    Icon(imageVector = Icons.Filled.Menu, contentDescription = "")
                }
            }
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState()) // Hace que el contenido sea desplazable
            ) {
                // Imagen de la receta
                Image(
                    painter = painterResource(id = R.drawable.enchis),
                    contentDescription = "Enchiladas Verdes",
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 300.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(16.dp))

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Face,
                            contentDescription = "Reloj",
                            tint = Color.White // Color del ícono blanco
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Tiempo de preparación: 45 minutos",
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onBackground // Color de texto blanco
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Subtítulo "Ingredientes"
                Text(
                    text = "Ingredientes",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground, // Color de texto negro
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Lista de ingredientes
                Text(
                    text = """
                        - 500 g de pollo desmenuzado
                        - 12 tortillas de maíz
                        - 2 tazas de salsa verde
                        - 1 taza de crema ácida
                        - 1 taza de queso rallado
                        - 1 cebolla picada
                        - Aceite para freír
                    """.trimIndent(),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onBackground, // Color de texto
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Subtítulo "Preparación"
                Text(
                    text = "Preparación",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground, // Color de texto negro
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Instrucciones de preparación
                Text(
                    text = """
                        1. Calienta el aceite en una sartén y fríe las tortillas ligeramente.
                        2. Rellena cada tortilla con pollo desmenuzado y enrolla.
                        3. Coloca las enchiladas en un refractario y cubre con salsa verde.
                        4. Esparce la crema ácida y el queso rallado por encima.
                        5. Hornea a 180°C durante 20 minutos o hasta que estén doradas.
                        6. Sirve caliente y disfruta.
                    """.trimIndent(),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onBackground, // Color de texto negro
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Botón para ver comentarios (en la parte inferior)
                Button(
                    onClick = { navController.navigate("commentsScreen") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Text("Ver Comentarios")
                }

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    )
}