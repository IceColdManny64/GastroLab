package com.example.gastrolab.ui.screens.TroubleshootScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.gastrolab.R
import com.example.gastrolab.ui.screens.MainScreens.Adaptive
import com.example.gastrolab.ui.screens.MainScreens.Adaptive2
import com.example.gastrolab.ui.screens.MainScreens.Bars
import com.example.gastrolab.ui.theme.GastroLabTheme

@Composable
fun ReportarProblemaInterface(navController: NavHostController) {

    BarsReportarProblema(navController)


}

@Composable
fun Report(navController: NavHostController){
    var shortDescription by remember { mutableStateOf("") }
    var detailedDescription by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(start = 16.dp, top = 70.dp, end = 16.dp, bottom = 80.dp)
            .verticalScroll(rememberScrollState())
            .imePadding()


    ) {
        Text(
            text = "Reportar problema.",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .padding(top = 40.dp)
        )


        Spacer(modifier = Modifier.height(20.dp))

        // Descripción breve
        Text(
            text = buildAnnotatedString {
                append("Descripción breve del problema:")
                withStyle(style = TextStyle(color = Color.Red).toSpanStyle()) {
                    append("*")
                }
            }
        )
        TextField(
            value = shortDescription,
            onValueChange = { shortDescription = it },
            label = { Text("Ejemplo: problema iniciando sesión") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(40.dp))

        // Descripción detallada
        Text(
            text = buildAnnotatedString {
                append("Descripción detallada del problema:")
                withStyle(style = TextStyle(color = Color.Red).toSpanStyle()) {
                    append("*")
                }
            }
        )
        TextField(
            value = detailedDescription,
            onValueChange = { detailedDescription = it },
            label = { Text("Detalles del problema") },
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
        )

        Spacer(modifier = Modifier.height(50.dp))

        // Adjuntar archivo con botón a la derecha
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Adjuntar video o imagen del problema (opcional):",
                modifier = Modifier.weight(1f)
            )
            Button(
                onClick = { },
                modifier = Modifier.width(180.dp)
            ) {
                Text("Seleccionar archivo", fontSize = 14.sp)
            }
        }

        Spacer(modifier = Modifier.height(46.dp))

        Button(
            onClick = { },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Enviar")
        }

        Spacer(modifier = Modifier.height(50.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { navController.navigate("mainScreen") }
        )
        {
            Text("Return to Main Menu")

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)

//@Preview

@Composable

fun BarsReportarProblema(navController: NavHostController) {


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
            Report(navController)
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


