package br.com.felipepalm14.eventour.domain.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.felipepalm14.eventour.domain.database.model.Event


@Database(entities = [Event::class], version = 2, exportSchema = false)
@TypeConverters(value = [Converters.CupomConverter::class])
abstract class AppDatabase : RoomDatabase() {

    abstract fun eventDao(): EventDao

    companion object {
        @Volatile private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) { instance ?: buildDatabase(context)
                .also { instance = it } }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDatabase::class.java, "events")
                .fallbackToDestructiveMigration()
                .build()
    }

}