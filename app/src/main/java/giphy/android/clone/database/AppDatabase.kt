package giphy.android.clone.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import giphy.android.clone.database.gif.LocalGif
import giphy.android.clone.database.gif.LocalGifDao


@Database(
    entities = [LocalGif::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getLocalGifDao(): LocalGifDao

    companion object {
        fun build(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "giphy-clone-db"
            )
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}