package com.example.gastrolab.ui.screens.AccountScrens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.window.core.layout.WindowHeightSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
import com.example.gastrolab.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesInterface(navController: NavHostController) {
    val height = currentWindowAdaptiveInfo().windowSizeClass.windowHeightSizeClass
    val width = currentWindowAdaptiveInfo().windowSizeClass.windowWidthSizeClass

    Scaffold(
        topBar = { TopAppBar(title = { Text("Favoritos") }) },
        bottomBar = { Bars(navController) }
    ) { paddingValues ->
        if (width == WindowWidthSizeClass.COMPACT) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                FilterSection()
                FavoriteRecipesList()
                SuggestedRecipesH()
            }
        } else if (height == WindowHeightSizeClass.COMPACT) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Column(
                    modifier = Modifier.weight(2f),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    FilterSection()
                    FavoriteRecipesList()
                }
                Column(
                    modifier = Modifier.weight(2f)
                ) {
                    SuggestedRecipesH()
                }
            }
        }
    }
}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterSection() {
    val selectedFilter by remember { mutableStateOf("Todo") }
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ExposedDropdownMenuBox(expanded = false, onExpandedChange = {}) {
            TextField(
                value = selectedFilter,
                onValueChange = {},
                readOnly = true,
                modifier = Modifier.fillMaxWidth(0.8f),
                trailingIcon = { Icon(Icons.Default.ArrowDropDown, contentDescription = "Dropdown") }
            )
        }
        IconButton(onClick = {}) {
            Icon(Icons.Default.List, contentDescription = "Filter")
        }
    }
}

@Composable
fun FavoriteRecipesList() {
    val recipes = listOf(
        Triple(R.drawable.smoothie, "Smoothie", "1 h 05 min"),
        Triple(R.drawable.lasagna, "Lasagna", "20 min"),
        Triple(R.drawable.sopes, "Sopes", "30 min"),
        Triple(R.drawable.tiramisu, "Tiramisu Cake", "3 h 40 min")
    )
    Column {
        Text("${recipes.size} Recetas", fontWeight = FontWeight.Bold)
        recipes.forEach { (image, name, time) ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = image),
                        contentDescription = "imagen receta",
                        modifier = Modifier.size(100.dp)
                    )
                    Column(modifier = Modifier.padding(start = 8.dp)) {
                        Text(name, fontWeight = FontWeight.Bold)
                        Text(time, fontSize = 12.sp)
                    }
                }
                Row {
                    IconButton(onClick = { }) {
                        Icon(Icons.Default.Favorite, contentDescription = "Favorito")
                    }
                }
            }
        }
    }
}

@Composable
fun SuggestedRecipesH() {
    val recipes = listOf(
        Triple(R.drawable.tamal, "Tamales", "45 min"),
        Triple(R.drawable.sincro, "Sincronizadas", "15 min"),
        Triple(R.drawable.pizza, "Pizza", "1 h"),
        Triple(R.drawable.tacos, "Tacos", "30 min"),
        Triple(R.drawable.mole, "Mole", "2 h"),
        Triple(R.drawable.hamburg, "Hamburguesa", "40 min"),
        Triple(R.drawable.enchiladas, "Enchiladas", "35 min")
    )
    Column {
        Text("Encuentra más recetas", fontWeight = FontWeight.Bold)
        LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            items(recipes) { (image, name, time) ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = painterResource(id = image),
                        contentDescription = name,
                        modifier = Modifier
                            .size(120.dp)
                            .clip(RoundedCornerShape(8.dp))
                    )
                    Text(name, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Text(time, fontSize = 12.sp, color = Color.Gray)
                }
            }
        }
    }
}