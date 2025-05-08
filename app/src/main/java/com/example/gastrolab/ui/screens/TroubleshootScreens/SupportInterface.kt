package com.example.gastrolab.ui.screens.TroubleshootScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gastrolab.R


@Composable
fun SupportInterface(navController: NavHostController) {

    BarsSupport(navController)


}

@Composable
fun Support(navController: NavHostController) {
    var message by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        // Título

        Text(
            color = MaterialTheme.colorScheme.onBackground,
            text = "Ayuda y Soporte",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Pregunta inicial
        Text(
            color = MaterialTheme.colorScheme.onBackground,
            text = "¿En qué necesitas que te ayudemos?",
            fontSize = 18.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )



        Column (
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(Color.Gray, shape = MaterialTheme.shapes.medium)
        ){
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp)

            ) {
                TextField(
                    value = message,
                    onValueChange = { message = it },
                    label = { Text("Escríbenos...", color = MaterialTheme.colorScheme.onBackground) }, // Texto negro
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.MailOutline,
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = MaterialTheme.colorScheme.onPrimary,
                        unfocusedTextColor = MaterialTheme.colorScheme.onPrimary
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Transparent)
                        .padding(0.dp)
                )

            }


            Spacer(modifier = Modifier.weight(1f))


            // Mensaje
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp, start = 10.dp, end = 10.dp)

            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .border(1.dp, Color.Black, shape = MaterialTheme.shapes.medium)
                        .background(Color.White, shape = MaterialTheme.shapes.medium),

                ) {

                    Text(
                        text = "¡Hola! ¿Necesitas ayuda?, escribe un mensaje.",
                        fontSize = 14.sp,
                        modifier = Modifier.padding(12.dp)
                    )

                }
                Spacer(modifier = Modifier.width(5.dp))

                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Default.Face,
                        contentDescription = "",
                        modifier = Modifier.size(50.dp)
                    )
                }
            }
        }




    }
}


@OptIn(ExperimentalMaterial3Api::class)

//@Preview

@Composable

fun BarsSupport(navController: NavHostController) {


    Column(

        modifier = Modifier

            .fillMaxSize()


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
            Support(navController)
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

