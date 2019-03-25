package com.example.krzdabrowski.myapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.krzdabrowski.myapplication.model.Flight
import com.example.krzdabrowski.myapplication.retrofit.SpaceXService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import timber.log.Timber

class FlightViewModel: ViewModel() {

    private val service by lazy {
        SpaceXService.create()
    }

    fun getNextFlights(): MutableLiveData<List<Flight>> {
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
}