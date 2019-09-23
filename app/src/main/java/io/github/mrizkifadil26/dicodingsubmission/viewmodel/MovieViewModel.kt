package io.github.mrizkifadil26.dicodingsubmission.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.github.mrizkifadil26.dicodingsubmission.data.movies.Movie
import io.github.mrizkifadil26.dicodingsubmission.data.movies.MovieResults
import io.github.mrizkifadil26.dicodingsubmission.data.repository.MovieRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MovieViewModel(application: Application) : AndroidViewModel(application) {

    private val parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.IO
    private val scope = CoroutineScope(coroutineContext)
    private val repository: MovieRepository

    init {
        repository = MovieRepository(application)
    }

    fun getAllMovies(): MutableLiveData<List<MovieResults>> {
        val result = MutableLiveData<List<MovieResults>>()
        scope.launch {
            val response = repository.getMovieFromRetrofit()
            result.postValue(response)
        }
        return result
    }

    fun getMovieById(id: Int): MutableLiveData<Movie>? {
        val result = MutableLiveData<Movie>()
        scope.launch {
            val response = repository.getMovieDetailFromRetrofit(id)
            result.postValue(response)
        }
        return result
    }

    fun getAllFavoriteMovies(): LiveData<List<Movie>> {
        val result = MutableLiveData<List<Movie>>()
        scope.launch {
            val response = repository.getAllFavoriteMovie()
            result.postValue(response)
        }
        return result
    }

    fun getFavoriteMovie(id: Int): LiveData<Movie> {
        val result = MutableLiveData<Movie>()
        scope.launch {
            val response = repository.getFavoriteMovieById(id)
            result.postValue(response)
        }
        return result
    }

    fun insertFavoriteMovie(movie: Movie) {
        scope.launch {
            repository.insertFavoriteMovie(movie)
        }
    }

    fun deleteFavoriteMovie(movie: Movie) {
        scope.launch {
            repository.deleteFavoriteMovie(movie)
        }
    }

    private fun cancelAllRequests() = coroutineContext.cancel()

    override fun onCleared() {
        super.onCleared()
        cancelAllRequests()
    }
}