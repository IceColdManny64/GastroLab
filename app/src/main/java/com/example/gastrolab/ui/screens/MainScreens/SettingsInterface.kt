package com.example.gastrolab.ui.screens.MainScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Apps
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.LockPerson
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.ReportProblem
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.SupportAgent
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.window.core.layout.WindowHeightSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
import com.example.clasetrabajo.data.model.MenuModel
import com.example.gastrolab.R
import com.example.gastrolab.data.model.SettingsModel
import com.example.gastrolab.ui.components.MainViewExCard
import com.example.gastrolab.ui.components.SettingsList
import com.example.gastrolab.ui.screens.TroubleshootScreens.Report

@Composable
fun SettingsInterface(navController: NavHostController) {
    BarsSettings(navController)
}

@OptIn(ExperimentalMaterial3Api::class)

@Composable

fun BarsSettings(navController: NavHostController) {
    Column(

        modifier = Modifier

            .fillMaxSize(),

    ) {

//can use MediumTopAppBar and other similar components to change the top bar size.

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
                    text = stringResource(R.string.settings_header),
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
        Column(

            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)

        ) {
            SettingsAdaptive(navController)
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
fun SettingsAdaptive(navController: NavHostController) {
    var height = currentWindowAdaptiveInfo().windowSizeClass.windowHeightSizeClass
    var width = currentWindowAdaptiveInfo().windowSizeClass.windowWidthSizeClass

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)

    ) {


        val settingsArray = arrayOf(
            SettingsModel(1, "Configuración de Aplicación", "Realiza cambios a tu cuenta, " +
                    "revisa opciones de notificaciones y accesibilidad", Icons.Filled.Apps),
            SettingsModel(2, "Privacidad y seguridad", "Cambia tu contraseña, borra tu cuenta o" +
                    " gestiona tu información", Icons.Filled.LockPerson),
            SettingsModel(3, "Soporte técnico", "Entra a un chat automático, o contacta a un experto para" +
                    "resolver un problema en la app", Icons.Filled.SupportAgent),
            SettingsModel(4, "Reporta un problema", "Reporta un error o problema general en la aplicacion." +
                    "Nos ayuda a mejorar!", Icons.Filled.ReportProblem)
        )

        if (width == WindowWidthSizeClass.COMPACT) {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 250.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1.1f),

                verticalArrangement = Arrangement.Center,
                horizontalArrangement = Arrangement.Center
            ) {
                items(settingsArray) { item ->
                    SettingsList(item.id, item.title, item.text, item.icon, navController)
                }
            }
    } else if (height == WindowHeightSizeClass.COMPACT) {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 400.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1.1f),

                verticalArrangement = Arrangement.Center,
                horizontalArrangement = Arrangement.Center
            ) {
                items(settingsArray) { item ->
                    SettingsList(item.id, item.title, item.text, item.icon, navController)
                }
            }
        }
    }
}


