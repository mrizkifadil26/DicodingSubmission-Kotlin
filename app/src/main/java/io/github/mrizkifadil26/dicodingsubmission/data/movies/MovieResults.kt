package io.github.mrizkifadil26.dicodingsubmission.data.movies

import com.google.gson.annotations.SerializedName

data class MovieResults (
    @SerializedName("id") var id: Int,
    @SerializedName("vote_average") var movieRating: Double,
    @SerializedName("title") var movieTitle: String?,
    @SerializedName("poster_path") var moviePoster: String?,
    @SerializedName("genre_ids") var genreId: List<Int>,
    @SerializedName("release_date") var movieReleaseDate: String?
)

data class MovieResponse(
    @SerializedName("page") var page: Int,
    @SerializedName("total_results") var totalResults: Int,
    @SerializedName("total_pages") var totalPages: Int,
    @SerializedName("results") var results: List<MovieResults>
)