package io.github.mrizkifadil26.dicodingsubmission.data.tvshows

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName

@Entity(tableName = "tbl_tv")
@TypeConverters(TvShowGenreConverter::class)
data class TvShow (
    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id") var id: Int,
    @ColumnInfo(name = "title")
    @SerializedName("name") var tvShowTitle: String?,
    @ColumnInfo(name = "release_date")
    @SerializedName("first_air_date") var tvShowReleaseDate: String?,
    @ColumnInfo(name = "genres")
    @SerializedName("genres") var tvShowGenres: List<TvShowGenre>,
    @ColumnInfo(name = "vote_average")
    @SerializedName("vote_average") var tvShowRating: Double,
    @ColumnInfo(name = "popularity")
    @SerializedName("popularity") var tvShowPopularity: Double,
    @ColumnInfo(name = "poster_path")
    @SerializedName("poster_path") var tvShowPoster: String?,
    @ColumnInfo(name = "backdrop_path")
    @SerializedName("backdrop_path") var tvShowBackdrop: String?,
    @ColumnInfo(name = "overview")
    @SerializedName("overview") var tvShowDescription: String?,
    @ColumnInfo(name = "seasons")
    @SerializedName("number_of_seasons") var tvShowSeasons: Int,
    @ColumnInfo(name = "episodes")
    @SerializedName("number_of_episodes") var tvShowEpisodes: Int
)