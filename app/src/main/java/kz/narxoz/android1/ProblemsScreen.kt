package kz.narxoz.android1

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items


@Composable
fun ProblemsScreen() {
    // Sample data for problems
    val problemsList = listOf(
        "У меня проблема в корзиной.",
        "Кто нибудь знает хорошие книги на подарок?",
        "Кто нибудь хочет обсудить книгу Цветы для Элджернона? Отпишитесь, кому интересно.",
        "Продам старые книги."
    )

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "Обсуждение и вопросы", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(16.dp))

        // Display the list of problems
        LazyColumn {
            items(problemsList) { problem ->
                ProblemItem(problem)
            }
        }
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
