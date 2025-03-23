package com.example.gastrolab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.example.gastrolab.ui.theme.GastroLabTheme
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gastrolab.ui.screens.AccountScrens.AccountInterface
import com.example.gastrolab.ui.screens.AccountScrens.FavoritesInterface
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
import com.example.gastrolab.ui.screens.RecipeSearchScreens.CommentsInterface
import com.example.gastrolab.ui.screens.RecipeSearchScreens.RecipeInterface
import com.example.gastrolab.ui.screens.RecipeSearchScreens.SearchInterface
import com.example.gastrolab.ui.screens.TroubleshootScreens.PrivacyInterface
import com.example.gastrolab.ui.screens.TroubleshootScreens.ReportarProblemaInterface
import com.example.gastrolab.ui.screens.TroubleshootScreens.SupportInterface

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
    SetupNavGraph(navController = navController)
}
@Composable
fun SetupNavGraph(navController: NavHostController){
    //startDestinations marks which screen is going to open at launch
    NavHost(navController = navController, startDestination = "mainScreen"){
        //add route name for every screen
        composable("loginScreen"){ LoginInterface(navController) }
        composable("signUpScreen"){ SignUpInterface(navController) }
        composable("loginPasswordScreen"){ LoginPasswordInterface(navController) }
        composable("mainScreen"){ MainScreen(navController) }
        composable("searchScreen"){ SearchInterface(navController) }
        composable("recipeScreen"){ RecipeInterface(navController) }
        composable("commentsScreen"){ CommentsInterface(navController) }
        composable("exploreScreen"){ ExploreInterface(navController) }
        composable("notifScreen"){ NotifInterface(navController) }
        composable("userMenuScreen"){ UserMenuInterface(navController) }
        composable("settingsScreen"){ SettingsInterface(navController) }
        composable("recommendedScreen"){ RecommendedInterface(navController) }
        composable("ai"){ ArticleInterface(navController) }
        composable("supportScreen"){ SupportInterface(navController) }
        composable("privacyScreen"){ PrivacyInterface(navController) }
        composable("reportProblem"){ ReportarProblemaInterface(navController) }
        composable("accountScreen"){ AccountInterface(navController) }
        composable("favoritesScreen"){ FavoritesInterface(navController) }
        composable("savedScreen"){ SavedInterface(navController) }
    }

}