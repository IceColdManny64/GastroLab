package com.example.gastrolab.ui.screens.AccountScrens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.clasetrabajo.data.viewmodel.RecipeViewModel
import com.example.gastrolab.R
import com.example.gastrolab.data.model.RecipeModel
import com.example.gastrolab.data.model.SessionManager

@Composable
fun ProfileSection(navController: NavController) {
    val context = LocalContext.current
    val session = remember { SessionManager(context) }
    val email = session.getUserEmail()

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
            Text("Mi cuenta", fontWeight = FontWeight.Bold)
            Text(email, color = Color.Gray)
        }
        Spacer(modifier = Modifier.weight(1f))
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                modifier = Modifier
                    .clickable{
                        navController.navigate("favoritesScreen")
                    },
                imageVector = Icons.Filled.Favorite,
                contentDescription = "Favorites",
                tint = MaterialTheme.colorScheme.secondary
            )
            Text(text = "Favoritos",
                color = MaterialTheme.colorScheme.secondary,
                fontWeight = FontWeight.Bold,
                fontSize = 10.sp,
                textAlign = TextAlign.Center)
        }
    }

}



@Composable
fun RecentRecipesSection(recentRecipes: List<RecipeModel>, onRecipeClick: (Int) -> Unit) {
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
fun RecommendedRecipesSection(recommendedRecipes: List<RecipeModel>, onRecipeClick: (Int) -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text("Podría gustarte", fontWeight = FontWeight.Bold)
        if (recommendedRecipes.isEmpty()) {
            Text("No hay recomendaciones", color = Color.Gray)
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.height(300.dp)
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

    LaunchedEffect(Unit) {
        viewModel.getRecipes { response ->
            if (response.isSuccessful) {
                val allRecipes = response.body() ?: emptyList()
                recentRecipes = allRecipes.take(5)
                recommendedRecipes = allRecipes.shuffled().take(9)
                isLoading = false
            } else {
                error = "Failed to load recipes: ${response.code()}"
                isLoading = false
            }
        }
    }

    Scaffold(
        topBar = {         TopAppBar(
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
                Row() {
                    Image(
                        painter = painterResource(R.drawable.gastrolab),
                        contentDescription = ""
                    )
                    Text(
                        text = stringResource(R.string.app_name),
                        style = TextStyle(brush = Brush.verticalGradient(colors = gastroGradient)),
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 25.sp
                    )
                }
            },
            actions = {
                IconButton(onClick = { navController.navigate("accountScreen") }) {
                    Icon(imageVector = Icons.Filled.AccountCircle, contentDescription = "Account icon")
                }
            }
        ) },
        bottomBar = { BottomBar(navController) }
    ) { paddingValues ->
        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else if (error != null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Text(text = error!!, color = MaterialTheme.colorScheme.error)
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item { ProfileSection(navController) }
                item {
                    RecentRecipesSection(
                        recentRecipes = recentRecipes,
                        onRecipeClick = { id -> navController.navigate("recipeScreen/$id") }
                    )
                }
                item {
                    RecommendedRecipesSection(
                        recommendedRecipes = recommendedRecipes,
                        onRecipeClick = { id -> navController.navigate("recipeScreen/$id") }
                    )
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
            .height(80.dp),
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
