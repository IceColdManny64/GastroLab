package com.example.gastrolab.ui.screens.MainScreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import com.example.gastrolab.R.string
import androidx.compose.material.icons.filled.Menu
import androidx.compose.ui.res.stringResource
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.gastrolab.R

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
        Text("Main Screen")
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
                    fontSize = 20.sp
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
        ) {
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