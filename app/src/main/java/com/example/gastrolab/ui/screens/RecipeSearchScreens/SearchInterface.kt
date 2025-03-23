package com.example.gastrolab.ui.screens.RecipeSearchScreens

import android.graphics.fonts.FontStyle
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.gastrolab.R



// Define la clase Recipe
data class Recipe(
    val name: String,
    val imageResId: Int
)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchInterface(navController: NavHostController) {
    var searchText by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize()
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        // Top Bar
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary
            ),
            title = {
                Icon(
                    imageVector = Icons.Filled.Search, // Ícono de búsqueda
                    contentDescription = "Search icon",
                    modifier = Modifier.size(24.dp)
                )
            },
            actions = {

                Icon(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = "Account icon",
                    modifier = Modifier
                        .clickable { navController.navigate("accountScreen") }
                        .padding(8.dp)
                )
            }
        )

        // Barra de búsqueda y filtro
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = searchText,
                onValueChange = { searchText = it },
                label = { Text("Buscar") },
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Box {
                Button(onClick = { expanded = true }) {
                    Text("Filtrar por")
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    DropdownMenuItem(text = { Text("Ingredientes") }, onClick = { /* Acción Ingredientes */ expanded = false })
                    DropdownMenuItem(text = { Text("Receta") }, onClick = {
                        navController.navigate("recipeInterface")
                        expanded = false
                    })
                }
            }
        }
        Text(
            text = "Recomendaciones",
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
        )

        // Lista de recetas
        val recipes = listOf(
            Recipe("Enchiladas verdes", R.drawable.enchis),
            Recipe("Pastor", R.drawable.pastor),
            Recipe("Tamal", R.drawable.tamal),
            Recipe("Hamburguesa", R.drawable.hamburg),
            Recipe("Pizza", R.drawable.pizza),
            Recipe("Sincronizada", R.drawable.sincro)
        )

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp)
        ) {
            items(recipes) { recipe ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Imagen de la receta
                    Image(
                        painter = painterResource(id = recipe.imageResId),
                        contentDescription = recipe.name,
                        modifier = Modifier
                            .size(120.dp)
                            .clip(RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    // Nombre de la receta
                    Text(
                        text = recipe.name,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.weight(1f)
                    )
                    // Icono de tres puntos con .clickable
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "Ver más",
                        modifier = Modifier
                            .clickable {
                                // Navegar a RecipeInterface solo para "Enchiladas verdes"
                                if (recipe.name == "Enchiladas verdes") {
                                    navController.navigate("recipeScreen")
                                }
                            }
                            .padding(8.dp)
                    )
                }
            }
        }

        // Bottom Bar
        BottomAppBar(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.secondary
        ) {
            Icon(
                imageVector = Icons.Filled.Home,
                contentDescription = "Home",
                modifier = Modifier
                    .weight(1f)
                    .clickable { navController.navigate("mainScreen") }
                    .padding(8.dp)
            )
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "Search",
                modifier = Modifier
                    .weight(1f)
                    .clickable { navController.navigate("searchScreen") }
                    .padding(8.dp)
            )
            Icon(
                imageVector = Icons.Filled.Notifications,
                contentDescription = "Notifications",
                modifier = Modifier
                    .weight(1f)
                    .clickable { navController.navigate("notifScreen") }
                    .padding(8.dp)
            )
            Icon(
                imageVector = Icons.Filled.Menu,
                contentDescription = "Menu",
                modifier = Modifier
                    .weight(1f)
                    .clickable { navController.navigate("settingsScreen") }
                    .padding(8.dp)
            )
        }
    }
}
