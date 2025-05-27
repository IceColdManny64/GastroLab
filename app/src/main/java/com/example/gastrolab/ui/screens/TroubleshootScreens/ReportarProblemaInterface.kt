package com.example.gastrolab.ui.screens.TroubleshootScreens

import android.content.ActivityNotFoundException
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
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
fun Report(navController: NavHostController) {
    var shortDescription by rememberSaveable { mutableStateOf("") }
    var detailedDescription by rememberSaveable { mutableStateOf("") }
    val context = LocalContext.current

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
            modifier = Modifier.padding(top = 20.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Descripción breve
        Text(
            text = buildAnnotatedString {
                append("Descripción breve del problema:")
                withStyle(style = TextStyle(color = Color.Red).toSpanStyle()) {
                    append("*")
                }
            },
            color = MaterialTheme.colorScheme.onBackground
        )
        TextField(
            value = shortDescription,
            onValueChange = { shortDescription = it },
            label = { Text("Ejemplo: problema iniciando sesión") },
            colors = TextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.onBackground,
                unfocusedTextColor = MaterialTheme.colorScheme.onTertiary
            ),
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
            },
            color = MaterialTheme.colorScheme.onBackground
        )
        TextField(
            value = detailedDescription,
            onValueChange = { detailedDescription = it },
            label = { Text("Detalles del problema") },
            colors = TextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.onBackground,
                unfocusedTextColor = MaterialTheme.colorScheme.onTertiary
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
        )

        Spacer(modifier = Modifier.height(50.dp))

        Button(
            onClick = {
                // Validar campos no vacíos
                if (shortDescription.isBlank() || detailedDescription.isBlank()) {
                    Toast.makeText(
                        context,
                        "Todos los campos deben estar completos",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@Button
                }

                // Preparar Intent para Gmail
                val emailIntent = Intent(Intent.ACTION_SEND).apply {
                    type = "message/rfc822"
                    putExtra(Intent.EXTRA_EMAIL, arrayOf("soporte@tuempresa.com"))
                    putExtra(Intent.EXTRA_SUBJECT, "Problema: $shortDescription")
                    putExtra(Intent.EXTRA_TEXT, detailedDescription)
                    `package` = "com.google.android.gm"
                }

                try {
                    context.startActivity(emailIntent)
                } catch (e: ActivityNotFoundException) {
                    Toast.makeText(
                        context,
                        "No está instalada la app de Gmail",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            },
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


