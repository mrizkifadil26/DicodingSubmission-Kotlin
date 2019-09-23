package io.github.mrizkifadil26.dicodingsubmission.data.tvshows

import com.google.gson.annotations.SerializedName

data class TvShowGenre(
    @SerializedName("id") var id: Int,
    @SerializedName("name") var name: String
)

data class TvShowGenreResponse(
    @SerializedName("genres") var genres: List<TvShowGenre>
)