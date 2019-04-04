package com.freeportmetrics.kotlin_internal_project.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.freeportmetrics.kotlin_internal_project.retrofit.SpaceXService
import io.objectbox.BoxStore
import io.objectbox.kotlin.boxFor
import kotlinx.coroutines.*
import retrofit2.HttpException
import retrofit2.Response
import timber.log.Timber

abstract class BaseRepository<T>(@PublishedApi internal val service: SpaceXService, @PublishedApi internal val boxStore: BoxStore) {

    abstract fun loadData() : LiveData<List<T>>

    inline fun <reified T: Any> fetchData(crossinline call: (SpaceXService) -> Deferred<Response<List<T>>>): LiveData<List<T>> {
        val result = MutableLiveData<List<T>>()

        CoroutineScope(Dispatchers.IO).launch {
            val request = call(service)
            withContext(Dispatchers.Main) {
                try {
                    val response = request.await()
                    if (response.isSuccessful) {
                        result.value = response.body()
                    } else {
                        Timber.d("Error occurred with code ${response.code()}")
                    }
                } catch (e: HttpException) {
                    Timber.d("Error: ${e.message()}")
                } catch (e: Throwable) {
                    Timber.d("Error: ${e.message}")
                }
            }
        }

        return result
    }

    inline fun <reified T: Any> saveToDatabase(data: List<T>) {
        CoroutineScope(Dispatchers.IO).launch {
            boxStore.boxFor<T>().put(data)
        }
    }
}