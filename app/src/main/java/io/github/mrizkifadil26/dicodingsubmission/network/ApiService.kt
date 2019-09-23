package io.github.mrizkifadil26.dicodingsubmission.network

import io.github.mrizkifadil26.dicodingsubmission.data.movies.Movie
import io.github.mrizkifadil26.dicodingsubmission.data.movies.MovieGenreResponse
import io.github.mrizkifadil26.dicodingsubmission.data.movies.MovieResponse
import io.github.mrizkifadil26.dicodingsubmission.data.tvshows.TvShow
import io.github.mrizkifadil26.dicodingsubmission.data.tvshows.TvShowGenreResponse
import io.github.mrizkifadil26.dicodingsubmission.data.tvshows.TvShowResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("discover/movie")
    suspend fun getMovieFromApi(): Response<MovieResponse>

    @GET("genre/movie/list")
    suspend fun getMovieGenreFromApi(): Response<MovieGenreResponse>

    @GET("movie/{id}")
    suspend fun getMovieDetailFromApi(@Path("id") movieId: Int): Response<Movie>

    @GET("discover/tv")
    suspend fun getTvShowFromApi(): Response<TvShowResponse>

    @GET("genre/tv/list")
    suspend fun getTvShowGenreFromApi(): Response<TvShowGenreResponse>

    @GET("tv/{id}")
    suspend fun getTvShowDetailFromApi(@Path("id") tvShowId: Int): Response<TvShow>
}