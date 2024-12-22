package kz.narxoz.android1.data

data class BookData(
    val title: String,
    val author: String,
    val description: String,
    val date: String,
    val image: Int? = null, // Assuming image is a drawable resource ID
    val reviews: List<String>,
    val progress: Float, // Значение от 0.0 до 1.0, показывающее прогресс чтения (например, 0.5 = 50%)
    val genre: String, // Жанр книги
    val pdfFilePath: String? = null, // Путь к PDF файлу (можно хранить как строку пути)
)

