package com.example.gastrolab.ui.screens.TroubleshootScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
            .padding(start = 16.dp, end = 16.dp)
            .verticalScroll(rememberScrollState())
            .imePadding()


    ) {
        Text(
            text = "Reportar problema.",
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .padding(top = 20.dp)
        )


        Spacer(modifier = Modifier.height(20.dp))

        // Descripción breve
        Text(
            color = MaterialTheme.colorScheme.onBackground,
            text = buildAnnotatedString {
                append("Descripción breve del problema:")
                withStyle(style = TextStyle(color = Color.Red).toSpanStyle()) {
                    append("*")
                }
            },
        )
        TextField(
            value = shortDescription,
            onValueChange = { shortDescription = it },
            label = { Text("Ejemplo: problema iniciando sesión") },
            colors = TextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.onPrimary,
                unfocusedTextColor = MaterialTheme.colorScheme.onPrimary
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(40.dp))

        // Descripción detallada
        Text(
            color = MaterialTheme.colorScheme.onBackground,
            text = buildAnnotatedString {
                append("Descripción detallada del problema:")
                withStyle(style = TextStyle(color = Color.Red).toSpanStyle()) {
                    append("*")
                }
            },
        )
        TextField(
            value = detailedDescription,
            onValueChange = { detailedDescription = it },
            label = { Text("Detalles del problema") },
            colors = TextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.onPrimary,
                unfocusedTextColor = MaterialTheme.colorScheme.onPrimary
            ),
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
                color = MaterialTheme.colorScheme.onBackground,
                text = "Adjuntar video o imagen del problema (opcional):",
                modifier = Modifier.weight(1f),
            )
            Button(
                onClick = { },
                modifier = Modifier.width(180.dp),

            ) {
                Text("Seleccionar archivo", fontSize = 14.sp, color = MaterialTheme.colorScheme.onSecondary)
            }
        }

        Spacer(modifier = Modifier.height(46.dp))

        Button(
            onClick = { },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Enviar", color = MaterialTheme.colorScheme.onSecondary)
        }

        Spacer(modifier = Modifier.height(50.dp))


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


