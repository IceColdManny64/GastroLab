package com.example.gastrolab.ui.screens.MainScreens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
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
import androidx.window.core.layout.WindowWidthSizeClass
import com.example.clasetrabajo.data.viewmodel.RecipeViewModel
import com.example.gastrolab.R
import com.example.gastrolab.data.model.RecipeModel
import com.example.gastrolab.ui.components.MainView
import com.example.gastrolab.ui.components.MainViewCompact
import com.example.gastrolab.ui.components.MainViewExCard
import com.example.gastrolab.ui.components.MainViewExCardCompact
import com.example.gastrolab.ui.components.MainViewSideCard
import com.example.gastrolab.ui.components.MainViewSideCardCompact
import com.example.gastrolab.ui.screens.ExploreNotifScreens.RecommendedInterface

@Composable
fun MainScreen(navController: NavHostController) {
    Bars(navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Bars(navController: NavHostController) {
    var selectedTabIndex by rememberSaveable { mutableIntStateOf(0) }
    val tabs = listOf("Explorar", "Recomendados")
    val composables = listOf<@Composable () -> Unit>(
        { Adaptive(navController) },
        { RecommendedInterface(navController) }
    )
    Column(modifier = Modifier.fillMaxSize()) {
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
        )

        Column {
            PrimaryTabRow(
                selectedTabIndex = selectedTabIndex,
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                divider = {},
                indicator = {
                    TabRowDefaults.PrimaryIndicator(
                        Modifier.tabIndicatorOffset(selectedTabIndex),
                        color = MaterialTheme.colorScheme.surface,
                        height = 3.dp
                    )
                }
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index },
                        text = { Text(
                            title,
                            color = if (selectedTabIndex == index) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.onSurface
                        ) }
                    )
                }
            }
        }

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
        ) {
            composables[selectedTabIndex]()
        }

        BottomAppBar(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp),
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
}


