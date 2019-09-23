package io.github.mrizkifadil26.dicodingsubmission.data.repository

import android.util.Log
import retrofit2.Response
import java.io.IOException
import io.github.mrizkifadil26.dicodingsubmission.network.Result

open class BaseRepository {

    suspend fun <T : Any> safeApiCall(call: suspend() -> Response<T>, errorMessage: String): T? {

        val result: Result<T> = safeApiResult(call, errorMessage)
        var data: T? = null

        when(result) {
            is Result.Success ->
                data = result.data

            is Result.Error -> {
                Log.d("BaseRepository", "Error: $errorMessage\nException: ${result.exception}")
            }
        }

        return data
    }

    private suspend fun <T: Any> safeApiResult(call: suspend() -> Response<T>, errorMessage: String): Result<T> {

        val response = call.invoke()
        if (response.isSuccessful) return Result.Success(response.body()!!)

        return Result.Error(IOException("Error occurred during getting safe api result. Error: $errorMessage"))
    }

}