package com.example.gastrolab

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowCompat
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.clasetrabajo.data.database.AppDatabase
import com.example.clasetrabajo.data.database.DatabaseProvider
import com.example.gastrolab.ui.screens.AccountScrens.AccountInterface
import com.example.gastrolab.ui.screens.AccountScrens.FavoritesInterface
import com.example.gastrolab.ui.screens.AccountScrens.OfflineInterface
import com.example.gastrolab.ui.screens.AccountScrens.SavedInterface
import com.example.gastrolab.ui.screens.ExploreNotifScreens.ArticleInterface
import com.example.gastrolab.ui.screens.ExploreNotifScreens.ExploreInterface
import com.example.gastrolab.ui.screens.ExploreNotifScreens.NotifInterface
import com.example.gastrolab.ui.screens.ExploreNotifScreens.RecommendedInterface
import com.example.gastrolab.ui.screens.LoginScreens.LoginInterface
import com.example.gastrolab.ui.screens.LoginScreens.LoginPasswordInterface
import com.example.gastrolab.ui.screens.LoginScreens.SignUpInterface
import com.example.gastrolab.ui.screens.MainScreens.MainScreen
import com.example.gastrolab.ui.screens.MainScreens.SettingsInterface
import com.example.gastrolab.ui.screens.MainScreens.UserMenuInterface
import com.example.gastrolab.ui.screens.RecipeSearchScreens.LocalRecipeInterface
import com.example.gastrolab.ui.screens.RecipeSearchScreens.RecipeInterface
import com.example.gastrolab.ui.screens.RecipeSearchScreens.SearchInterface
import com.example.gastrolab.ui.screens.TroubleshootScreens.PrivacyInterface
import com.example.gastrolab.ui.screens.TroubleshootScreens.ReportarProblemaInterface
import com.example.gastrolab.ui.screens.TroubleshootScreens.SupportInterface
import com.example.gastrolab.ui.screens.TroubleshootScreens.UpdateCredentialsInterface
import com.example.gastrolab.ui.theme.GastroLabTheme
import com.example.gastrolab.utils.NotificationHelper

class MainActivity : FragmentActivity() {
    lateinit var database: AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.statusBarColor = android.graphics.Color.TRANSPARENT
        NotificationHelper.createChannel(this)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requestPermissions(
                arrayOf(android.Manifest.permission.POST_NOTIFICATIONS),
                0
            )
        }

        try{
            database = DatabaseProvider.getDatabase(this)
            Log.d("debug-db", "DATABASE LOADED SUCCESSFULLY" )
        }catch (exception: Exception){
            Log.d("debug-db", "ERROR: $exception" )
        }
                //enableEdgeToEdge()
        setContent {
            GastroLabTheme {
                ComposeMultiScreenApp()
                }
            }
        }
    }
@Composable
fun ComposeMultiScreenApp(){
    val navController = rememberNavController()
    val context = LocalContext

    LaunchedEffect(Unit) {
        (context as? Activity)?.intent?.let { intent ->
            if (intent.getStringExtra("destination") == "favoritesScreen") {
                navController.navigate("favoritesScreen") {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        }
    }

    SetupNavGraph(navController = navController)
}
@Composable
fun SetupNavGraph(navController: NavHostController){
    //startDestinations marks which screen is going to open at launch
    NavHost(navController = navController, startDestination = "loginScreen"){
        //add route name for every screen
        composable("loginScreen"){ LoginInterface(navController) }
        composable("signUpScreen"){ SignUpInterface(navController) }
        composable("loginPasswordScreen"){ LoginPasswordInterface(navController) }
        composable("mainScreen"){ MainScreen(navController) }
        composable("offScreen"){ OfflineInterface(navController) }
        composable("searchScreen") { SearchInterface(navController) }
        composable("recipeScreen/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull() ?: 0
            RecipeInterface(id = id, navController = navController)
        }
        composable("exploreScreen"){ ExploreInterface(navController) }
        composable("notifScreen"){ NotifInterface(navController) }
        composable("userMenuScreen"){ UserMenuInterface(navController) }
        composable("settingsScreen"){ SettingsInterface(navController) }
        composable("recommendedScreen"){ RecommendedInterface(navController) }
        composable("articleScreen/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull() ?: 0
            ArticleInterface(id = id, navController = navController)
        }
        composable("supportScreen"){ SupportInterface(navController) }
        composable("privacyScreen"){ PrivacyInterface(navController) }
        composable("reportProblem"){ ReportarProblemaInterface(navController) }
        composable("accountScreen"){ AccountInterface(navController) }
        composable("updateCredentialsScreen") {
            val context = LocalContext.current
            UpdateCredentialsInterface(navController, onAuthSuccess = {
                Toast.makeText(context, "¡Listo para cambiar tus credenciales!", Toast.LENGTH_SHORT).show()
        })
        }
        composable("favoritesScreen"){ FavoritesInterface(navController) }
        composable("savedScreen"){ SavedInterface(navController) }
        composable("localRecipeScreen/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull() ?: 0
            LocalRecipeInterface(id = id, navController = navController)
        }
    }
}
