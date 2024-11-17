package kz.narxoz.android1

data class ItemData(
    val title: String,
    val description: String,
    val date: String,
    val image: Int? = null,
    val reviews: List<String> = emptyList()
)

