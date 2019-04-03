package com.freeportmetrics.kotlin_internal_project.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.freeportmetrics.kotlin_internal_project.model.Flight
import com.freeportmetrics.kotlin_internal_project.retrofit.SpaceXService
import io.objectbox.BoxStore
import io.objectbox.kotlin.boxFor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import timber.log.Timber

class FlightRepository(private val service: SpaceXService, private val boxStore: BoxStore) :
    BaseRepository<Flight> {

    override fun fetchData(): LiveData<List<Flight>> {
        val result = MutableLiveData<List<Flight>>()

        CoroutineScope(Dispatchers.IO).launch {
            val request = service.getNextFlightsAsync()
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

    override fun saveToDatabase(data: List<Flight>) {
        CoroutineScope(Dispatchers.IO).launch {
            boxStore.boxFor<Flight>().put(data)
        }
    }
}