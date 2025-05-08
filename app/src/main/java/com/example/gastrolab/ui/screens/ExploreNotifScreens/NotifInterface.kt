package com.example.gastrolab.ui.screens.ExploreNotifScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.clasetrabajo.data.viewmodel.RecipeViewModel
import com.example.gastrolab.R
import com.example.gastrolab.data.model.RecipeModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotifInterface(navController: NavHostController, viewModel: RecipeViewModel = viewModel()) {
    // Notificaciones de ejemplo con diseño mejorado
    val exampleNotifications = listOf(
        Notification(
            title = "Nueva recomendación",
            message = "Podria gustarte!",
            recipeId = 1,
            timestamp = "Hace 2 horas",
            isRead = true
        ),
        Notification(
            title = "Receta popular",
            message = "De las más vistas ahora mismo!",
            recipeId = 2,
            timestamp = "Ayer",
            isRead = false
        ),
        Notification(
            title = "De temporada",
            message = "Usa ingredientes frescos!",
            recipeId = 4,
            timestamp = "Ayer",
            isRead = false
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.height(50.dp),
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.secondary
                ),
                title = {
                    Text(
                        text = "Notificaciones",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    )
                },
                actions = {
                    IconButton(onClick = { navController.navigate("accountScreen") }) {
                        androidx.compose.material3.Icon(
                            imageVector = Icons.Filled.AccountCircle,
                            contentDescription = "Account icon",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ) {
                IconButton(modifier = Modifier.weight(1f), onClick = { navController.navigate("mainScreen") }) {
                    androidx.compose.material3.Icon(Icons.Filled.Home, contentDescription = null)
                }
                IconButton(modifier = Modifier.weight(1f), onClick = { navController.navigate("searchScreen") }) {
                    androidx.compose.material3.Icon(Icons.Filled.Search, contentDescription = null)
                }
                IconButton(modifier = Modifier.weight(1f), onClick = { navController.navigate("notifScreen") }) {
                    androidx.compose.material3.Icon(Icons.Filled.Notifications, contentDescription = null)
                }
                IconButton(modifier = Modifier.weight(1f), onClick = { navController.navigate("settingsScreen") }) {
                    androidx.compose.material3.Icon(Icons.Filled.Menu, contentDescription = null)
                }
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            items(exampleNotifications) { notification ->
                NotificationCard(
                    notification = notification,
                    viewModel = viewModel,
                    onClick = { navController.navigate("recipeScreen/${notification.recipeId}") }
                )
            }
        }
    }
}

@Composable
fun NotificationCard(
    notification: Notification,
    viewModel: RecipeViewModel,
    onClick: () -> Unit
) {
    var recipe by remember { mutableStateOf<RecipeModel?>(null) }

    LaunchedEffect(notification.recipeId) {
        viewModel.getRecipe(notification.recipeId) { response ->
            if (response.isSuccessful) recipe = response.body()
        }
    }

    if (notification.isRead){
        Text("Leida")
    }
    else {
        Text("No leida")
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { onClick() },
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = if (notification.isRead){
                MaterialTheme.colorScheme.onBackground
            }
            else {
                MaterialTheme.colorScheme.primary
            }
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {


        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = recipe?.imageURL ?: R.drawable.generic,
                contentDescription = "Imagen receta",
                modifier = Modifier
                    .size(64.dp)
                    .clip(MaterialTheme.shapes.small)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = notification.title,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSecondary
                )
                Text(
                    text = notification.message,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSecondary
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = notification.timestamp,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSecondary
                )
            }
        }
    }
}

// Modelo de notificación mejorado
data class Notification(
    val title: String,
    val message: String,
    val recipeId: Int,
    val timestamp: String,
    val isRead: Boolean
)