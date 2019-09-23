package io.github.mrizkifadil26.dicodingsubmission.data.repository

import android.app.Application
import io.github.mrizkifadil26.dicodingsubmission.data.CatalogueRoomDatabase
import io.github.mrizkifadil26.dicodingsubmission.data.movies.Movie
import io.github.mrizkifadil26.dicodingsubmission.data.movies.MovieDao
import io.github.mrizkifadil26.dicodingsubmission.data.movies.MovieGenre
import io.github.mrizkifadil26.dicodingsubmission.data.movies.MovieResults
import io.github.mrizkifadil26.dicodingsubmission.network.ApiService
import io.github.mrizkifadil26.dicodingsubmission.network.RetrofitBuilder

class MovieRepository(application: Application) : BaseRepository() {

    private val apiService: ApiService
    private val catalogueRoomDatabase: CatalogueRoomDatabase
    private val movieDao: MovieDao

    init {
        apiService = RetrofitBuilder.apiService
        catalogueRoomDatabase = CatalogueRoomDatabase.getDatabase(application)
        movieDao = catalogueRoomDatabase.movieDao()
    }

    //    Get Movie From WebService
    suspend fun getMovieFromRetrofit(): List<MovieResults>? {

        val movieResponse = safeApiCall(
            call = { apiService.getMovieFromApi() },
            errorMessage = "Error Fetching Movies"
        )
        return movieResponse?.results
    }

    suspend fun getMovieGenreFromRetrofit(): List<MovieGenre>? {

        val movieGenreResponse = safeApiCall(
            call = {apiService.getMovieGenreFromApi()},
            errorMessage = "Error Fetching MovieGenre"
        )

        return movieGenreResponse?.genres
    }

    suspend fun getMovieDetailFromRetrofit(id: Int): Movie? = safeApiCall(
        call = {apiService.getMovieDetailFromApi(id)},
        errorMessage = "Error Fetching Movie"
    )

    //    Get Favorite Movie From Database
    suspend fun getAllFavoriteMovie(): List<Movie> {
        return movieDao.getAllFavoriteMovie()
    }

    suspend fun getFavoriteMovieById(id: Int): Movie {
        return movieDao.getFavoriteMovieById(id)
    }

    suspend fun insertFavoriteMovie(movie: Movie) {
        return movieDao.insertFavoriteMovie(movie)
    }

    suspend fun deleteFavoriteMovie(movie: Movie) {
        return movieDao.deleteFavoriteMovie(movie)
    }

    suspend fun deleteAllFavoriteMovie() {
        return movieDao.deleteAllFavoriteMovie()
    }
}