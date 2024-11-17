package kz.narxoz.android1

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items
import kotlinx.coroutines.delay

@Composable
fun ProblemsScreen() {
    val problemsList = listOf(
        "У меня проблема в корзиной.",
        "Кто нибудь знает хорошие книги на подарок?",
        "Кто нибудь хочет обсудить книгу Цветы для Элджернона? Отпишитесь, кому интересно.",
        "Продам старые книги."
    )

    // Define the loading state
    var isLoading by remember { mutableStateOf(true) }

    // Simulate a delay to show the loading animation
    LaunchedEffect(Unit) {
        delay(1500) // 3-second delay for loading simulation
        isLoading = false // Set loading to false after delay
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(text = "Обсуждение и вопросы", style = MaterialTheme.typography.titleLarge)

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn {
                items(problemsList) { problem ->
                    ProblemItem(problem)
                }
            }
        }

        // Display the loading overlay if isLoading is true
        LoadingOverlay(isLoading = isLoading)
    }
}

@Composable
fun ProblemItem(problem: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = problem, style = MaterialTheme.typography.bodyMedium)
        }
    }
}
