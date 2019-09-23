package io.github.mrizkifadil26.dicodingsubmission.ui.movies

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import io.github.mrizkifadil26.dicodingsubmission.R
import io.github.mrizkifadil26.dicodingsubmission.data.movies.MovieGenre
import io.github.mrizkifadil26.dicodingsubmission.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.fragment_movies.*

class MovieFragment : Fragment() {

    private lateinit var movieAdapter: MovieAdapter
    private var movieGenres: List<MovieGenre> = listOf()
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
                    setListMovie(movieResults, movieGenres)
                    notifyDataSetChanged()
                }

                recycler_movie.apply {
                    adapter = movieAdapter
                }

                progress_movie.visibility = View.GONE
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
//        Log.d("XXX","Request Code = $requestCode, ResultCode = $resultCode, Data = $data")
        if (requestCode == ADD_TO_FAVORITE_REQUEST_CODE) {
            if (resultCode == ADD_TO_FAVORITE_RESULT_CODE) {
                Toast.makeText(context, "Added to Favorite", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        const val ADD_TO_FAVORITE_REQUEST_CODE = 26
        const val ADD_TO_FAVORITE_RESULT_CODE = 27
    }
}