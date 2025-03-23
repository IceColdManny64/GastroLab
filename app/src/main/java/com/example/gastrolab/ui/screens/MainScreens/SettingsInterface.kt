package com.example.gastrolab.ui.screens.MainScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.gastrolab.R
import com.example.gastrolab.ui.screens.TroubleshootScreens.Report

@Composable
fun SettingsInterface(navController: NavHostController) {
    BarsSettings(navController)

    Column {
        Button(
            onClick = {navController.navigate("reportProblem")}
        )
        {
            Text("Go to Report Screen")
        }
        Button(
            onClick = {navController.navigate("supportScreen")}
        )
        {
            Text("Go to Support Screen")
        }
    }


}

@OptIn(ExperimentalMaterial3Api::class)

//@Preview

@Composable

fun BarsSettings(navController: NavHostController) {


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

                val gastroGradient = listOf(
                    MaterialTheme.colorScheme.surface, MaterialTheme.colorScheme.tertiary,

                    MaterialTheme.colorScheme.onSurface,

                    MaterialTheme.colorScheme.onTertiary
                )

                Text(

                    text = stringResource(R.string.app_name),

                    style = TextStyle(brush = Brush.linearGradient(colors = gastroGradient)),

                    color = MaterialTheme.colorScheme.secondary,

                    fontStyle = FontStyle.Italic,

                    fontWeight = FontWeight.ExtraBold,

                    fontSize = 25.sp

                )
            },

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

                .background(MaterialTheme.colorScheme.background)


        ) {

        }

        BottomAppBar(

            modifier = Modifier

                .fillMaxWidth()

                .height(50.dp)

                .background(MaterialTheme.colorScheme.primary),

            containerColor = MaterialTheme.colorScheme.primary,

            contentColor = MaterialTheme.colorScheme.onPrimary


        ) {

            IconButton(

                modifier = Modifier

                    .weight(1f),

                onClick = { navController.navigate("mainScreen") },

                ) {

                Icon(imageVector = Icons.Filled.Home, contentDescription = "")

            }

            IconButton(

                modifier = Modifier

                    .weight(1f),

                onClick = { navController.navigate("searchScreen") },

                ) {

                Icon(imageVector = Icons.Filled.Search, contentDescription = "")

            }

            IconButton(

                modifier = Modifier

                    .weight(1f),

                onClick = { navController.navigate("notifScreen") },

                ) {

                Icon(imageVector = Icons.Filled.Notifications, contentDescription = "")

            }

            IconButton(

                modifier = Modifier

                    .weight(1f),

                onClick = { navController.navigate("settingsScreen") },

                ) {

                Icon(imageVector = Icons.Filled.Menu, contentDescription = "")

            }
        }
    }
}