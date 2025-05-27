package com.example.gastrolab.ui.screens.ExploreNotifScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.clasetrabajo.data.viewmodel.RecipeViewModel
import com.example.gastrolab.R
import com.example.gastrolab.data.model.RecipeModel
import com.example.gastrolab.ui.theme.GastroLabTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleInterface(id: Int, navController: NavController) {
    val viewModel: RecipeViewModel = viewModel()
    var recipe by remember { mutableStateOf<RecipeModel?>(null) }

    LaunchedEffect(id) {
        viewModel.getRecipe(id) { response ->
            if (response.isSuccessful) {
                recipe = response.body()
            }
        }
    }

    GastroLabTheme {
        Scaffold(
            topBar = {
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
            },
            bottomBar = {
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
        ) { innerPadding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp)
                    .background(MaterialTheme.colorScheme.background),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Título del artículo
                item {
                    Text(
                        text = recipe?.title ?: "Artículo sobre recetas",
                        color = MaterialTheme.colorScheme.secondary,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }

                // Imagen principal
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp)
                            .background(MaterialTheme.colorScheme.onBackground),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(
                                ImageRequest.Builder(LocalContext.current)
                                    .data(recipe?.imageURL)
                                    .placeholder(R.drawable.generic)
                                    .error(R.drawable.generic)
                                    .build()
                            ),
                            contentDescription = "Imagen principal",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }

                // Texto del artículo principal
                item {
                    Text(
                        text = stringResource(id = R.string.article_description),
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                // Segunda sección: imagen secundaria y texto
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.Top
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = stringResource(id = R.string.secondary_description),
                                color = MaterialTheme.colorScheme.onBackground,
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Image(
                            painter = rememberAsyncImagePainter(
                                ImageRequest.Builder(LocalContext.current)
                                    .data(recipe?.imageURL)
                                    .placeholder(R.drawable.generic)
                                    .error(R.drawable.generic)
                                    .build()
                            ),
                            contentDescription = "Imagen secundaria",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(150.dp)
                                .fillMaxSize()
                                .background(MaterialTheme.colorScheme.surfaceVariant)
                        )
                    }
                }

                // Área para comentarios o notas
//                item {
//                    OutlinedTextField(
//                        value = "",
//                        onValueChange = {},
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .height(100.dp)
//                            .background(MaterialTheme.colorScheme.surface),
//                        shape = RoundedCornerShape(8.dp),
//                        placeholder = {
//                            Text(
//                                text = stringResource(id = R.string.comment_placeholder),
//                                color = MaterialTheme.colorScheme.onSurface
//                            )
//                        }
//                    )
//                }

                // Botones de acción
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Button(
                            onClick = {
                                recipe?.id?.let {
                                    navController.navigate("recipeScreen/$it")
                                }
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.primary
                            ),
                            modifier = Modifier.weight(1f).padding(start = 4.dp)
                        ) {
                            Text(
                                text = stringResource(R.string.view_recipe),
                                fontSize = 14.sp,
                                color = MaterialTheme.colorScheme.onSecondary
                            )
                        }
                    }
                }
            }
        }
    }
}
