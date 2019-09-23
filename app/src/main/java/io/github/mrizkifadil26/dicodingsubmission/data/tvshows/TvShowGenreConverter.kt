package io.github.mrizkifadil26.dicodingsubmission.data.tvshows

import androidx.room.TypeConverter
import com.google.gson.Gson

class TvShowGenreConverter {
    @TypeConverter
    fun tvJsonToList(json: String): List<TvShowGenre> {
        val objects = Gson().fromJson(json, Array<TvShowGenre>::class.java)
        return objects.toList()
    }

    @TypeConverter
    fun tvListToJson(list: List<TvShowGenre>): String {
        return Gson().toJson(list)
    }
}