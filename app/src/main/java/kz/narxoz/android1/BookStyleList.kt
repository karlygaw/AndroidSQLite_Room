package kz.narxoz.android1

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.delay

@Composable
fun FavoriteBooksList(navController: NavController, modifier: Modifier = Modifier) {
    val itemList = listOf(
        ItemData(
            title = "Источник",
            description = "Роман Айн Рэнд",
            date = "Опубликовано: 1943",
            image = R.drawable.fountainhead,
            reviews = listOf("Увлекательное повествование!", "Вдохновляющий роман.")
        ),
        ItemData(
            title = "Цветы для Элджернона",
            description = "Роман Дэниела Киза",
            date = "Опубликовано: 1959",
            image = R.drawable.flowers_for_algernon,
            reviews = listOf("Трогательная история.", "Очень эмоционально.")
        ),
        ItemData(
            title = "Шерлок Холмс",
            description = "Рассказы Артура Конан Дойля",
            date = "Опубликовано: 1887–1927",
            image = R.drawable.sherlock_holmes,
            reviews = listOf("Классический детектив.", "Интересные загадки.")
        ),
        ItemData(
            title = "Автобиография Бенджамина Франклина",
            description = "Мемуары Бенджамина Франклина",
            date = "Опубликовано: 1791",
            image = R.drawable.ben_franklin,
            reviews = listOf("Интересный взгляд на историю.", "Полезное чтение.")
        ),
        ItemData(
            title = "Письма к Луцилию",
            description = "Сборник писем Сенеки",
            date = "Написано: 62–65 н. э.",
            image = R.drawable.letters_to_lucilius,
            reviews = listOf("Мудрые советы.", "Философские размышления.")
        )
    )

    val (isLoading, setIsLoading) = remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        delay(1500)
        setIsLoading(false)
    }

    // Show loading overlay only if isLoading is true
    Box(modifier = modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(itemList) { item ->
                ListItemCard(item) {
                    navController.navigate("detail/${item.title}/${item.description}/${item.date}/${item.image ?: 0}/${item.reviews.joinToString("|")}") {
                        launchSingleTop = true
                    }
                }
            }
        }

        // Show overlay
        LoadingOverlay(isLoading = isLoading)
    }
}
