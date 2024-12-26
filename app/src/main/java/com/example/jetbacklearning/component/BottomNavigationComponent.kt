package com.example.jetbacklearning.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.jetbacklearning.R

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    Card(shape = RoundedCornerShape(12.dp)) {
        NavigationBar {
            val items = listOf(BottomNavItem.Home, BottomNavItem.Profile, BottomNavItem.Settings)
            val navBackStackEntry = navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry.value?.destination?.route

            items.forEach { item ->
                val isSelected = currentRoute == item.route
                NavigationBarItem(
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.Blue,
                        unselectedIconColor = Color.Gray,
                        selectedTextColor = Color.Blue,
                        unselectedTextColor = Color.Gray,
                    ),
                    // Set a shape for the indicator
                    // modifier = Modifier
                    //    .padding(4.dp)
                    //  .background(
                    //    color =/* if (isSelected) Color.LightGray else*/ Color.Transparent,
                    //  shape = RoundedCornerShape(12.dp)
                    //),
                    icon = { Icon(item.icon, contentDescription = item.label) },
                    label = { Text(item.label) },
                    selected = navController.currentBackStackEntry?.destination?.route == item.route,
                    onClick = {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },


                    )
            }
        }
    }

}
sealed class BottomNavItem(val route: String, val icon: ImageVector, val label: String) {
    object Home : BottomNavItem("home",  Icons.Filled.Home, "Home")
    object Profile : BottomNavItem("friendslist", Icons.Filled.Person, "Profile")
    object Settings : BottomNavItem("setting", Icons.Filled.Settings, "Settings")
}