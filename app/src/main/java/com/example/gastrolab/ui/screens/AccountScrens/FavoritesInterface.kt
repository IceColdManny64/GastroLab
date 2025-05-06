package com.example.gastrolab.ui.screens.AccountScrens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.window.core.layout.WindowHeightSizeClass
import coil.compose.AsyncImage
import com.example.clasetrabajo.data.dao.RecipeDao
import com.example.clasetrabajo.data.database.AppDatabase
import com.example.clasetrabajo.data.database.DatabaseProvider
import com.example.clasetrabajo.data.viewmodel.RecipeViewModel
import com.example.gastrolab.R
import com.example.gastrolab.data.model.RecipeEntity
import com.example.gastrolab.data.model.RecipeModel
import com.example.gastrolab.ui.screens.MainScreens.Bars
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesInterface(navController: NavHostController) {
    val windowInfo = currentWindowAdaptiveInfo().windowSizeClass
    val db: AppDatabase = DatabaseProvider.getDatabase(LocalContext.current)
    val recipeDao = db.recipeDao()
    var recipesdb by remember { mutableStateOf<List<RecipeEntity>>(emptyList()) }

    LaunchedEffect(Unit) {
        recipesdb = withContext(Dispatchers.IO) { recipeDao.getAll() }
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Favoritos") }) },
        bottomBar = { Bars(navController) }
    ) { paddingValues ->
        when (windowInfo.windowHeightSizeClass) {
            WindowHeightSizeClass.COMPACT -> LandscapeLayout(navController, recipesdb, recipeDao)
            else -> PortraitLayout(navController, recipesdb, recipeDao, paddingValues)
        }
    }
}

