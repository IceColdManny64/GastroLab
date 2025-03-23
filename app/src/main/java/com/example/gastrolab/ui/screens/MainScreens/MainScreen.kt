package com.example.gastrolab.ui.screens.MainScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material3.adaptive.currentWindowSize
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.DpSize
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.window.core.layout.WindowHeightSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
import com.example.clasetrabajo.data.model.MenuModel
import com.example.gastrolab.R
import com.example.gastrolab.ui.components.CompactMainView
import com.example.gastrolab.ui.components.MainView
import com.example.gastrolab.ui.components.MainViewExCard
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun MainScreen(navController: NavHostController) {
    Bars(navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Bars(navController: NavHostController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Barra superior
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.secondary
            ),
            title = {
                Text(
                    text = stringResource(R.string.app_name),
                    color = MaterialTheme.colorScheme.secondary,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 25.sp
                )
            },
            actions = {
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Filled.AccountCircle,
                        contentDescription = "Account icon"
                    )
                }
            }
        )

        // Contenido principal
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            Adaptive(navController)  // Pasamos el navController aquí
        }

        // Barra inferior
        BottomAppBar(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary),
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.secondary
        ) {
            IconButton(
                modifier = Modifier.weight(1f),
                onClick = { navController.navigate("mainScreen") },
            ) {
                Icon(imageVector = Icons.Filled.Home, contentDescription = "Home")
            }
            IconButton(
                modifier = Modifier.weight(1f),
                onClick = { navController.navigate("searchScreen") },
            ) {
                Icon(imageVector = Icons.Filled.Search, contentDescription = "Search")
            }
            IconButton(
                modifier = Modifier.weight(1f),
                onClick = { navController.navigate("notifScreen") },
            ) {
                Icon(imageVector = Icons.Filled.Notifications, contentDescription = "Notifications")
            }
            IconButton(
                modifier = Modifier.weight(1f),
                onClick = { navController.navigate("settingsScreen") },
            ) {
                Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu")
            }
        }
    }
}

@Composable
fun Adaptive(navController: NavHostController) {  // Recibe navController como parámetro
    var windowSize = currentWindowAdaptiveInfo().windowSizeClass
    var height = currentWindowAdaptiveInfo().windowSizeClass.windowHeightSizeClass
    var width = currentWindowAdaptiveInfo().windowSizeClass.windowWidthSizeClass

    // Botón para navegar a ExploreScreen
    Button(
        onClick = {
            // Usamos navController aquí
            navController.navigate("exploreScreen")
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = "Explorar Recetas", style = MaterialTheme.typography.bodyLarge)
    }
    // Botón para navegar a recomended
    Button(
        onClick = {
            // Usamos navController aquí
            navController.navigate("recommendedScreen")
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = "Recomendadas", style = MaterialTheme.typography.bodyLarge)
    }

    // Resto del contenido adaptativo
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        val arrayCard = arrayOf(
            MenuModel(1, "Pizza tradicional", "El sabor de Italia en tu horno 🍕", R.drawable.pizza),
        )
        val arrayView = arrayOf(
            MenuModel(1, "Enchiladas verdes", "Disfruta la pura tradición mexicana! 🇲🇽", R.drawable.enchis),
            MenuModel(2, "Mole poblano", "Un manjar de muchos ingredientes", R.drawable.mole)
        )

        if (width == WindowWidthSizeClass.COMPACT) {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 200.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) {
                items(arrayCard) { item ->
                    MainViewExCard(item.id, item.title, item.text, item.image)
                }
            }
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 160.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1.8f)
            ) {
                items(arrayView) { item ->
                    MainView(item.id, item.title, item.text, item.image)
                }
            }
        } else if (height == WindowHeightSizeClass.COMPACT) {
            LazyColumn {
                items(arrayView) { item ->
                    MainView(item.id, item.title, item.text, item.image)
                }
            }
        }
    }
}