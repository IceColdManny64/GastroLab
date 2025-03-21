package com.example.gastrolab.ui.screens.MainScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material3.adaptive.currentWindowSize
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Menu
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.DpSize
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.MultiChoiceSegmentedButtonRow
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.colorResource
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
import com.example.gastrolab.ui.components.MainViewSideCard
import com.example.gastrolab.ui.components.MainView
import com.example.gastrolab.ui.components.MainViewExCard
import com.example.gastrolab.ui.components.MainViewSideCard
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun MainScreen(navController: NavHostController){
    Bars(navController)

}
@OptIn(ExperimentalMaterial3Api::class)
//@Preview
@Composable
fun Bars(navController: NavHostController) {

    Column(
        modifier = Modifier
            .fillMaxSize()

    ) {
        //can use MediumTopAppBar and other similar components to change the top bar size.
        TopAppBar(
            modifier = Modifier
                .height(50.dp),
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.secondary
            ),
            title = {
                val gastroGradient= listOf(MaterialTheme.colorScheme.surface
                    , MaterialTheme.colorScheme.surface,
                    MaterialTheme.colorScheme.onTertiary,
                    MaterialTheme.colorScheme.onTertiary)
                Text(
                    text = stringResource(R.string.app_name),
                    style = TextStyle(brush = Brush.linearGradient(colors = gastroGradient)),
                    color = MaterialTheme.colorScheme.secondary,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 25.sp
                )},
            actions = {
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Filled.AccountCircle,
                        contentDescription = "Account icon"
                    )
                }
            }
        )
        TopAppBar(
            modifier = Modifier
                .height(50.dp),
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.secondary
            ),
            title = {
                val gastroGradient= listOf(MaterialTheme.colorScheme.surface
                    , MaterialTheme.colorScheme.surface,
                    MaterialTheme.colorScheme.onTertiary,
                    MaterialTheme.colorScheme.onTertiary)
                Text(
                    text = stringResource(R.string.app_name),
                    style = TextStyle(brush = Brush.linearGradient(colors = gastroGradient)),
                    color = MaterialTheme.colorScheme.secondary,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 25.sp
                )},
            actions = {
                    var selectedIndex by remember { mutableIntStateOf(0) }
                    val sOptions = listOf("Explorar", "Recomendados")
                    val destinations = listOf("mainScreen", "recommendedScreen") // Rutas de navegación

                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        SingleChoiceSegmentedButtonRow(
                            modifier = Modifier.clip(ShapeDefaults.ExtraSmall)
                        ) {
                            sOptions.forEachIndexed { index, label ->
                                SegmentedButton(
                                    shape = SegmentedButtonDefaults.itemShape(index, sOptions.size),
                                    onClick = {
                                        selectedIndex = index
                                        navController.navigate(destinations[index]) // Navega a la vista seleccionada
                                    },
                                    selected = index == selectedIndex,
                                    label = { Text(label) },
                                    colors = SegmentedButtonDefaults.colors(
                                        activeContainerColor = MaterialTheme.colorScheme.secondary, // Color del botón activo
                                        activeContentColor = MaterialTheme.colorScheme.secondaryContainer, // Texto en blanco cuando está activo
                                        inactiveContainerColor = MaterialTheme.colorScheme.background, // Color cuando no está seleccionado
                                        inactiveContentColor = MaterialTheme.colorScheme.surface // Texto negro cuando está inactivo
                                    )
                                )
                            }
                        }
                    }
            }
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)

        ) {
            Adaptive()
        }
        BottomAppBar(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(MaterialTheme.colorScheme.primary),
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.secondary

        ) {
            IconButton(
                modifier = Modifier
                    .weight(1f),
                onClick = {navController.navigate("mainScreen")},
            ) {
                Icon(imageVector = Icons.Filled.Home, contentDescription = "")
            }
            IconButton(
                modifier = Modifier
                    .weight(1f),
                onClick = {navController.navigate("searchScreen")},
            ) {
                Icon(imageVector = Icons.Filled.Search, contentDescription = "")
            }
            IconButton(
                modifier = Modifier
                    .weight(1f),
                onClick = {navController.navigate("notifScreen")},
            ) {
                Icon(imageVector = Icons.Filled.Notifications, contentDescription = "")
            }
            IconButton(
                modifier = Modifier
                    .weight(1f),
                onClick = {navController.navigate("settingsScreen")},
            ) {
                Icon(imageVector = Icons.Filled.Menu, contentDescription = "")
            }
            }
        }
    }

@Composable
fun Adaptive(){
    var windowSize = currentWindowAdaptiveInfo().windowSizeClass
    var height = currentWindowAdaptiveInfo().windowSizeClass.windowHeightSizeClass
    var width = currentWindowAdaptiveInfo().windowSizeClass.windowWidthSizeClass

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        val arrayCard = arrayOf(
            MenuModel(1, "Pizza tradicional", "El sabor de italia en tu horno 🇮🇹! ", R.drawable.pizza)
        )
        val arrayView = arrayOf(
            MenuModel(1, "Enchiladas verdes", "Disfruta la pura tradición mexicana! 🇲🇽", R.drawable.enchis),
            MenuModel(2, "Mole poblano", "Un manjar de muchos ingredientes", R.drawable.mole)
        )
        val arraySide = arrayOf(
            MenuModel(1, "Tamales oaxaqueños", "Llega el sabor de Oaxaca a tu mesa!", R.drawable.tamal),
            MenuModel(2, "Tacos al pastor", "Un manjar galardonado globalmente", R.drawable.pastor),
            MenuModel(3, "Hamburguesas de pollo", "¿Sin res en casa? ¿Y si las pruebas?", R.drawable.hamburg),
            MenuModel(4, "Sincronizadas", "¿Traes prisa? Lo simple nunca falla!", R.drawable.sincro)
        )

        if(width == WindowWidthSizeClass.COMPACT) {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 200.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) {
                items(arrayCard) { item ->
                    MainViewExCard(item.id, item.title, item.text, item.image)
                }
            }
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 160.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) {
                items(arrayView) { item ->
                    MainView(item.id, item.title, item.text, item.image)
                }
            }
            LazyHorizontalGrid(
                rows = GridCells.Adaptive(minSize = 80.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) {
                items(arraySide) { item ->
                    MainViewSideCard(item.id, item.title, item.text, item.image)
                }
            }
        } else if (height == WindowHeightSizeClass.COMPACT) {
            LazyColumn {
                items(arrayView) { item -> MainView(item.id, item.title, item.text, item.image) }
            }
            LazyColumn {
                items(arrayCard) { item -> MainViewExCard(item.id, item.title, item.text, item.image) }
            }
            LazyColumn {
                items(arraySide) { item -> MainViewSideCard(item.id, item.title, item.text, item.image) }
            }
        }
    }
}


