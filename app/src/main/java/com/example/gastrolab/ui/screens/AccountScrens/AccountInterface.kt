package com.example.gastrolab.ui.screens.AccountScrens

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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.gastrolab.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountInterface(navController: NavHostController) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Cuenta") }) },
        bottomBar = { BottomNavigationBar(navController) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            ProfileSection()
            SearchBar()
            SegmentedButtons(navController)
            RecentRecipesSection()
            RecipeCountSection()
        }
    }
}//accountInterface

@Composable
fun ProfileSection() {
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        Image(
            painter = painterResource(id = R.drawable.account_circle),
            contentDescription = "Profile Picture",
            modifier = Modifier.size(50.dp).clip(CircleShape).background(Color.Gray)
        )
        Column {
            Text("Nombre_Público_Usuario", fontWeight = FontWeight.Bold)
            Text("@Nombre_Usuario", color = Color.Gray)
        }
        Spacer(modifier = Modifier.weight(1f))
        IconButton(onClick = {  }) {//boton edicion de perfil
            Icon(Icons.Default.Edit, contentDescription = "Editar perfil")
        }
    }
}

@Composable
fun SearchBar() {
    TextField(
        value = "",
        onValueChange = {},
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search Icon") },
        placeholder = { Text("En Tu Colección") },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun SegmentedButtons(navController: NavHostController) {
    var selectedOption by remember { mutableStateOf("Guardadas") }
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        listOf("Guardadas", "Favoritas").forEach { option ->
            Button(
                onClick = {
                    selectedOption = option
                    when (option) {
                        "Guardadas" -> navController.navigate("saved")
                        "Favoritas" -> navController.navigate("favorites")
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (selectedOption == option) Color.Blue else Color.LightGray
                )
            ) {
                Text(option, color = Color.White)
            }
        }
    }
}

@Composable
fun RecentRecipesSection() {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text("Recetas vistas últimamente", fontWeight = FontWeight.Bold)
        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(5) {
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .background(Color.LightGray)
                ) {
                    Icon(Icons.Default.AccountBox, contentDescription = "Recipe Image")
                }
            }
        }
    }
}

@Composable
fun RecipeCountSection() {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text("Número de recetas", fontWeight = FontWeight.Bold)
        LazyVerticalGrid(columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(9) {
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .background(Color.LightGray)
                ) {
                    Icon(Icons.Default.AccountBox, contentDescription = "Recipe Image")
                }
            }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    BottomAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(MaterialTheme.colorScheme.primary),
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.secondary
    ) {
        IconButton(
            modifier = Modifier.weight(1f),
            onClick = { navController.navigate("mainScreen") }
        ) {
            Icon(imageVector = Icons.Filled.Home, contentDescription = "Inicio")
        }
        IconButton(
            modifier = Modifier.weight(1f),
            onClick = { navController.navigate("searchScreen") }
        ) {
            Icon(imageVector = Icons.Filled.Search, contentDescription = "Buscar")
        }
        IconButton(
            modifier = Modifier.weight(1f),
            onClick = { navController.navigate("notifScreen") }
        ) {
            Icon(imageVector = Icons.Filled.Notifications, contentDescription = "Notificaciones")
        }
        IconButton(
            modifier = Modifier.weight(1f),
            onClick = { navController.navigate("settingsScreen") }
        ) {
            Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menú")
        }
    }
}
