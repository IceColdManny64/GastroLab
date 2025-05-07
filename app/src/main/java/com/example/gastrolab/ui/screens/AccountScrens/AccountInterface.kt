package com.example.gastrolab.ui.screens.AccountScrens

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
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.window.core.layout.WindowHeightSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.clasetrabajo.data.viewmodel.RecipeViewModel
import com.example.gastrolab.R
import com.example.gastrolab.data.model.RecipeModel

@Composable
fun ProfileSection() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.account_circle),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .background(Color.Gray)
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
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(
            onClick = { navController.navigate("favoritesScreen") },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Text("Favoritos", color = MaterialTheme.colorScheme.onPrimary)
        }
    }
}

@Composable
fun RecentRecipesSection(
    recentRecipes: List<RecipeModel>,
    onRecipeClick: (Int) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text("Recetas vistas últimamente", fontWeight = FontWeight.Bold)
        if (recentRecipes.isEmpty()) {
            Text("No hay recetas recientes", color = Color.Gray)
        } else {
            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                items(recentRecipes.take(5)) { recipe ->
                    RecipeItem(recipe = recipe, onRecipeClick = { onRecipeClick(recipe.id) })
                }
            }
        }
    }
}

@Composable
fun RecommendedRecipesSection(
    recommendedRecipes: List<RecipeModel>,
    onRecipeClick: (Int) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text("Podría gustarte", fontWeight = FontWeight.Bold)
        if (recommendedRecipes.isEmpty()) {
            Text("No hay recomendaciones", color = Color.Gray)
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(recommendedRecipes.take(9)) { recipe ->
                    RecipeItem(recipe = recipe, onRecipeClick = { onRecipeClick(recipe.id) })
                }
            }
        }
    }
}

@Composable
fun RecipeItem(recipe: RecipeModel, onRecipeClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable { onRecipeClick() }
    ) {
        Image(
            painter = rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current)
                    .data(recipe.imageURL)
                    .placeholder(R.drawable.generic)
                    .error(R.drawable.generic)
                    .build()
            ),
            contentDescription = recipe.title,
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(8.dp))
        )
        Text(
            text = recipe.title,
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            maxLines = 1
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountInterface(navController: NavHostController) {
    val viewModel: RecipeViewModel = viewModel()
    var recentRecipes by remember { mutableStateOf<List<RecipeModel>>(emptyList()) }
    var recommendedRecipes by remember { mutableStateOf<List<RecipeModel>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var error by remember { mutableStateOf<String?>(null) }

    val height = currentWindowAdaptiveInfo().windowSizeClass.windowHeightSizeClass
    val width = currentWindowAdaptiveInfo().windowSizeClass.windowWidthSizeClass

    LaunchedEffect(Unit) {
        viewModel.getRecipes { response ->
            if (response.isSuccessful) {
                val allRecipes = response.body() ?: emptyList()
                recentRecipes = allRecipes.take(5) // Simulate recently viewed
                recommendedRecipes = allRecipes.shuffled().take(9) // Simulate recommendations
                isLoading = false
            } else {
                error = "Failed to load recipes: ${response.code()}"
                isLoading = false
            }
        }
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Cuenta") }) },
        bottomBar = { BottomBar(navController) }
    ) { paddingValues ->
        if (isLoading) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }
        } else if (error != null) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = error!!, color = MaterialTheme.colorScheme.error)
            }
        } else {
            if (width == WindowWidthSizeClass.COMPACT) {
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
                    RecentRecipesSection(
                        recentRecipes = recentRecipes,
                        onRecipeClick = { id -> navController.navigate("recipeScreen/$id") }
                    )
                    RecommendedRecipesSection(
                        recommendedRecipes = recommendedRecipes,
                        onRecipeClick = { id -> navController.navigate("recipeScreen/$id") }
                    )
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
                        ProfileSection()
                        SearchBar()
                        ActionButtons(navController)
                    }
                    Column(
                        modifier = Modifier.weight(2f),
                    ) {
                        RecentRecipesSection(
                            recentRecipes = recentRecipes,
                            onRecipeClick = { id -> navController.navigate("recipeScreen/$id") }
                        )
                        RecommendedRecipesSection(
                            recommendedRecipes = recommendedRecipes,
                            onRecipeClick = { id -> navController.navigate("recipeScreen/$id") }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    BottomAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary
    ) {
        IconButton(
            modifier = Modifier.weight(1f),
            onClick = { navController.navigate("mainScreen") }
        ) {
            Icon(imageVector = Icons.Filled.Home, contentDescription = "")
        }
        IconButton(
            modifier = Modifier.weight(1f),
            onClick = { navController.navigate("searchScreen") }
        ) {
            Icon(imageVector = Icons.Filled.Search, contentDescription = "")
        }
        IconButton(
            modifier = Modifier.weight(1f),
            onClick = { navController.navigate("notifScreen") }
        ) {
            Icon(imageVector = Icons.Filled.Notifications, contentDescription = "")
        }
        IconButton(
            modifier = Modifier.weight(1f),
            onClick = { navController.navigate("settingsScreen") }
        ) {
            Icon(imageVector = Icons.Filled.Menu, contentDescription = "")
        }
    }
}



