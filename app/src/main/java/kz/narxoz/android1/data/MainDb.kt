package kz.narxoz.android1.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database (entities = [BookEntity::class], version = 1)
abstract class MainDb : RoomDatabase() {
    abstract fun getDao(): BookDao
    companion object{
        fun getDb(context: Context): MainDb{
            return Room.databaseBuilder(
                context.applicationContext,
                MainDb::class.java,
                "app.db"
            ).build()
        }
    }
}