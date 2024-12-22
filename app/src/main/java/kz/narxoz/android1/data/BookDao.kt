package kz.narxoz.android1.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {

    // Добавление книги
    @Insert
    suspend fun insertBook(book: BookEntity)

    // Получение всех книг
    @Query("SELECT * FROM books")
    fun getAllBooks(): Flow<List<BookEntity>>

    // Получение книги по ID
    @Query("SELECT * FROM books WHERE id = :bookId")
    suspend fun getBookById(bookId: Long): BookEntity?

    // Обновление книги
    @Update
    suspend fun updateBook(book: BookEntity)

    // Удаление книги по ID
    @Query("DELETE FROM books WHERE id = :bookId")
    suspend fun deleteBookById(bookId: Long)

    // Удаление всех книг
    @Query("DELETE FROM books")
    suspend fun deleteAllBooks()
}
