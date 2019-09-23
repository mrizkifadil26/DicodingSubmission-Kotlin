package io.github.mrizkifadil26.dicodingsubmission.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.github.mrizkifadil26.dicodingsubmission.data.repository.TvShowRepository
import io.github.mrizkifadil26.dicodingsubmission.data.tvshows.TvShow
import io.github.mrizkifadil26.dicodingsubmission.data.tvshows.TvShowResults
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class TvShowViewModel(application: Application) : AndroidViewModel(application) {

    private val parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.IO
    private val scope = CoroutineScope(coroutineContext)
    private val repository: TvShowRepository


    init {
        repository = TvShowRepository(application)
    }

    fun getAllTvShows(): MutableLiveData<List<TvShowResults>> {
        val result = MutableLiveData<List<TvShowResults>>()
        scope.launch {
            val response = repository.getTvShowFromRetrofit()
            result.postValue(response)
        }
        return result
    }

    fun getTvShowById(id: Int): MutableLiveData<TvShow>? {
        val result = MutableLiveData<TvShow>()
        scope.launch {
            val response = repository.getTvShowDetailFromRetrofit(id)
            result.postValue(response)
        }
        return result
    }

    fun getAllFavoriteTvShows(): LiveData<List<TvShow>> {
        val result = MutableLiveData<List<TvShow>>()
        scope.launch {
            val response = repository.getAllFavoriteTvShow()
            result.postValue(response)
        }
        return result
    }

    fun getFavoriteTvShow(id: Int): LiveData<TvShow> {
        val result = MutableLiveData<TvShow>()
        scope.launch {
            val response = repository.getFavoriteTvShowById(id)
            result.postValue(response)
        }
        return result
    }

    fun insertFavoriteTvShow(TvShow: TvShow) {
        scope.launch {
            repository.insertFavoriteTvShow(TvShow)
        }
    }

    fun deleteFavoriteTvShow(TvShow: TvShow) {
        scope.launch {
            repository.deleteFavoriteTvShow(TvShow)
        }
    }

    private fun cancelAllRequests() = coroutineContext.cancel()

    override fun onCleared() {
        super.onCleared()
        cancelAllRequests()
    }

}