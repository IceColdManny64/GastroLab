package com.example.gastrolab.ui.screens.MainScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material3.adaptive.currentWindowSize
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.window.core.layout.WindowHeightSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
import com.example.clasetrabajo.data.model.MenuModel
import com.example.gastrolab.R
import com.example.gastrolab.ui.components.CompactMainView
import com.example.gastrolab.ui.components.MainView
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun MainScreen(navController: NavHostController){

    Bars(navController)
    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ){

        Button(
            onClick = {navController.navigate("reportProblem")}
        )
        {
            Text("Go to Report Screen")
        }

    }
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
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary
            ),
            title = {
                Text(
                    text = stringResource(R.string.app_name),
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

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.onSecondary)
        ) {
            Adaptive()
        }
        BottomAppBar(
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
    //Compact width < 600dp Phone Portrait
    //Medium width >=600dp < 840dp Tablet Portraitv
    //Expanded Width >= 840dp Tablet Landscape

    //Compact Height < 480dp Phone Landscape
    //Medium Height >= 480dp < 900dp Tablet Landscape Phone Portrait
    // Expanded Height >= 900dp Tablet Portrait
    Column {
        val arrayPost = arrayOf(
            MenuModel(1, "Title 1", "Text 1", R.drawable.enchis),
            MenuModel(2, "Title 2", "Text 2", R.drawable.enchis),
            MenuModel(3, "Title 3", "Text 3", R.drawable.enchis),
            MenuModel(4, "Title 1", "Text 1", R.drawable.enchis)
        )
        if(width == WindowWidthSizeClass.COMPACT) {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 100.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) {
                items(arrayPost){
                        item -> MainView(item.id, item.title, item.text, item.image)
                }
            }

        } else if (height == WindowHeightSizeClass.COMPACT) {
            LazyColumn {
                items(arrayPost) { item -> MainView(item.id, item.title, item.text, item.image) }
            }
        }
    }

}