@Composable
private fun FavoritesListContent(
    recipesdb: List<RecipeEntity>,
    recipeDao: RecipeDao,
    navController: NavHostController
) {
    if (recipesdb.isEmpty()) {
        EmptyFavoritesMessage()
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(recipesdb) { recipedb ->
                FavoriteRecipesList(
                    id = recipedb.id,
                    title = recipedb.title ?: "",
                    preparetime = recipedb.preparetime ?: "",
                    imageURL = recipedb.imageURL ?: "",
                    onItemClick = { navController.navigate("localRecipeScreen/${recipedb.id}") },
                    onDeleteClick = {
                        CoroutineScope(Dispatchers.IO).launch {
                            recipeDao.delete(recipedb)
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun SuggestedRecipesH(
    navController: NavHostController,
    isLandscape: Boolean = false,
    viewModel: RecipeViewModel = viewModel()
) {
    var suggestedRecipes by remember { mutableStateOf<List<RecipeModel>>(emptyList()) }

    LaunchedEffect(Unit) {
        viewModel.getRecipes { response ->
            suggestedRecipes = response.body()?.shuffled()?.take(6) ?: emptyList()
        }
    }

    if (isLandscape) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp)
        ) {
            Text(
                text = "Sugerencias",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.tertiary,
                modifier = Modifier.padding(bottom = 2.dp)
            )

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(suggestedRecipes) { recipe ->
                    LandscapeSuggestedItem(recipe, navController)
                }
            }
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
                .padding(vertical = 5.dp)
        ) {
            Text(
                text = "Recetas sugeridas",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.tertiary,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
            ) {
                items(suggestedRecipes) { recipe ->
                    SuggestedRecipeItem(recipe, navController)
                }
            }
        }
    }
}

@Composable
fun LandscapeSuggestedItem(recipe: RecipeModel, navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { navController.navigate("recipeScreen/${recipe.id}") }
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        AsyncImage(
            model = recipe.imageURL,
            contentDescription = recipe.title,
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop,
            error = painterResource(R.drawable.generic)
        )
        Text(
            text = recipe.title,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            maxLines = 2
        )
        Text(
            text = recipe.preparetime,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
fun FavoriteRecipesList(
    id: Int,
    title: String,
    preparetime: String,
    imageURL: String,
    onDeleteClick: () -> Unit,
    onItemClick: () -> Unit // Nuevo parámetro
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onItemClick() }, // ¡Aquí está el cambio!
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Row(verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = imageURL,
                contentDescription = "imagen receta",
                modifier = Modifier.size(100.dp)
            )
            Column(modifier = Modifier.padding(start = 8.dp)) {
                Text(title, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Text(preparetime, fontSize = 12.sp)
            }
        }
        IconButton(onClick = {
            onDeleteClick()
        }) {
            Icon(
                imageVector = Icons.Filled.DeleteForever,
                contentDescription = "Delete"
            )
        }
    }
}

@Composable
fun SuggestedRecipeItem(recipe: RecipeModel, navController: NavHostController) {
    Column(
        modifier = Modifier
            .width(160.dp)
            .clickable { navController.navigate("recipeScreen/${recipe.id}") },
        horizontalAlignment = Alignment.CenterHorizontally
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
        Text(
            text = recipe.title,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            maxLines = 1,
            modifier = Modifier.padding(top = 4.dp)
        )
        Text(
            text = recipe.preparetime,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}



@Composable
private fun PortraitLayout(
    navController: NavHostController,
    recipesdb: List<RecipeEntity>,
    recipeDao: RecipeDao,
    paddingValues: PaddingValues
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        Box(modifier = Modifier.weight(1f)) {
            if (recipesdb.isEmpty()) {
                EmptyFavoritesMessage()
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(recipesdb) { recipedb ->
                        FavoriteRecipesList(
                            id = recipedb.id,
                            title = recipedb.title ?: "",
                            preparetime = recipedb.preparetime ?: "",
                            imageURL = recipedb.imageURL ?: "",
                            onItemClick = { navController.navigate("localRecipeScreen/${recipedb.id}") },
                            onDeleteClick = {
                                CoroutineScope(Dispatchers.IO).launch {
                                    recipeDao.delete(recipedb)
                                }
                            }
                        )
                    }
                }
            }
        }

        SuggestedRecipesH(
            navController = navController,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )
    }
}

@Composable
private fun LandscapeLayout(
    navController: NavHostController,
    recipesdb: List<RecipeEntity>,
    recipeDao: RecipeDao
) {
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Columna de favoritos
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 8.dp)
        ) {
            if (recipesdb.isEmpty()) {
                EmptyFavoritesMessage()
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(recipesdb) { recipedb ->
                        FavoriteRecipesList(
                            id = recipedb.id,
                            title = recipedb.title ?: "",
                            preparetime = recipedb.preparetime ?: "",
                            imageURL = recipedb.imageURL ?: "",
                            onItemClick = { navController.navigate("localRecipeScreen/${recipedb.id}") },
                            onDeleteClick = {
                                CoroutineScope(Dispatchers.IO).launch {
                                    recipeDao.delete(recipedb)
                                }
                            }
                        )
                    }
                }
            }
        }

        // Columna de sugerencias
        Column(
            modifier = Modifier
                .weight(1f)
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(8.dp)
        ) {
            Text(
                text = "Sugerencias",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 2.dp)
            )
            SuggestedRecipesH(navController = navController, isLandscape = true)
        }
    }
}

@Composable
fun SuggestedRecipesH(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    isLandscape: Boolean = false,
    viewModel: RecipeViewModel = viewModel()
) {
    var suggestedRecipes by remember { mutableStateOf<List<RecipeModel>>(emptyList()) }

    LaunchedEffect(Unit) {
        viewModel.getRecipes { response ->
            suggestedRecipes = response.body()?.shuffled()?.take(6) ?: emptyList()
        }
    }

    if (isLandscape) {
        LazyColumn(
            modifier = modifier.fillMaxSize()
                .background(MaterialTheme.colorScheme.onTertiary),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(suggestedRecipes) { recipe ->
                LandscapeSuggestedItem(recipe, navController)
            }
        }
    } else {
        Column(
            modifier = modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(vertical = 5.dp)
        ) {
            Text(
                text = "Recetas sugeridas",
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
            ) {
                items(suggestedRecipes) { recipe ->
                    SuggestedRecipeItem(recipe, navController)
                }
            }
        }
    }
}

@Composable
private fun EmptyFavoritesMessage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Default.List,
            contentDescription = null,
            modifier = Modifier.size(64.dp),
            tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.6f)
        )
        Text(
            text = "No tienes recetas favoritas",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
            modifier = Modifier.padding(top = 16.dp)
        )
        Text(
            text = "Agrega recetas desde la sección de exploración",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.4f),
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

// Resto de componentes (FavoriteRecipesList, SuggestedRecipeItem, LandscapeSuggestedItem) se mantienen igual que en tu código original