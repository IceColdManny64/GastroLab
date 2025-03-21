package com.example.gastrolab.ui.screens.TroubleshootScreens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.gastrolab.ui.screens.MainScreens.Bars
import com.example.gastrolab.ui.theme.GastroLabTheme

@Composable
fun ReportarProblemaInterface(navController: NavHostController) {

    Bars(navController)

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
