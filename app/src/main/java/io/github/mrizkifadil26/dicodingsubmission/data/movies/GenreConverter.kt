package io.github.mrizkifadil26.dicodingsubmission.data.movies

import androidx.room.TypeConverter
import com.google.gson.Gson

class GenreConverter {
    @TypeConverter
    fun jsonToList(json: String): List<MovieGenre> {
        val objects = Gson().fromJson(json, Array<MovieGenre>::class.java)
        return objects.toList()
    }

    @TypeConverter
    fun listToJson(list: List<MovieGenre>): String {
        return Gson().toJson(list)
    }
}