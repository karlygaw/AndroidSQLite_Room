package kz.narxoz.android1

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

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

    LazyColumn(
        modifier = modifier
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
}

