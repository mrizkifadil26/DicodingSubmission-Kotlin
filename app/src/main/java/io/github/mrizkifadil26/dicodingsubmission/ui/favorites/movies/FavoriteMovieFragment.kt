package io.github.mrizkifadil26.dicodingsubmission.ui.favorites.movies

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import io.github.mrizkifadil26.dicodingsubmission.R
import io.github.mrizkifadil26.dicodingsubmission.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.fragment_favorite_movie.*

class FavoriteMovieFragment : Fragment() {

    private lateinit var favoriteMovieAdapter: FavoriteMovieAdapter
    private lateinit var favoriteMovieViewModel: MovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progress_favorite_movie.visibility = View.VISIBLE
        recycler_favorite_movie.apply {
            layoutManager = GridLayoutManager(view.context, 2)
            hasFixedSize()
        }
        favoriteMovieAdapter = FavoriteMovieAdapter(view.context)

        favoriteMovieViewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)
        favoriteMovieViewModel.getAllFavoriteMovies().observe(this, Observer { favoriteMovieResults ->

            Log.d("XXX", "Favorite List: $favoriteMovieResults")

            if (favoriteMovieResults.isEmpty()) {
                text_data_movie.text = getString(R.string.no_data)
            } else {
                favoriteMovieAdapter.apply {
                    setListFavoriteMovie(favoriteMovieResults)
                    notifyDataSetChanged()
                }

                recycler_favorite_movie.apply {
                    adapter = favoriteMovieAdapter
                }
            }
            progress_favorite_movie.visibility = View.GONE
        })
    }
}
