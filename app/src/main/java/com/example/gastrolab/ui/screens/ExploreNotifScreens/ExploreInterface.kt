package com.example.gastrolab.ui.screens.ExploreNotifScreens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import com.example.gastrolab.R
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.IconButton
import androidx.compose.ui.tooling.preview.Preview


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ExploreInterface(navController: NavHostController) {
    val categories = listOf(
        Triple(
            "Postres",
            listOf(
                R.drawable.ic_dessert, R.drawable.flan, R.drawable.cake, R.drawable.churros,
                R.drawable.tiramisu, R.drawable.brownie, R.drawable.donuts, R.drawable.panna_cotta
            ),
            listOf(
                "Pay Fresa", "Flan", "Pastel de Chocolate", "Churros",
                "Tiramisú", "Brownie", "Donas", "Panna Cotta"
            )
        ),
        Triple(
            "Saludables",
            listOf(
                R.drawable.ic_healthy, R.drawable.salad, R.drawable.smoothie, R.drawable.avocado_toast,
                R.drawable.quinoa_salad, R.drawable.green_juice, R.drawable.yogurt_bowl, R.drawable.veg_burger
            ),
            listOf(
                "Ensalada Saludable", "Ensalada Fresca", "Smoothie Verde", "Tostada de Aguacate",
                "Ensalada de Quinoa", "Jugo Verde", "Tazón de Yogurt", "Hamburguesa Vegana"
            )
        ),
        Triple(
            "Rápidas",
            listOf(
                R.drawable.ic_fast, R.drawable.sandwich, R.drawable.quick_pasta, R.drawable.bowl,
                R.drawable.burger, R.drawable.tacos, R.drawable.pizza_slice, R.drawable.wrap
            ),
            listOf(
                "Big Mac", "Sándwich", "Pasta Express", "Tazón Saludable",
                "Hamburguesa", "Tacos al Pastor", "Rebanada de Pizza", "Wrap de Pollo"
            )
        ),
        Triple(
            "Italianas",
            listOf(
                R.drawable.ic_italian, R.drawable.pizza, R.drawable.pasta, R.drawable.lasagna,
                R.drawable.ravioli, R.drawable.minestrone, R.drawable.focaccia, R.drawable.italian_salad
            ),
            listOf(
                "Pasta Italiana", "Pizza Margarita", "Pasta Alfredo", "Lasaña Tradicional",
                "Ravioli de Espinacas", "Sopa Minestrone", "Focaccia Italiana", "Ensalada Italiana"
            )
        ),
        Triple(
            "Mexicanas",
            listOf(
                R.drawable.ic_mexican, R.drawable.tacos, R.drawable.enchiladas, R.drawable.guacamole,
                R.drawable.burritos, R.drawable.chiles_rellenos, R.drawable.sopes, R.drawable.quesadillas
            ),
            listOf(
                "Pozole", "Tacos de Bistec", "Enchiladas Verdes", "Guacamole Casero",
                "Burritos Tex-Mex", "Chiles Rellenos", "Sopes Tradicionales", "Quesadillas de Queso"
            )
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Explorar Recetas",
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
            items(categories) { (category, images, titles) ->
                CategoryItemWithCarousel(category, images, titles) {
                    navController.navigate("recommendedScreen/$category")
                }
            }
            }
        }
    }

// Tarjeta para cada categoría + Carrusel con títulos de cada imagen
@Composable
fun CategoryItemWithCarousel(category: String, images: List<Int>, titles: List<String>, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondary)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            /*
  Icon(
      painter = painterResource(id = images.first()),
      contentDescription = category,
      tint = MaterialTheme.colorScheme.onSecondary,
      modifier = Modifier.size(48.dp)
  )
  Spacer(modifier = Modifier.width(12.dp))
  */
                Text(
                    text = category,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            RecipeCarousel(images, titles)
        }
    }


// Carrusel de imágenes con títulos debajo
@Composable
fun RecipeCarousel(images: List<Int>, titles: List<String>) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(horizontal = 8.dp)
    ) {
        items(images.zip(titles)) { (imageRes, title) ->
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = title,
                    modifier = Modifier
                        .size(120.dp)
                        .clip(RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
}
