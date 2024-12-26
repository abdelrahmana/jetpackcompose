package com.example.jetbacklearning

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.jetbacklearning.component.BottomNavigationBar
import com.example.jetbacklearning.screens.SettingScreen
import com.example.jetbacklearning.screens.detailsscreen.DetailsScreen
import com.example.jetbacklearning.ui.theme.JetbackLearningTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val CustomColorScheme = lightColorScheme(
            primary = Color(0xFF6200EE), // purple_700 equivalent
            onPrimary = Color.Yellow,
            secondary = Color.Gray,
            onSecondary = Color.Red
        )
        setContent {
            MaterialTheme(colorScheme = CustomColorScheme) {
                val navController = rememberNavController()

                Scaffold(modifier = Modifier.fillMaxSize(),
                    bottomBar = { BottomNavigationBar(navController) }) { innerPadding ->
                    /* Greeting(
                             name = "Android",
                             modifier = Modifier.padding(innerPadding)
                     )*/
                    MyApp(navController)
                }
            }
        }
    }
}

@Composable
fun MyApp(navController: NavHostController) {
    NavGraph(navController)
}

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "setting") {
        composable("home") { FirstFragment(/*...*/) }
        ///{userName}/{password}
        composable("friendslist") { backStackEntry ->
            SecondClass(
                navController = navController,
                //username = backStackEntry.arguments?.getString("userName")?:"",
                //password = backStackEntry.arguments?.getString("password")?:""
            )
        }
        composable("detailsScreen/{userName}/{password}") { backStackEntry ->
            DetailsScreen(
                navController = navController,
                username = backStackEntry.arguments?.getString("userName")?: "",
                password = backStackEntry.arguments?.getString("password")?: ""
            )
        }
        composable("setting") { SettingScreen(navController) }
        /*...*/
    }
}

    @Composable
    fun Greeting(name: String, modifier: Modifier = Modifier) {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        JetbackLearningTheme {
            Greeting("Android")
        }
    }