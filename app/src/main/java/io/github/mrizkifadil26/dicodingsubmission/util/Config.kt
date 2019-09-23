package io.github.mrizkifadil26.dicodingsubmission.util

import io.github.mrizkifadil26.dicodingsubmission.data.movies.Movie
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
        detail.genreList.forEachIndexed { index, genre ->
            if (index < detail.genreList.size - 1) {
                genreBuilder.append(genre).append(" | ")
            } else {
                genreBuilder.append(genre.name)
            }
        }
        return genreBuilder.toString()
    }

    /*fun tvGenreStringBuilder(detail: TvShow?): String {
        val genreBuilder = StringBuilder()
        detail?.genres?.forEachIndexed { index, genre ->
            if (index < detail.genres.size - 1) {
                genreBuilder.append(genre.name).append(" | ")
            } else {
                genreBuilder.append(genre.name)
            }
        }
        return genreBuilder.toString()
    }*/
}