package io.github.mrizkifadil26.dicodingsubmission.data.tvshows

import androidx.room.*

@Dao
interface TvShowDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteTv(tvShow: TvShow)

    @Delete
    suspend fun deleteFavoriteTv(tvShow: TvShow)

    @Query("SELECT * FROM tbl_tv")
    suspend fun getAllFavoriteTv(): List<TvShow>

    @Query("SELECT * FROM tbl_tv WHERE id = :id")
    suspend fun getFavoriteTv(id: Int): TvShow
}