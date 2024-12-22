package kz.narxoz.android1

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp(onRouteChange: (String) -> Unit) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)) {

            NavHost(navController, startDestination = "list") {
                composable("list") {
                    FavoriteBooksList(navController = navController)
                }
                composable("detail/{title}/{description}/{date}/{image}/{reviews}") { backStackEntry ->
                    val reviews = backStackEntry.arguments?.getString("reviews")?.split("|") ?: emptyList()
                    DetailScreen(
                        navController = navController,
                        title = backStackEntry.arguments?.getString("title") ?: "",
                        description = backStackEntry.arguments?.getString("description") ?: "",
                        date = backStackEntry.arguments?.getString("date") ?: "",
                        image = backStackEntry.arguments?.getString("image")?.toIntOrNull(),
                        reviews = reviews
                    )
                }
                composable("problems") {
                    ReadingNowList()
                }
                composable("account") {
                    MyAccountScreen()
                }
            }
        }
    }
}
