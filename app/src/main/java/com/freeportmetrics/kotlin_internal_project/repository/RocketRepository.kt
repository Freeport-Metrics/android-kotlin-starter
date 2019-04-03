package com.freeportmetrics.kotlin_internal_project.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.freeportmetrics.kotlin_internal_project.model.Rocket
import com.freeportmetrics.kotlin_internal_project.retrofit.SpaceXService
import io.objectbox.BoxStore
import io.objectbox.kotlin.boxFor
import kotlinx.coroutines.*
import retrofit2.HttpException
import timber.log.Timber

class RocketRepository(private val service: SpaceXService, private val boxStore: BoxStore) :
    BaseRepository<Rocket> {

    override fun fetchData(): LiveData<List<Rocket>> {
        val result = MutableLiveData<List<Rocket>>()

        CoroutineScope(Dispatchers.IO).launch {
            val request = service.getRocketsAsync()
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

    override fun saveToDatabase(data: List<Rocket>) {
        CoroutineScope(Dispatchers.IO).launch {
            boxStore.boxFor<Rocket>().put(data)
        }
    }
}