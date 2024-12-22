package kz.narxoz.android1.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class BookEntity(
    @PrimaryKey(autoGenerate = true) val id: Long? = 0, // Добавлен уникальный ID
    val title: String,
    val author: String,
    val description: String,
    val date: String,
    val image: Int? = null, // Если используете drawable ресурсы, иначе можно заменить на String
    val reviews: List<String>,
    val progress: Float, // Значение от 0.0 до 1.0
    val genre: String, // Жанр книги
    val pdfFilePath: String? = null, // Путь к PDF файлу
)
