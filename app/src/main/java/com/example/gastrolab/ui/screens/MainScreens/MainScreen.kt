package com.example.gastrolab.ui.screens.MainScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.window.core.layout.WindowHeightSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
import com.example.clasetrabajo.data.model.MenuModel
import com.example.gastrolab.R
import com.example.gastrolab.ui.components.MainView
import com.example.gastrolab.ui.components.MainViewExCard
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
}


@Composable
fun Adaptive(navController: NavHostController) {
    var windowSize = currentWindowAdaptiveInfo().windowSizeClass
    var height = currentWindowAdaptiveInfo().windowSizeClass.windowHeightSizeClass
    var width = currentWindowAdaptiveInfo().windowSizeClass.windowWidthSizeClass

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        val arrayCard = arrayOf(
            MenuModel(1,"Un manjar de Italia: La Pizza",
                "Descubre algo más sobre\neste manjar y lleva el" +
                        "\nsabor de italia a tu horno 🇮🇹! ",
                R.drawable.pizza),
            MenuModel(2,"El kebab: brocheta al estilo Europeo ",
                "Tiene muchos estilos, y el sabor \n" +
                        " es delicioso en todos ellos! ",
                R.drawable.kebab,),
        )
        val arrayView = arrayOf(
            MenuModel(1, "Enchiladas verdes", "Disfruta la pura tradición mexicana! 🇲🇽", R.drawable.enchis),
            MenuModel(2, "Mole poblano", "Un manjar de muchos ingredientes", R.drawable.mole),
            MenuModel(3, "Enchiladas verdes", "Disfruta la pura tradición mexicana! 🇲🇽", R.drawable.enchis),
            MenuModel(4, "Mole poblano", "Un manjar de muchos ingredientes", R.drawable.mole)

        )
        val arraySide = arrayOf(
            MenuModel(1, "Tamales oaxaqueños", "Llega el sabor de Oaxaca a tu mesa!", R.drawable.tamal,  ),
            MenuModel(2, "Tacos al pastor", "Un manjar galardonado globalmente", R.drawable.pastor),
            MenuModel(3, "Hamburguesas de pollo", "¿Sin res en casa? ¿Con ganas de algo nuevo?", R.drawable.hamburg),
            MenuModel(4, "Sincronizadas", "¿Hoy traes prisa? Lo más simple nunca falla!", R.drawable.sincro),
            MenuModel(5, "Caldo de pollo", "¿Con frío? Esto te lo quitará!", R.drawable.caldo),
            MenuModel(6, "Huevos a la mexicana", "Con todo el estilo mexicano", R.drawable.huevmx)
        )

        if (width == WindowWidthSizeClass.COMPACT) {


            TextButton(
                onClick = {}

            ){
                Text(stringResource(R.string.popular_header),
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 25.sp)
                Icon(Icons.Filled.KeyboardArrowRight, contentDescription = "", tint = MaterialTheme.colorScheme.onPrimary)

            }
            LazyHorizontalGrid(
                rows = GridCells.Adaptive(minSize = 110.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1.1f)
            ) {
                items(arrayCard) { item ->
                    MainViewExCard(item.id, item.title, item.text, item.image, navController)
                }
            }
            TextButton(
                onClick = {},

                ){
                Text(stringResource(R.string.featured_header),
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 25.sp)
                Icon(Icons.Filled.KeyboardArrowRight, contentDescription = "", tint = MaterialTheme.colorScheme.onPrimary)

            }
            LazyRow(
                modifier = Modifier
                    .padding(8.dp)
                    .weight(1.1f)
                    .height(20.dp)
            ) {
                items(arrayView) { item ->
                    MainView(item.id, item.title, item.text, item.image, navController)
                }
            }

            TextButton(
                onClick = {},

                ){
                Text(stringResource(R.string.seasonal_header),
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 25.sp)
                Icon(Icons.Filled.KeyboardArrowRight, contentDescription = "", tint = MaterialTheme.colorScheme.onPrimary)

            }
            LazyHorizontalGrid(
                rows = GridCells.Adaptive(minSize =65.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) {
                items(arraySide) { item ->
                    MainViewSideCard(item.id, item.title, item.text, item.image, navController)
                }
            }
        } else if (height == WindowHeightSizeClass.COMPACT) {
            LazyHorizontalGrid(
                rows = GridCells.Adaptive(minSize = 80.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) {
                items(arraySide) { item ->
                    MainViewSideCardCompact(item.id, item.title, item.text, item.image)
                }
            }
        }
    }
}

@Composable
fun Adaptive2(navController: NavHostController) {
    var windowSize = currentWindowAdaptiveInfo().windowSizeClass
    var height = currentWindowAdaptiveInfo().windowSizeClass.windowHeightSizeClass
    var width = currentWindowAdaptiveInfo().windowSizeClass.windowWidthSizeClass

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        val arrayCard = arrayOf(
            MenuModel(1, "Pizza tradicional", "El sabor de italia en tu horno 🇮🇹! ", R.drawable.pizza),
            MenuModel(1, "Pizza tradicional", "Esta es la pantalla de recomendados! ", R.drawable.pizza),
        )
        val arrayView = arrayOf(
            MenuModel(1, "Enchiladas verdes", "Disfruta la pura tradición mexicana! 🇲🇽", R.drawable.enchis),
            MenuModel(2, "Mole poblano", "Un manjar de muchos ingredientes", R.drawable.mole)
        )
        val arraySide = arrayOf(
            MenuModel(1, "Tamales oaxaqueños", "Llega el sabor de Oaxaca a tu mesa!", R.drawable.tamal),
            MenuModel(2, "Tacos al pastor", "Un manjar galardonado globalmente", R.drawable.pastor),
            MenuModel(3, "Hamburguesas de pollo", "¿Sin res en casa? ¿Y si las pruebas?", R.drawable.hamburg),
            MenuModel(4, "Sincronizadas", "¿Traes prisa? Lo simple nunca falla!", R.drawable.sincro),
            MenuModel(5, "Caldo de pollo", "¿Con frío? Esto te lo quitará", R.drawable.caldo),
            MenuModel(6, "Huevos a la mexicana", "Con todo el estilo mexicano", R.drawable.huevmx)
        )

        if (width == WindowWidthSizeClass.COMPACT) {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 200.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) {
                items(arrayCard) { item ->
                    MainViewExCard(item.id, item.title, item.text, item.image, navController)
                }
            }
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 160.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) {
                items(arrayView) { item ->
                    MainView(item.id, item.title, item.text, item.image, navController)
                }
            }
            LazyHorizontalGrid(
                rows = GridCells.Adaptive(minSize = 80.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) {
                items(arraySide) { item ->
                    MainViewSideCard(item.id, item.title, item.text, item.image, navController)
                }
            }
        } else if (height == WindowHeightSizeClass.COMPACT) {

        }
    }
}
