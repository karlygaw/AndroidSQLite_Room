package kz.narxoz.android1

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import kz.narxoz.android1.data.ItemData

@Composable
fun FavoriteBooksList(navController: NavController, modifier: Modifier = Modifier) {
    val itemList = listOf(
        ItemData(
            title = "Источник",
            author = "Айн Рэнд",
            date = "Опубликовано: 1943",
            image = R.drawable.fountainhead,
            reviews = listOf("Увлекательное повествование!", "Вдохновляющий роман."),
            progress = 0.0f,
            genre = "Философия"
        ),
        ItemData(
            title = "Цветы для Элджернона",
            description = "Роман Дэниела Киза",
            date = "Опубликовано: 1959",
            image = R.drawable.flowers_for_algernon,
            reviews = listOf("Трогательная история.", "Очень эмоционально."),
            progress = 0.0f,
            genre = "Фантастика"
        ),
        ItemData(
            title = "Шерлок Холмс",
            description = "Рассказы Артура Конан Дойля",
            date = "Опубликовано: 1887–1927",
            image = R.drawable.sherlock_holmes,
            reviews = listOf("Классический детектив.", "Интересные загадки."),
            progress = 0.0f,
            genre = "Детективы"
        ),
        ItemData(
            title = "Автобиография Бенджамина Франклина",
            description = "Мемуары Бенджамина Франклина",
            date = "Опубликовано: 1791",
            image = R.drawable.ben_franklin,
            reviews = listOf("Интересный взгляд на историю.", "Полезное чтение."),
            progress = 0.0f,
            genre = "История"
        ),
        ItemData(
            title = "Письма к Луцилию",
            description = "Сборник писем Сенеки",
            date = "Написано: 62–65 н. э.",
            image = R.drawable.letters_to_lucilius,
            reviews = listOf("Мудрые советы.", "Философские размышления."),
            progress = 0.0f,
            genre = "Философия"
        )
    )

    var isLoading by remember { mutableStateOf(true) }
    var searchText by remember { mutableStateOf("") }
    var filteredList by remember { mutableStateOf(itemList) }
    var selectedGenre by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        delay(1500) // Имитируем загрузку
        isLoading = false
    }

    val scrollState = rememberLazyListState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Заголовок "Мир Книг"
