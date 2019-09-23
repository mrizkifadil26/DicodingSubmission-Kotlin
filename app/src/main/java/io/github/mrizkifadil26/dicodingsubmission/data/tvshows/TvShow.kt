package io.github.mrizkifadil26.dicodingsubmission.data.tvshows

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "tbl_tv")
data class TvShow (
    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id") var id: Int,
    @ColumnInfo(name = "title")
    @SerializedName("name") var tvShowTitle: String?,
    @ColumnInfo(name = "release_date")
    @SerializedName("first_air_date") var tvShowReleaseDate: String?,
//    @ColumnInfo(name = "genres") var tvShowGenres: String,
//    @SerializedName("genres") var tvShowGenreList: List<TvShowGenre>,
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
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(tvShowTitle)
        parcel.writeString(tvShowReleaseDate)
        parcel.writeDouble(tvShowRating)
        parcel.writeDouble(tvShowPopularity)
        parcel.writeString(tvShowPoster)
        parcel.writeString(tvShowBackdrop)
        parcel.writeString(tvShowDescription)
        parcel.writeInt(tvShowSeasons)
        parcel.writeInt(tvShowEpisodes)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TvShow> {
        override fun createFromParcel(parcel: Parcel): TvShow {
            return TvShow(parcel)
        }

        override fun newArray(size: Int): Array<TvShow?> {
            return arrayOfNulls(size)
        }
    }
}