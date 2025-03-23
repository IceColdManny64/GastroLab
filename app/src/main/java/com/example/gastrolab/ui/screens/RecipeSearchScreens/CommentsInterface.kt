package com.example.gastrolab.ui.screens.RecipeSearchScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Star
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
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
fun CommentsInterface(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Comentarios",
                        fontWeight = FontWeight.Bold
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
                    .verticalScroll(rememberScrollState())
            ) {
                // Imagen de la receta
                Image(
                    painter = painterResource(id = R.drawable.enchis), // Cambia esto por tu recurso de imagen
                    contentDescription = "Enchiladas Verdes",
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 300.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Calificación con estrellas más grandes y texto
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Estrellas más grandes
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Estrellas llenas
                        repeat(5) {
                            Icon(
                                imageVector = Icons.Filled.Star,
                                contentDescription = "Estrella llena",
                                tint = Color.Yellow,
                                modifier = Modifier.size(32.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))


                    Text(
                        text = "Calificación: 5",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
                // Título de la receta
                Text(
                    text = "Enchiladas Verdes",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Iconos de favoritos, guardar y compartir con texto
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    // Botón de favoritos con texto
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.clickable { /* Acción para favoritos */ }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Add, // Ícono de favoritos
                            contentDescription = "Favoritos",
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Favorito",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }

                    // Botón de guardar con texto
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.clickable { /* Acción para guardar */ }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.CheckCircle, // Ícono de guardar
                            contentDescription = "Guardar",
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Guardar",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }

                    // Botón de compartir con texto
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.clickable { /* Acción para compartir */ }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Send, // Ícono de compartir
                            contentDescription = "Compartir",
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Compartir",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Número de comentarios
                Text(
                    text = "788 comentarios",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Comentario de ejemplo
                CommentWithReplies()

                Spacer(modifier = Modifier.height(16.dp))

                // Campo para agregar un nuevo comentario
                AddCommentField()
            }
        }
    )
}

@Composable
fun CommentWithReplies() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onBackground)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Mario Vargas",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onTertiary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "El mejor platillo del mundo, se lo recomendé a mi primo y dijo que estaba a todo dar.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "45 respuestas ✔",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.background,
                modifier = Modifier.clickable { /* Acción para ver respuestas */ }
            )

            // Respuestas de ejemplo
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Respuesta 1: ¡Totalmente de acuerdo!",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.background,
                modifier = Modifier.padding(start = 16.dp)
            )
            Text(
                text = "Respuesta 2: Las mejores enchiladas que he probado.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.background,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }
}

@Composable
fun AddCommentField() {
    var commentText by remember { mutableStateOf("") }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onPrimary)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            TextField(
                value = commentText,
                onValueChange = { commentText = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 100.dp), // Altura mínima para el campo de texto
                placeholder = {
                    Text(
                        text= "Agrega un comentario...",
                        color = MaterialTheme.colorScheme.onSurface) },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = MaterialTheme.colorScheme.onSecondary
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { /* */ },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Publicar")
            }
        }
    }
}