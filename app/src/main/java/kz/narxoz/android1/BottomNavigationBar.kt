package kz.narxoz.android1

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search

@Composable
fun BottomNavigationBar(navController: NavController) {
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

    NavigationBar  {
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Home, contentDescription = "Books") },
            label = { Text("Книги") },
            selected = currentRoute == "list",
            onClick = {
                navController.navigate("list") {
                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Search, contentDescription = "Problems") },
            label = { Text("Вопросы") },
            selected = currentRoute == "problems",
            onClick = {
                navController.navigate("problems") {
                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Person, contentDescription = "Account") },
            label = { Text("Мой аккаунт") },
            selected = currentRoute == "account",
            onClick = {
                navController.navigate("account") {
                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )
    }
}