//        Text(
//            text = "Мир Книг",
//            style = MaterialTheme.typography.titleLarge.copy(
//                fontWeight = FontWeight.Bold,
//                fontSize = 24.sp
//            ),
//            color = MaterialTheme.colorScheme.primary,
//            modifier = Modifier.padding(bottom = 16.dp)
//        )

        Row(
            verticalAlignment = Alignment.CenterVertically,  // Выравнивание по вертикали
            horizontalArrangement = Arrangement.spacedBy(8.dp), // Расстояние между изображением и текстом
            modifier = Modifier
                .padding(bottom = 16.dp)  // Отступ снизу
                .fillMaxWidth()  // Заполнение всего доступного пространства
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo3), // Замените на свой ресурс изображения
                contentDescription = "Logo",
                modifier = Modifier
                    .height(70.dp)
            )

            Column(
                horizontalAlignment = Alignment.End,  // Выравнивание текста по правому краю
                modifier = Modifier.fillMaxWidth()  // Заполнение пространства для выравнивания по правому краю
            ) {
                Text(
                    text = "Hello, Karlygash",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,  // Жирное начертание
                        fontSize = 20.sp,  // Размер шрифта больше
                        color = Color.DarkGray  // Белый цвет текста
                    )
                )
                Text(
                    text = "Let's start reading",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 12.sp,  // Размер шрифта меньше
                        color = Color.DarkGray  // Белый цвет текста
                    )
                )
            }
        }




        // Поле поиска с иконкой
        OutlinedTextField(
            value = searchText,
            onValueChange = { text ->
                searchText = text
                filteredList = itemList.filter { item ->
                    item.title.contains(searchText, ignoreCase = true) ||
                            item.description.contains(searchText, ignoreCase = true) ||
                            item.genre.contains(searchText, ignoreCase = true)
                }
            },
            label = { Text("Поиск") },
            trailingIcon = {
                IconButton(onClick = {
                    filteredList = itemList.filter { item ->
                        item.title.contains(searchText, ignoreCase = true) ||
                                item.description.contains(searchText, ignoreCase = true) ||
                                item.genre.contains(searchText, ignoreCase = true)
                    }
                }) {
                    Icon(imageVector = Icons.Filled.Search, contentDescription = "Search Icon")
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        // Секция "Upcoming Book Fair"
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(bottom = 16.dp)
                .graphicsLayer {
                    // Позиционируем Box в зависимости от прокрутки
                    translationY = if (scrollState.firstVisibleItemIndex == 0) {
                        0f
                    } else {
                        (scrollState.firstVisibleItemScrollOffset / 2).toFloat()
                    }
                }
        ) {
            Image(
                painter = painterResource(id = R.drawable.upcoming_fair), // Замените на своё изображение
                contentDescription = "Предстоящая книжная ярмарка",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.4f))
            )
            Column(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(16.dp)
            ) {
                Text(
                    text = "Upcoming Book Fair",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
            Button(
                onClick = { /* Действие при нажатии */ },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
            ) {
                Text("Забронировать")
            }
        }

        // Кнопки-фильтры по жанрам
        LazyRow(
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            val genres = listOf("Все") + itemList.map { it.genre }.distinct() // Добавляем "Все" в начало
            items(genres) { genre ->
                FilterChip(
                    selected = selectedGenre == genre || (genre == "Все" && selectedGenre == null),
                    onClick = {
                        selectedGenre = if (genre == "Все") null else genre
                        filteredList = if (selectedGenre == null) itemList else itemList.filter { it.genre == selectedGenre }
                    },
                    label = { Text(text = genre) },
                    modifier = Modifier.background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f))
                )
            }
        }

        if (isLoading) {
            // Индикатор загрузки
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(state = scrollState, modifier = Modifier.fillMaxSize()) {
                // Секция популярных книг (показывается только при выборе "Все")
                if (selectedGenre == null) {
                    item {
                        Text(
                            text = "Popular Books",
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                        LazyRow(
                            contentPadding = PaddingValues(horizontal = 8.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            items(itemList) { book ->
                                BookCard(book) {
                                    navController.navigate("detail/${book.title}/${book.description}/${book.date}/${book.image ?: 0}") {
                                        launchSingleTop = true
                                    }
                                }
                            }
                        }
                    }

                    item {
                        Spacer(modifier = Modifier.height(16.dp)) // Отступ между секциями
                        Text(
                            text = "Recommended for You",
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                        LazyRow(
                            contentPadding = PaddingValues(horizontal = 8.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            val recommendedBooks =
                                itemList.filter { it.genre in listOf("Философия", "Фантастика") }
                            items(recommendedBooks) { book ->
                                BookCard(book) {
                                    navController.navigate("detail/${book.title}/${book.description}/${book.date}/${book.image ?: 0}") {
                                        launchSingleTop = true
                                    }
                                }
                            }
                        }
                    }
                } else {
                    // Показываем список книг только при выборе конкретного жанра
                    items(filteredList) { item ->
                        ListItemCard(item) {
                            navController.navigate(
                                "detail/${item.title}/${item.description}/${item.date}/${item.image ?: 0}/${
                                    item.reviews.joinToString(
                                        "|"
                                    )
                                }"
                            ) {
                                launchSingleTop = true
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BookCard(book: ItemData, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .width(140.dp)
            .height(200.dp)
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = book.image),
                contentDescription = book.title,
                modifier = Modifier
                    .height(120.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = book.title,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            Text(
                text = book.genre,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.secondary
            )
        }
    }
}
