package io.github.mrizkifadil26.dicodingsubmission.util

import io.github.mrizkifadil26.dicodingsubmission.data.movies.Movie
import io.github.mrizkifadil26.dicodingsubmission.data.tvshows.TvShow
import java.text.SimpleDateFormat
import java.util.*

object Config {
    const val API_URL = "https://api.themoviedb.org/3/"
    const val IMAGE_URL = "https://image.tmdb.org/t/p/w185"
    const val BACKDROP_URL = "https://image.tmdb.org/t/p/w500"

    fun dateFormatter(date: String, pattern: String): String {
        val parser = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val formatter = SimpleDateFormat(pattern, Locale.getDefault())
        return formatter.format(parser.parse(date))
    }

    fun movieGenreStringBuilder(detail: Movie): String {
        val genreBuilder = StringBuilder()
        detail.movieGenres.forEachIndexed { index, genre ->
            if (index < detail.movieGenres.size - 1) {
                genreBuilder.append(genre.name).append(" | ")
            } else {
                genreBuilder.append(genre.name)
            }
        }
        return genreBuilder.toString()
    }

    fun tvGenreStringBuilder(detail: TvShow): String {
        val genreBuilder = StringBuilder()
        detail.tvShowGenres.forEachIndexed { index, genre ->
            if (index < detail.tvShowGenres.size - 1) {
                genreBuilder.append(genre.name).append(" | ")
            } else {
                genreBuilder.append(genre.name)
            }
        }
        return genreBuilder.toString()
    }
}