@Composable
fun Adaptive(navController: NavHostController, viewModel: RecipeViewModel = viewModel()) {
    val height = currentWindowAdaptiveInfo().windowSizeClass.windowHeightSizeClass
    val width = currentWindowAdaptiveInfo().windowSizeClass.windowWidthSizeClass
    var recipes by remember { mutableStateOf<List<RecipeModel>>(emptyList()) }
    var recipeDetail by remember { mutableStateOf<RecipeModel?>(null) }
    LaunchedEffect(Unit) {
        viewModel.getRecipes { response ->
            if (response.isSuccessful) {
                recipes = response.body() ?: emptyList()
            } else {
                Log.d("debug", "Failed to load recipes: ${response.code()}")
            }
        }
    }

    val listState = rememberLazyListState()



    Column(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {

        if (width == WindowWidthSizeClass.COMPACT) {
            TextButton(onClick = {}) {
                Text(
                    stringResource(R.string.popular_header),
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 25.sp
                )
                Icon(Icons.Filled.KeyboardArrowRight, contentDescription = "", tint = MaterialTheme.colorScheme.onBackground)
            }

            LazyHorizontalGrid(
                rows = GridCells.Adaptive(minSize = 110.dp),
                modifier = Modifier.fillMaxSize().weight(1.1f)
            ) {
                items(recipes.take(6)) { item ->
                    MainViewExCard(item.id, item.title, item.description, item.imageURL,
                        onButtonClick = {
                            viewModel.getRecipe(item.id){ response ->
                                if(response.isSuccessful){
                                    recipeDetail = response.body()
                                }
                                }
                            navController.navigate("articleScreen/${item.id}")
                        }
                    )
                }
            }

            TextButton(onClick = {}) {
                Text(
                    stringResource(R.string.featured_header),
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 25.sp
                )
                Icon(Icons.Filled.KeyboardArrowRight, contentDescription = "", tint = MaterialTheme.colorScheme.onBackground)
            }

            LazyRow(
                modifier = Modifier.padding(8.dp).weight(1.3f).height(25.dp)
            ) {
                items(recipes.drop(8).take(8)) { item ->
                    MainView(item.id, item.title, item.description, item.imageURL,
                        onButtonClick = {
                            viewModel.getRecipe(item.id){ response ->
                                if(response.isSuccessful){
                                    recipeDetail = response.body()
                                }
                            }
                            navController.navigate("recipeScreen/${item.id}")
                        })
                }
            }

            TextButton(onClick = {}) {
                Text(
                    stringResource(R.string.seasonal_header),
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 25.sp
                )
                Icon(Icons.Filled.KeyboardArrowRight, contentDescription = "", tint = MaterialTheme.colorScheme.onBackground)
            }

            LazyHorizontalGrid(
                rows = GridCells.Adaptive(minSize = 65.dp),
                modifier = Modifier.fillMaxSize().weight(1f)
            ) {
                items(recipes.drop(8).take(8)) { item ->
                    MainViewSideCard(item.id, item.title, item.description, item.imageURL,                         onButtonClick = {
                        viewModel.getRecipe(item.id){ response ->
                            if(response.isSuccessful){
                                recipeDetail = response.body()
                            }
                        }
                        navController.navigate("recipeScreen/${item.id}")
                    })
                }
            }

        } else if (height == WindowHeightSizeClass.COMPACT) {
            Row(
                modifier = Modifier.horizontalScroll(rememberScrollState()).fillMaxWidth()
            ) {
                Column(modifier = Modifier.fillMaxHeight().padding(horizontal = 8.dp)) {
                    TextButton(onClick = {}) {
                        Text(
                            stringResource(R.string.popular_header),
                            color = MaterialTheme.colorScheme.onBackground,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 25.sp
                        )
                        Icon(Icons.Filled.KeyboardArrowRight, contentDescription = "", tint = MaterialTheme.colorScheme.onBackground)
                    }
                    LazyColumn(modifier = Modifier
                        .width(350.dp) // o el ancho que necesites
                        .fillMaxHeight(), // o altura deseada
                        state = listState) {
                        items(recipes.take(6)) { item ->
                            MainViewExCardCompact(item.id, item.title, item.description, item.imageURL,                         onButtonClick = {
                                viewModel.getRecipe(item.id){ response ->
                                    if(response.isSuccessful){
                                        recipeDetail = response.body()
                                    }
                                }
                                navController.navigate("articleScreen/${item.id}")
                            })
                        }
                    }
                }

                Column(modifier = Modifier.fillMaxHeight().padding(horizontal = 8.dp)) {
                    TextButton(onClick = {}) {
                        Text(
                            stringResource(R.string.featured_header),
                            color = MaterialTheme.colorScheme.onBackground,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 25.sp
                        )
                        Icon(Icons.Filled.KeyboardArrowRight, contentDescription = "", tint = MaterialTheme.colorScheme.onBackground)
                    }
                    LazyColumn(
                        modifier = Modifier
                            .width(350.dp) // o el ancho que necesites
                            .fillMaxHeight(),
                    ) {
                        items(recipes.drop(6).take(6)) { item ->
                            MainViewCompact(item.id, item.title, item.description, item.imageURL,
                                onButtonClick = {
                                viewModel.getRecipe(item.id){ response ->
                                    if(response.isSuccessful){
                                        recipeDetail = response.body()
                                    }
                                }
                                navController.navigate("recipeScreen/${item.id}")
                            }
                            )
                        }
                    }
                }

                Column(modifier = Modifier.fillMaxHeight().padding(horizontal = 8.dp)) {
                    TextButton(onClick = {}) {
                        Text(
                            stringResource(R.string.seasonal_header),
                            color = MaterialTheme.colorScheme.onBackground,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 25.sp
                        )
                        Icon(Icons.Filled.KeyboardArrowRight, contentDescription = "", tint = MaterialTheme.colorScheme.onBackground)
                    }
                    LazyColumn(modifier = Modifier.width(350.dp) // o el ancho que necesites
                        .fillMaxHeight(),) {
                        items(recipes.drop(8).take(8)) { item ->
                            MainViewSideCardCompact(item.id, item.title, item.description, item.imageURL,
                                onButtonClick = {
                                viewModel.getRecipe(item.id){ response ->
                                    if(response.isSuccessful){
                                        recipeDetail = response.body()
                                    }
                                }
                                navController.navigate("recipeScreen/${item.id}")
                            })
                        }
                    }
                }
            }
        } else if (width == WindowWidthSizeClass.EXPANDED) {
            Row(
                modifier = Modifier.horizontalScroll(rememberScrollState()).fillMaxWidth()
            ) {
                Column(modifier = Modifier.fillMaxHeight().padding(horizontal = 8.dp)) {
                    TextButton(onClick = {}) {
                        Text(
                            stringResource(R.string.popular_header),
                            color = MaterialTheme.colorScheme.onBackground,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 25.sp
                        )
                        Icon(Icons.Filled.KeyboardArrowRight, contentDescription = "", tint = MaterialTheme.colorScheme.onBackground)
                    }
                    LazyColumn(modifier = Modifier
                        .width(350.dp) // o el ancho que necesites
                        .fillMaxHeight(), // o altura deseada
                        state = listState) {
                        items(recipes.take(6)) { item ->
                            MainViewExCardCompact(item.id, item.title, item.description, item.imageURL,                         onButtonClick = {
                                viewModel.getRecipe(item.id){ response ->
                                    if(response.isSuccessful){
                                        recipeDetail = response.body()
                                    }
                                }
                                navController.navigate("articleScreen/${item.id}")
                            })
                        }
                    }
                }

                Column(modifier = Modifier.fillMaxHeight().padding(horizontal = 8.dp)) {
                    TextButton(onClick = {}) {
                        Text(
                            stringResource(R.string.featured_header),
                            color = MaterialTheme.colorScheme.onBackground,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 25.sp
                        )
                        Icon(Icons.Filled.KeyboardArrowRight, contentDescription = "", tint = MaterialTheme.colorScheme.onBackground)
                    }
                    LazyColumn(
                        modifier = Modifier
                            .width(350.dp) // o el ancho que necesites
                            .fillMaxHeight(),
                    ) {
                        items(recipes.drop(6).take(6)) { item ->
                            MainViewCompact(item.id, item.title, item.description, item.imageURL,
                                onButtonClick = {
                                    viewModel.getRecipe(item.id){ response ->
                                        if(response.isSuccessful){
                                            recipeDetail = response.body()
                                        }
                                    }
                                    navController.navigate("recipeScreen/${item.id}")
                                }
                            )
                        }
                    }
                }

                Column(modifier = Modifier.fillMaxHeight().padding(horizontal = 8.dp)) {
                    TextButton(onClick = {}) {
                        Text(
                            stringResource(R.string.seasonal_header),
                            color = MaterialTheme.colorScheme.onBackground,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 25.sp
                        )
                        Icon(Icons.Filled.KeyboardArrowRight, contentDescription = "", tint = MaterialTheme.colorScheme.onBackground)
                    }
                    LazyColumn(modifier = Modifier.width(350.dp) // o el ancho que necesites
                        .fillMaxHeight(),) {
                        items(recipes.drop(8).take(8)) { item ->
                            MainViewSideCardCompact(item.id, item.title, item.description, item.imageURL,
                                onButtonClick = {
                                    viewModel.getRecipe(item.id){ response ->
                                        if(response.isSuccessful){
                                            recipeDetail = response.body()
                                        }
                                    }
                                    navController.navigate("recipeScreen/${item.id}")
                                })
                        }
                    }
                }
            }
        } else if (height == WindowHeightSizeClass.EXPANDED) {
            Row(
                modifier = Modifier.horizontalScroll(rememberScrollState()).fillMaxWidth()
            ) {
                Column(modifier = Modifier.fillMaxHeight().padding(horizontal = 8.dp)) {
                    TextButton(onClick = {}) {
                        Text(
                            stringResource(R.string.popular_header),
                            color = MaterialTheme.colorScheme.onBackground,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 25.sp
                        )
                        Icon(Icons.Filled.KeyboardArrowRight, contentDescription = "", tint = MaterialTheme.colorScheme.onBackground)
                    }
                    LazyColumn(modifier = Modifier
                        .width(350.dp) // o el ancho que necesites
                        .fillMaxHeight(), // o altura deseada
                        state = listState) {
                        items(recipes.take(6)) { item ->
                            MainViewExCardCompact(item.id, item.title, item.description, item.imageURL,                         onButtonClick = {
                                viewModel.getRecipe(item.id){ response ->
                                    if(response.isSuccessful){
                                        recipeDetail = response.body()
                                    }
                                }
                                navController.navigate("articleScreen/${item.id}")
                            })
                        }
                    }
                }

                Column(modifier = Modifier.fillMaxHeight().padding(horizontal = 8.dp)) {
                    TextButton(onClick = {}) {
                        Text(
                            stringResource(R.string.featured_header),
                            color = MaterialTheme.colorScheme.onBackground,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 25.sp
                        )
                        Icon(Icons.Filled.KeyboardArrowRight, contentDescription = "", tint = MaterialTheme.colorScheme.onBackground)
                    }
                    LazyColumn(
                        modifier = Modifier
                            .width(350.dp) // o el ancho que necesites
                            .fillMaxHeight(),
                    ) {
                        items(recipes.drop(8).take(8)) { item ->
                            MainViewCompact(item.id, item.title, item.description, item.imageURL,
                                onButtonClick = {
                                    viewModel.getRecipe(item.id){ response ->
                                        if(response.isSuccessful){
                                            recipeDetail = response.body()
                                        }
                                    }
                                    navController.navigate("recipeScreen/${item.id}")
                                }
                            )
                        }
                    }
                }

                Column(modifier = Modifier.fillMaxHeight().padding(horizontal = 8.dp)) {
                    TextButton(onClick = {}) {
                        Text(
                            stringResource(R.string.seasonal_header),
                            color = MaterialTheme.colorScheme.onBackground,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 25.sp
                        )
                        Icon(Icons.Filled.KeyboardArrowRight, contentDescription = "", tint = MaterialTheme.colorScheme.onBackground)
                    }
                    LazyColumn(modifier = Modifier.width(350.dp) // o el ancho que necesites
                        .fillMaxHeight(),) {
                        items(recipes.drop(8).take(8)) { item ->
                            MainViewSideCardCompact(item.id, item.title, item.description, item.imageURL,
                                onButtonClick = {
                                    viewModel.getRecipe(item.id){ response ->
                                        if(response.isSuccessful){
                                            recipeDetail = response.body()
                                        }
                                    }
                                    navController.navigate("recipeScreen/${item.id}")
                                })
                        }
                    }
                }
            }
        }
    }
}





