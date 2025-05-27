package com.example.gastrolab.ui.screens.RecipeSearchScreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.clasetrabajo.data.database.AppDatabase
import com.example.clasetrabajo.data.database.DatabaseProvider
import com.example.gastrolab.R
import com.example.gastrolab.data.model.RecipeEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocalRecipeInterface(id: Int, navController: NavHostController) {
    val db: AppDatabase = DatabaseProvider.getDatabase(LocalContext.current)
    val recipeDao = db.recipeDao()
    var recipeEntity by remember { mutableStateOf<RecipeEntity?>(null) }

    LaunchedEffect(id) {
        withContext(Dispatchers.IO) {
            recipeEntity = recipeDao.getById(id)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.height(80.dp),
                title = { Text("Receta Guardada") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Filled.ArrowBack, "Volver")
                    }
                }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState())
            ) {
                recipeEntity?.let { entity ->
                    ShowLocalRecipe(
                        navController = navController,
                        id = entity.id,
                        title = entity.title,
                        description = entity.description,
                        imageURL = entity.imageURL,
                        preparetime = entity.preparetime,
                        difficulty = entity.difficulty,
                        likerate = entity.likerate
                    )
                } ?: Text("Cargando...", modifier = Modifier.padding(16.dp))
            }
        }
    )
}

@Composable
fun ShowLocalRecipe(
    navController: NavController,
    id: Int,
    title: String,
    description: String,
    imageURL: String,
    preparetime: String,
    difficulty: String,
    likerate: Int
) {
    // Reutiliza la misma UI de ShowRecipe pero con datos locales
    AsyncImage(
        contentDescription = "",
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(max = 300.dp)
            .clip(RoundedCornerShape(8.dp)),
        contentScale = ContentScale.Crop,
        model = imageURL,
        error = painterResource(R.drawable.generic)
    )

    Spacer(modifier = Modifier.height(16.dp))
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = description,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.Face,
                contentDescription = "Reloj",
                tint = Color.White // Color del ícono blanco
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Tiempo de preparación: " + preparetime,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground // Color de texto blanco
            )
        }
    }

    Spacer(modifier = Modifier.height(20.dp))
    Text(
        text = "Dificultad: " + difficulty,
        fontWeight = FontWeight.Bold,
        fontSize = 15.sp,
        color = MaterialTheme.colorScheme.onBackground,
        modifier = Modifier.padding(horizontal = 16.dp)
    )
    Spacer(modifier = Modifier.height(24.dp))
    // Subtítulo "Ingredientes"
    Text(
        text = "Ingredientes",
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onBackground, // Color de texto negro
        modifier = Modifier.padding(horizontal = 16.dp)
    )

    Spacer(modifier = Modifier.height(8.dp))

    // Lista de ingredientes
    Text(
        text = """
                        - 500 g de pollo desmenuzado
                        - 12 tortillas de maíz
                        - 2 tazas de salsa verde
                        - 1 taza de crema ácida
                        - 1 taza de queso rallado
                        - 1 cebolla picada
                        - Aceite para freír
                    """.trimIndent(),
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.onBackground, // Color de texto
        modifier = Modifier.padding(horizontal = 16.dp)
    )

    Spacer(modifier = Modifier.height(24.dp))

    // Subtítulo "Preparación"
    Text(
        text = "Preparación",
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onBackground, // Color de texto negro
        modifier = Modifier.padding(horizontal = 16.dp)
    )

    Spacer(modifier = Modifier.height(8.dp))

    // Instrucciones de preparación
    Text(
        text = """
                        1. Calienta el aceite en una sartén y fríe las tortillas ligeramente.
                        2. Rellena cada tortilla con pollo desmenuzado y enrolla.
                        3. Coloca las enchiladas en un refractario y cubre con salsa verde.
                        4. Esparce la crema ácida y el queso rallado por encima.
                        5. Hornea a 180°C durante 20 minutos o hasta que estén doradas.
                        6. Sirve caliente y disfruta.
                    """.trimIndent(),
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.onBackground, // Color de texto negro
        modifier = Modifier.padding(horizontal = 16.dp)
    )
    // Botón para ver comentarios (en la parte inferior)
    Spacer(modifier = Modifier.height(16.dp))
}