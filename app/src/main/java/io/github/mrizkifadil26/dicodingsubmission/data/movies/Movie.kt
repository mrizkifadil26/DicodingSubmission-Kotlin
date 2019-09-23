package io.github.mrizkifadil26.dicodingsubmission.data.movies

import androidx.room.*
import com.google.gson.annotations.SerializedName

@Entity(tableName = "tbl_movie")
data class Movie (
    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id") var id: Int,
    @ColumnInfo(name = "title")
    @SerializedName("title") var movieTitle: String?,
    @ColumnInfo(name = "release_date")
    @SerializedName("release_date") var movieReleaseDate: String?,
    @SerializedName("genres") var genreList: List<MovieGenre>,
    @ColumnInfo(name = "vote_average")
    @SerializedName("vote_average") var movieRating: Double,
    @ColumnInfo(name = "popularity")
    @SerializedName("popularity") var moviePopularity: Double,
    @ColumnInfo(name = "poster_path")
    @SerializedName("poster_path") var moviePoster: String?,
    @ColumnInfo(name = "backdrop_path")
    @SerializedName("backdrop_path") var movieBackdrop: String?,
    @ColumnInfo(name = "overview")
    @SerializedName("overview") var movieDescription: String?,
    @ColumnInfo(name = "tagline")
    @SerializedName("tagline") var movieTagline: String?
)