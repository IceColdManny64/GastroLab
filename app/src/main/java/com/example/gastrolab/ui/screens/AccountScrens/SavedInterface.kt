package com.example.gastrolab.ui.screens.AccountScrens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.getValue
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.mutableStateOf
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.window.core.layout.WindowHeightSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
import com.example.gastrolab.R
import com.example.gastrolab.ui.screens.MainScreens.Bars

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SavedInterface(navController: NavHostController) {
    val height = currentWindowAdaptiveInfo().windowSizeClass.windowHeightSizeClass
    val width = currentWindowAdaptiveInfo().windowSizeClass.windowWidthSizeClass

    Scaffold(
        topBar = { TopAppBar(modifier = Modifier.height(80.dp), title = { Text("Guardados") }) },
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
                SavedRecipe()
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
                }
                Column(
                    modifier = Modifier.weight(2f).verticalScroll(rememberScrollState())
                ) {
                    SavedRecipe()
                }
            }
        }
    }
}

@Composable
fun SavedRecipe() {
    val recipes = listOf(
        Triple(R.drawable.flan, "Flan", "15 min"),
        Triple(R.drawable.huevmx, "Huevo a la mexicana", "10 min"),
        Triple(R.drawable.kebab, "Kebab", "45 min"),
        Triple(R.drawable.pasta, "Pasta", "1 h 5 min"),
        Triple(R.drawable.brownie, "Brownie", "1 h 30 m"),
        Triple(R.drawable.chiles_rellenos, "Chiles Rellenos", "30 m")
    )
    Column {//contador por cada receta
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
                        Icon(Icons.Default.Done, contentDescription = "Favorito")
                    }
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
                trailingIcon = {Icons.Default.ArrowDropDown }
            )
        }
        IconButton(onClick = {}) {
            Icon(Icons.Default.List, contentDescription = "Filter")
        }
    }
}
