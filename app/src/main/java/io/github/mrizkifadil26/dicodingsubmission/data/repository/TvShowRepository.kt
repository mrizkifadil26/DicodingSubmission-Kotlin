package io.github.mrizkifadil26.dicodingsubmission.data.repository

import android.app.Application
import io.github.mrizkifadil26.dicodingsubmission.data.CatalogueRoomDatabase
import io.github.mrizkifadil26.dicodingsubmission.data.tvshows.TvShow
import io.github.mrizkifadil26.dicodingsubmission.data.tvshows.TvShowDao
import io.github.mrizkifadil26.dicodingsubmission.data.tvshows.TvShowResults
import io.github.mrizkifadil26.dicodingsubmission.network.ApiService
import io.github.mrizkifadil26.dicodingsubmission.network.RetrofitBuilder

class TvShowRepository(application: Application) : BaseRepository() {

    private val apiService: ApiService
    private val catalogueRoomDatabase: CatalogueRoomDatabase
    private val tvShowDao: TvShowDao

    init {
        apiService = RetrofitBuilder.apiService
        catalogueRoomDatabase = CatalogueRoomDatabase.getDatabase(application)
        tvShowDao = catalogueRoomDatabase.tvShowDao()
    }

    suspend fun getTvShowFromRetrofit(): List<TvShowResults>? {

        val tvShowResponse = safeApiCall(
            call = {apiService.getTvShowFromApi()},
            errorMessage = "Error Fetching TvShows"
        )

        return tvShowResponse?.results
    }

    suspend fun getTvShowDetailFromRetrofit(id: Int): TvShow? = safeApiCall(
        call = { apiService.getTvShowDetailFromApi(id) },
        errorMessage = "Error Fetching TvShow"
    )

    //    Get Favorite TvShow From Database
    suspend fun getAllFavoriteTvShow(): List<TvShow> {
        return tvShowDao.getAllFavoriteTv()
    }

    suspend fun getFavoriteTvShowById(id: Int): TvShow {
        return tvShowDao.getFavoriteTv(id)
    }

    suspend fun insertFavoriteTvShow(tvShow: TvShow) {
        return tvShowDao.insertFavoriteTv(tvShow)
    }

    suspend fun deleteFavoriteTvShow(tvShow: TvShow) {
        return tvShowDao.deleteFavoriteTv(tvShow)
    }

}