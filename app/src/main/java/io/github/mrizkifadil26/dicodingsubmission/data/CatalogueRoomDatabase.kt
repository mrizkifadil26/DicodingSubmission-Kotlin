package io.github.mrizkifadil26.dicodingsubmission.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import io.github.mrizkifadil26.dicodingsubmission.data.movies.GenreConverter
import io.github.mrizkifadil26.dicodingsubmission.data.movies.Movie
import io.github.mrizkifadil26.dicodingsubmission.data.movies.MovieDao
import io.github.mrizkifadil26.dicodingsubmission.data.tvshows.TvShow
import io.github.mrizkifadil26.dicodingsubmission.data.tvshows.TvShowDao

@Database(entities = [
                Movie::class,
                TvShow::class], version = 10)
@TypeConverters(GenreConverter::class)
abstract class CatalogueRoomDatabase : RoomDatabase() {
    abstract fun movieDao() : MovieDao
    abstract fun tvShowDao() : TvShowDao

    companion object {
        @Volatile
        private var INSTANCE: CatalogueRoomDatabase? = null

        fun getDatabase(context: Context): CatalogueRoomDatabase {
            val tempInstance =
                INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CatalogueRoomDatabase::class.java,
                    "db_submission"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}