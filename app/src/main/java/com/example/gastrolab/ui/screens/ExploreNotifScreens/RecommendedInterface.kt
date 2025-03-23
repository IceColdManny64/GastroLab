package com.example.gastrolab.ui.screens.ExploreNotifScreens



import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.gastrolab.R
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.BottomAppBar
import androidx.compose.ui.Alignment

@Composable
@OptIn(ExperimentalMaterial3Api::class)

fun RecommendedInterface(navController: NavHostController) {
    val recipes = listOf(
        Recipe("Tacos al Pastor", "Mexicanas", R.drawable.tacos, "30 min", 80, "Fácil"),
        Recipe("Ensalada César", "Saludables", R.drawable.salad, "15 min", 90, "Fácil"),
        Recipe("Pizza Margarita", "Italianas", R.drawable.pizza, "40 min", 85, "Medio"),
        Recipe("Flan Napolitano", "Postres", R.drawable.flan, "60 min", 95, "Medio"),
        Recipe("Pasta Alfredo", "Italianas", R.drawable.pasta, "35 min", 90, "Fácil"),
        Recipe("Smoothie Verde", "Saludables", R.drawable.smoothie, "10 min", 70, "Fácil")
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Recomendadas",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                },
                actions = {
                    IconButton(onClick = { /* acción de cuenta */ }) {
                        Icon(
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
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(recipes) { recipe ->
                RecipeCard(recipe)
            }
        }
    }
}

@Composable
fun RecipeCard(recipe: Recipe) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onSurface)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Dificultad en la parte superior de la imagen
            Text(
                text = recipe.difficulty,
                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
                color = Color.White,
                modifier = Modifier
                    .padding(8.dp)
                    .background(Color.Black.copy(alpha = 0.5f), shape = RoundedCornerShape(8.dp))
                    .padding(horizontal = 12.dp, vertical = 4.dp)
            )

            // Imagen de la receta
            Image(
                painter = painterResource(id = recipe.imageRes),
                contentDescription = recipe.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Nombre de la receta
            Text(
                text = recipe.name,
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
            )
            Text(
                text = "Categoría: ${recipe.category}",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )

            // Detalles: Tiempo de preparación y calificación
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Icono de tiempo
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Filled.Info, // Aquí puedes reemplazarlo por un ícono de reloj
                        contentDescription = "Tiempo",
                        modifier = Modifier.size(18.dp)
                    )
                    Text(
                        text = recipe.prepTime,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray
                    )
                }

                // Icono de calificación
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Filled.ThumbUp, // Puedes usar el ícono de un corazón o pulgar hacia arriba
                        contentDescription = "Calificación",
                        modifier = Modifier.size(18.dp)
                    )
                    Text(
                        text = "${recipe.rating}%",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}

// Modelo de Receta actualizado
data class Recipe(
    val name: String,
    val category: String,
    val imageRes: Int,
    val prepTime: String, // Tiempo de preparación
    val rating: Int, // Calificación en porcentaje
    val difficulty: String // Nivel de dificultad
)
