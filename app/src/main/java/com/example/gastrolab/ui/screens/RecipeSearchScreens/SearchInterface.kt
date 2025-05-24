package com.example.gastrolab.ui.screens.RecipeSearchScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.clasetrabajo.data.viewmodel.RecipeViewModel
import com.example.gastrolab.R
import com.example.gastrolab.data.model.RecipeModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun SearchInterface(
    navController: NavHostController,
    viewModel: RecipeViewModel = viewModel()
) {
    var searchText by remember { mutableStateOf("") }
    var showBottomSheet by remember { mutableStateOf(false) }
    var recipes by remember { mutableStateOf<List<RecipeModel>>(emptyList()) }

    var selectedDifficulties by remember { mutableStateOf(setOf<String>()) }
    var selectedCategories by remember { mutableStateOf(setOf<String>()) }
    var selectedTimeLimits by remember { mutableStateOf(setOf<String>()) }
    var selectedLikeRates by remember { mutableStateOf(setOf<Int>()) }



    val filteredRecipes = recipes.filter { recipe ->
        val matchesSearch = recipe.title.contains(searchText, ignoreCase = true) ||
                recipe.description.contains(searchText, ignoreCase = true)

        val matchesDifficulty = selectedDifficulties.isEmpty() || selectedDifficulties.contains(recipe.difficulty)
        val matchesCategory = selectedCategories.isEmpty() || selectedCategories.contains(recipe.category)
        val matchesLike = selectedLikeRates.isEmpty() || selectedLikeRates.any { recipe.likerate >= it }
        val matchesTime = selectedTimeLimits.isEmpty() || selectedTimeLimits.any {
            try {
                val (h, m, s) = recipe.preparetime.split(":").map { it.toInt() }
                val recipeSeconds = h * 3600 + m * 60 + s
                val (fh, fm, fs) = it.split(":").map { it.toInt() }
                val filterSeconds = fh * 3600 + fm * 60 + fs
                recipeSeconds <= filterSeconds
            } catch (e: Exception) {
                true
            }
        }

        matchesSearch && matchesDifficulty && matchesCategory && matchesLike && matchesTime
    }


    LaunchedEffect(Unit) {
        viewModel.getRecipes { response ->
            if (response.isSuccessful) {
                recipes = response.body() ?: emptyList()
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(MaterialTheme.colorScheme.background)
    ) {
        TopAppBar(
            modifier = Modifier.height(80.dp),
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.secondary
            ),
            title = {
                val gastroGradient = listOf(
                    MaterialTheme.colorScheme.tertiary,
                    MaterialTheme.colorScheme.surface,
                    MaterialTheme.colorScheme.tertiary
                )
                Text(
                    text = stringResource(R.string.app_name),
                    style = TextStyle(brush = Brush.verticalGradient(colors = gastroGradient)),
                    fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 25.sp
                )
            },
            actions = {
                IconButton(onClick = { navController.navigate("accountScreen") }) {
                    Icon(imageVector = Icons.Filled.AccountCircle, contentDescription = "Account icon")
                }
            }
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = searchText,
                onValueChange = {
                    searchText = it
                    viewModel.searchRecipes(it) { response ->
                        if (response.isSuccessful) {
                            recipes = response.body() ?: emptyList()
                        }
                    }
                },
                label = { Text("Buscar") },
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { showBottomSheet = true }) {
                Text("Filtros")
            }
        }

        Text(
            text = if (searchText.isBlank()) "Recomendaciones" else "Resultados",
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp)
        ) {
            items(filteredRecipes.take(6)) { recipe ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .clickable { navController.navigate("recipeScreen/${recipe.id}") },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(
                        model = recipe.imageURL,
                        contentDescription = recipe.title,
                        modifier = Modifier
                            .size(120.dp)
                            .clip(RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.Crop,
                        error = painterResource(R.drawable.generic)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = recipe.title,
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.weight(1f)
                    )
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "Ver más",
                        tint = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier
                            .clickable { navController.navigate("recipeScreen/${recipe.id}") }
                            .padding(8.dp)
                    )
                }
            }
        }

        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = { showBottomSheet = false }
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxHeight(0.85f)
                        .padding(16.dp)
                ) {
                    item {
                        Text("Filtrar por dificultad", style = MaterialTheme.typography.titleMedium)
                        Spacer(Modifier.height(8.dp))
                        FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            listOf("Muy facil", "Facil", "Medio", "Dificil").forEach { difficulty ->
                                val selected = selectedDifficulties.contains(difficulty)
                                FilterChip(
                                    selected = selected,
                                    onClick = {
                                        selectedDifficulties = if (selected) {
                                            selectedDifficulties - difficulty
                                        } else {
                                            selectedDifficulties + difficulty
                                        }
                                    },
                                    label = { Text(difficulty) }
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                    }

                    item {
                        Text("Filtrar por categoría", style = MaterialTheme.typography.titleMedium)
                        Spacer(Modifier.height(8.dp))
                        FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            listOf("Mexicanas", "Italianas", "Orientales", "Saludables", "Estadounidenses", "Postres").forEach { category ->
                                val selected = selectedCategories.contains(category)
                                FilterChip(
                                    selected = selected,
                                    onClick = {
                                        selectedCategories = if (selected) {
                                            selectedCategories - category
                                        } else {
                                            selectedCategories + category
                                        }
                                    },
                                    label = { Text(category) }
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                    }

                    item {
                        Text("Tiempo máximo de preparación", style = MaterialTheme.typography.titleMedium)
                        Spacer(Modifier.height(8.dp))
                        FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            listOf("00:15:00", "00:30:00", "01:00:00", "01:30:00", "02:00:00").forEach { time ->
                                val selected = selectedTimeLimits.contains(time)
                                FilterChip(
                                    selected = selected,
                                    onClick = {
                                        selectedTimeLimits = if (selected) {
                                            selectedTimeLimits - time
                                        } else {
                                            selectedTimeLimits + time
                                        }
                                    },
                                    label = { Text("$time o menos") }
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                    }

                    item {
                        Text("Valoración mínima", style = MaterialTheme.typography.titleMedium)
                        Spacer(Modifier.height(8.dp))
                        FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            listOf(25, 50, 75, 90).forEach { rate ->
                                val selected = selectedLikeRates.contains(rate)
                                FilterChip(
                                    selected = selected,
                                    onClick = {
                                        selectedLikeRates = if (selected) {
                                            selectedLikeRates - rate
                                        } else {
                                            selectedLikeRates + rate
                                        }
                                    },
                                    label = { Text("$rate+") }
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(24.dp))
                    }

                    item {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Button(
                                onClick = {
                                    selectedDifficulties = emptySet()
                                    selectedCategories = emptySet()
                                    selectedTimeLimits = emptySet()
                                    selectedLikeRates = emptySet()
                                }
                            ) {
                                Text("Limpiar filtros")
                            }
                            Button(onClick = { showBottomSheet = false }) {
                                Text("Aplicar")
                            }
                        }
                    }
                }
            }
        }




        BottomAppBar(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp),
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ) {
            IconButton(modifier = Modifier.weight(1f), onClick = { navController.navigate("mainScreen") }) {
                Icon(imageVector = Icons.Filled.Home, contentDescription = "Home")
            }
            IconButton(modifier = Modifier.weight(1f), onClick = { navController.navigate("searchScreen") }) {
                Icon(imageVector = Icons.Filled.Search, contentDescription = "Search")
            }
            IconButton(modifier = Modifier.weight(1f), onClick = { navController.navigate("notifScreen") }) {
                Icon(imageVector = Icons.Filled.Notifications, contentDescription = "Notifications")
            }
            IconButton(modifier = Modifier.weight(1f), onClick = { navController.navigate("settingsScreen") }) {
                Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu")
            }
        }
    }
}
