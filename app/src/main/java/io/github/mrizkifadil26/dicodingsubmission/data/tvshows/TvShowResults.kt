package io.github.mrizkifadil26.dicodingsubmission.data.tvshows

import com.google.gson.annotations.SerializedName

data class TvShowResults (
    @SerializedName("id") var id: Int,
    @SerializedName("vote_average") var tvShowRating: Double,
    @SerializedName("name") var tvShowTitle: String,
    @SerializedName("first_air_date") var tvReleaseDate: String,
    @SerializedName("poster_path") var tvShowPoster: String,
    @SerializedName("genre_ids") var genreId: List<Int>
)

data class TvShowResponse (
    @SerializedName("page") var page: Int,
    @SerializedName("total_results") var totalResults: Int,
    @SerializedName("total_pages") var totalPages: Int,
    @SerializedName("results") var results: List<TvShowResults>
)