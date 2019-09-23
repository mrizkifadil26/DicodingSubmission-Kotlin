package io.github.mrizkifadil26.dicodingsubmission.data.movies

import androidx.room.*

@Dao
interface MovieDao {

    @Query("SELECT * FROM tbl_movie")
    suspend fun getAllFavoriteMovie(): List<Movie>

    @Query("SELECT * FROM tbl_movie WHERE id = :id")
    suspend fun getFavoriteMovieById(id: Int): Movie

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteMovie(movie: Movie)

    @Delete
    suspend fun deleteFavoriteMovie(movie: Movie)

    @Query("DELETE FROM tbl_movie")
    suspend fun deleteAllFavoriteMovie()
}