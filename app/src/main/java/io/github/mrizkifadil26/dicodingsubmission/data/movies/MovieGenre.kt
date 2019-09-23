package io.github.mrizkifadil26.dicodingsubmission.data.movies

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class MovieGenre(
    @PrimaryKey
    @SerializedName("id") var id: Int,
    @SerializedName("name") var name: String
)

data class MovieGenreResponse (
    @SerializedName("genres") var genres: List<MovieGenre>
)