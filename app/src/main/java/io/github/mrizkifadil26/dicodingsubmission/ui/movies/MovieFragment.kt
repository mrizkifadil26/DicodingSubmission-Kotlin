package io.github.mrizkifadil26.dicodingsubmission.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import io.github.mrizkifadil26.dicodingsubmission.R
import io.github.mrizkifadil26.dicodingsubmission.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.fragment_movies.*

class MovieFragment : Fragment() {

    private lateinit var movieAdapter: MovieAdapter
    private lateinit var movieViewModel: MovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progress_movie.visibility = View.VISIBLE
        recycler_movie.apply {
            layoutManager = GridLayoutManager(view.context, 2)
            hasFixedSize()
        }
        movieAdapter = MovieAdapter(view.context)

        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)
        movieViewModel.getAllMovies().observe(this, Observer {
            movieResults -> run {
                movieAdapter.apply {
                    setListMovie(movieResults)
                    notifyDataSetChanged()
                }

                recycler_movie.apply {
                    adapter = movieAdapter
                }

                progress_movie.visibility = View.GONE
            }
        })
    }
}