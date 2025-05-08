package com.example.gastrolab.ui.screens.AccountScrens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
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
import com.example.gastrolab.ui.screens.RecipeSearchScreens.RecipeDetailComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesInterface(navController: NavHostController) {
    val windowInfo = currentWindowAdaptiveInfo().windowSizeClass
    val db: AppDatabase = DatabaseProvider.getDatabase(LocalContext.current)
    var recipeDao = db.recipeDao()
    var recipesdb by remember { mutableStateOf<List<RecipeEntity>>(emptyList()) }

    LaunchedEffect(Unit) {
        recipesdb = withContext(Dispatchers.IO) { recipeDao.getAll() }
    }
    val refreshList: suspend () -> Unit = {
        val updated = withContext(Dispatchers.IO) { recipeDao.getAll() }
        recipesdb = updated
    }

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.height(50.dp),
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
                        fontStyle = FontStyle.Italic,
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
        },
        bottomBar = {
            BottomAppBar(
                modifier = Modifier.height(50.dp),
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
    ) { paddingValues ->
        when (windowInfo.windowHeightSizeClass) {
            WindowHeightSizeClass.COMPACT -> LandscapeLayout(navController, recipesdb, recipeDao, refreshList)
            else -> PortraitLayout(navController, recipesdb, recipeDao, paddingValues, refreshList)
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
        ) {


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
        ) {
            Text(
                text = "Recetas sugeridas",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
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
            .padding(12.dp),
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
            maxLines = 1
        )
        Text(
            text = recipe.preparetime,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(4.dp)
        )
    }
}



@Composable
private fun PortraitLayout(
    navController: NavHostController,
    recipesdb: List<RecipeEntity>,
    recipeDao: RecipeDao,
    paddingValues: PaddingValues,
    refreshList: suspend () -> Unit
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
                                    refreshList()
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
    recipeDao: RecipeDao,
    refreshList: suspend () -> Unit
) {
    var selected by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                WindowInsets.systemBars
                    .only(WindowInsetsSides.Vertical)
                    .asPaddingValues()
            ),
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Columna de favoritos
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp)
            ) {
                if (!selected) {
                    if (recipesdb.isEmpty()) {
                        EmptyFavoritesMessage()
                    } else {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize().padding(20.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            items(recipesdb) { recipedb ->
                                FavoriteRecipesList(
                                    id = recipedb.id,
                                    title = recipedb.title ?: "",
                                    preparetime = recipedb.preparetime ?: "",
                                    imageURL = recipedb.imageURL ?: "",
                                    onItemClick = { selected = true },
                                    onDeleteClick = {
                                        CoroutineScope(Dispatchers.IO).launch {
                                            recipeDao.delete(recipedb)
                                            refreshList()
                                        }
                                    }
                                )
                            }
                        }
                    }
                } else {
                    // Mostrar botones cuando se selecciona algo
                    RecipeDetailComponent {
                        selected = false
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
                color = MaterialTheme.colorScheme.onBackground,
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