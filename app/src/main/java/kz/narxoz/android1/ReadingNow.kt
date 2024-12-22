package kz.narxoz.android1

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kz.narxoz.android1.data.ItemData

@Composable
fun ReadingNowList() {
    val readingNowList = listOf(
        ItemData(
            title = "Источник",
            description = "Роман Айн Рэнд",
            date = "Опубликовано: 1943",
            image = R.drawable.fountainhead,
            progress = 0.5f, // 50% прочитано
            reviews = listOf("Очень вдохновляющая книга!", "Отличное произведение."),
            genre = "Философия" // Add the genre parameter
        ),
        ItemData(
            title = "Цветы для Элджернона",
            description = "Роман Дэниела Киза",
            date = "Опубликовано: 1959",
            image = R.drawable.flowers_for_algernon,
            progress = 0.75f, // 75% прочитано
            reviews = listOf("Трогательная история.", "Очень сильные эмоции."),
            genre = "Научная фантастика" // Add the genre parameter
        ),
        ItemData(
            title = "Шерлок Холмс",
            description = "Рассказы Артура Конан Дойля",
            date = "Опубликовано: 1887–1927",
            image = R.drawable.sherlock_holmes,
            progress = 0.2f, // 20% прочитано
            reviews = listOf("Классические детективы.", "Захватывающее чтение."),
            genre = "Детектив" // Add the genre parameter
        )
    )



    var isLoading by remember { mutableStateOf(true) }

    // Симуляция загрузки данных
    LaunchedEffect(Unit) {
        delay(1500) // 1.5-секундная задержка для имитации загрузки
        isLoading = false
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "Читаю сейчас",
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(bottom = 16.dp)
            )

            if (isLoading) {
                // Показ индикатора загрузки
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else {
                // Показ списка книг
                LazyColumn {
                    items(readingNowList) { book ->
                        ReadingNowItem(book)
                    }
                }
            }
        }
    }
}

@Composable
fun ReadingNowItem(book: ItemData) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp)
        ) {
            // Картинка книги
            book.image?.let {
                Image(
                    painter = painterResource(id = it),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Информация о книге
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = book.title,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    fontSize = 18.sp
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = book.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Прогресс чтения
                Text(
                    text = "Прогресс: ${(book.progress * 100).toInt()}%",
                    style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(4.dp))

                LinearProgressIndicator(
                    progress = book.progress,
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}
