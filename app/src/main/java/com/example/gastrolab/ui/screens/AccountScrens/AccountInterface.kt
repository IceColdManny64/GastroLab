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
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
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
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.gastrolab.R
import com.example.gastrolab.ui.screens.MainScreens.Adaptive

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountInterface(navController: NavHostController) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Cuenta") }) },
        bottomBar = { Bars(navController) }
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
            ActionButtons(navController)
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
        IconButton(onClick = { /* Acción de editar perfil */ }) {
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
fun ActionButtons(navController: NavHostController) {
    var selectedOption by remember { mutableStateOf("Guardadas") }
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(
            onClick = { navController.navigate("savedScreen") },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Text("Guardados", color = MaterialTheme.colorScheme.onPrimary)
        }
        Button(
            onClick = { navController.navigate("favoritesScreen") },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Text("Favoritos", color = MaterialTheme.colorScheme.onPrimary)
        }
    }
}

@Composable
fun RecentRecipesSection() {
    val recipes = listOf(
        Pair(R.drawable.enchis, "Enchiladas"),
        Pair(R.drawable.hamburg, "Hamburguesa"),
        Pair(R.drawable.mole, "Mole"),
        Pair(R.drawable.pastor, "Tacos"),
        Pair(R.drawable.pizza, "Pizza")
    )
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text("Recetas vistas últimamente", fontWeight = FontWeight.Bold)
        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(recipes) { (imageRes, name) ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = painterResource(id = imageRes),
                        contentDescription = name,
                        modifier = Modifier.size(80.dp).clip(RoundedCornerShape(8.dp))
                    )
                    Text(name, fontSize = 12.sp, textAlign = TextAlign.Left)
                }
            }
        }
    }
}

@Composable
fun RecipeCountSection() {
    val recipes = listOf(
        Pair(R.drawable.tamal, "Tamales"),
        Pair(R.drawable.sincro, "Sincronizadas"),
        Pair(R.drawable.pizza, "Pizza"),
        Pair(R.drawable.pastor, "Tacos"),
        Pair(R.drawable.mole, "Mole"),
        Pair(R.drawable.hamburg, "Hamburguesa"),
        Pair(R.drawable.enchis, "Enchiladas"),
        Pair(R.drawable.tamal, "Tamales"),
        Pair(R.drawable.sincro, "Sincronizadas")
    )
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text("Número de recetas", fontWeight = FontWeight.Bold)
        LazyVerticalGrid(columns = GridCells.Fixed(3), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(recipes) { (imageRes, name) ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = painterResource(id = imageRes),
                        contentDescription = name,
                        modifier = Modifier.size(100.dp).clip(RoundedCornerShape(8.dp))
                    )
                    Text(name, fontSize = 12.sp, textAlign = TextAlign.Center)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Bars(navController: NavHostController) {
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
}

