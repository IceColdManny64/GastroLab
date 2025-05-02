package com.example.gastrolab.ui.screens.AccountScrens

import android.util.Log
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
import androidx.compose.material.icons.filled.DeleteForever
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.window.core.layout.WindowHeightSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
import coil.compose.AsyncImage
import com.example.clasetrabajo.data.database.AppDatabase
import com.example.clasetrabajo.data.database.DatabaseProvider
import com.example.gastrolab.R
import com.example.gastrolab.data.model.RecipeEntity
import com.example.gastrolab.data.model.RecipeModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesInterface(navController: NavHostController) {
    val height = currentWindowAdaptiveInfo().windowSizeClass.windowHeightSizeClass
    val width = currentWindowAdaptiveInfo().windowSizeClass.windowWidthSizeClass

    val db: AppDatabase = DatabaseProvider.getDatabase(LocalContext.current)
    val recipeDao = db.recipeDao()
    var recipesdb by remember { mutableStateOf<List<RecipeEntity>>(emptyList()) }
    var recipeDetail by remember { mutableStateOf<RecipeModel?>(null) }

    LaunchedEffect(Unit) {
        recipesdb = withContext(Dispatchers.IO) {
            recipeDao.getAll()
        }
    }

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
                }
                val listState = rememberLazyListState()
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    state = listState
                ) {
                    items(recipesdb) { recipedb ->
                        FavoriteRecipesList(
                            recipedb?.id ?: 0,
                            recipedb?.title ?: "",
                            recipedb?.preparetime ?: "",
                            recipedb?.imageURL ?: "",
                            onDeleteClick = {
                                CoroutineScope(Dispatchers.IO).launch {
                                    try {
                                        recipeDao.delete(recipedb)
                                        //update the consult so the deleted element can disappear from screen immediately
                                        recipesdb = withContext(Dispatchers.IO) {
                                            recipeDao.getAll()
                                        }
                                        Log.d("debug-db", "Account deleted successfully")
                                    } catch (exception: Exception) {
                                        Log.d("debug-db", "ERROR: $exception")
                                    }
                                }
                            }
                        )
                    }
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
fun FavoriteRecipesList(
    id: Int,
    title: String,
    preparetime: String,
    imageURL: String,
    onDeleteClick:() -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
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
                Text(title, fontWeight = FontWeight.Bold)
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