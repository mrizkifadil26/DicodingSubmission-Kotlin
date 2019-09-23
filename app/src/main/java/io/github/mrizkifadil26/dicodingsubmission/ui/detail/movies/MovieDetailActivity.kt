package io.github.mrizkifadil26.dicodingsubmission.ui.detail.movies

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import io.github.mrizkifadil26.dicodingsubmission.R
import io.github.mrizkifadil26.dicodingsubmission.data.movies.Movie
import io.github.mrizkifadil26.dicodingsubmission.util.Config
import io.github.mrizkifadil26.dicodingsubmission.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : AppCompatActivity() {

    private var movieViewModel: MovieViewModel? = null
    private var movieId: Int = 0
    private lateinit var movieResult: Movie
    private var isClicked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowTitleEnabled(false)
        }

        movieId = intent.getIntExtra(EXTRA_MOVIE, 0)

        progress_detail_movie.visibility = View.VISIBLE

        movieViewModel = ViewModelProviders.of(this@MovieDetailActivity).get(MovieViewModel::class.java)
        movieViewModel?.getFavoriteMovie(movieId)?.observe(this, Observer { results ->
            if (results != null) {
                fab_fav_movie_detail.setImageResource(R.drawable.ic_favorite)
                isClicked = true

                Picasso.get()
                    .load(Config.IMAGE_URL + results.moviePoster)
                    .into(movie_detail_poster)

                Picasso.get()
                    .load(Config.BACKDROP_URL + results.movieBackdrop)
                    .into(movie_detail_backdrop)

                movie_detail_title.text = results.movieTitle.plus(" (" + results.movieReleaseDate?.let {
                    Config.dateFormatter(
                        it, "yyyy")
                } + ")")

                val genres  = Config.movieGenreStringBuilder(results)
                text_movie_detail_genre.text = genres

                movie_detail_release_date.text =
                    results.movieReleaseDate?.let { Config.dateFormatter(it, "d MMM yyyy") }
                movie_detail_rating.text = results.movieRating.toString()
                movie_detail_tagline.text = ("""
                            """" + results.movieTagline + """"
                        """).trimIndent()
                movie_detail_overview.text = results.movieDescription

                progress_detail_movie.visibility = View.GONE

                movieResult = results
            } else {
                movieViewModel?.getMovieById(movieId)?.observe(this, Observer { remoteResults ->
                    fab_fav_movie_detail.setImageResource(R.drawable.ic_favorite_border)
                    isClicked = false

                    Picasso.get()
                        .load(Config.IMAGE_URL + remoteResults.moviePoster)
                        .into(movie_detail_poster)

                    Picasso.get()
                        .load(Config.BACKDROP_URL + remoteResults.movieBackdrop)
                        .into(movie_detail_backdrop)

                    movie_detail_title.text = remoteResults.movieTitle.plus(" (" + remoteResults.movieReleaseDate?.let {
                        Config.dateFormatter(
                            it, "yyyy")
                    } + ")")
                    movie_detail_release_date.text =
                        remoteResults.movieReleaseDate?.let { Config.dateFormatter(it, "d MMM yyyy") }
                    movie_detail_rating.text = remoteResults.movieRating.toString()
                    movie_detail_tagline.text = ("""
                            """" + remoteResults.movieTagline + """"
                        """).trimIndent()
                    movie_detail_overview.text = remoteResults.movieDescription

                    progress_detail_movie.visibility = View.GONE

                    movieResult = remoteResults
                })
            }
        })

        fab_fav_movie_detail.setOnClickListener { view ->
            isClicked = if (!isClicked) {
                movieViewModel?.insertFavoriteMovie(movieResult)
                fab_fav_movie_detail.setImageResource(R.drawable.ic_favorite)
                Snackbar.make(view, "Added to Favorite List", Snackbar.LENGTH_SHORT).show()
                true
            } else {
                movieViewModel?.deleteFavoriteMovie(movieResult)
                fab_fav_movie_detail.setImageResource(R.drawable.ic_favorite_border)
                Snackbar.make(view, "Removed from Favorite List", Snackbar.LENGTH_SHORT).show()
                false
            }
        }
    }

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }
}
