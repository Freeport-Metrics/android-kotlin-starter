package com.example.krzdabrowski.myapplication.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.krzdabrowski.myapplication.model.Rocket
import com.example.krzdabrowski.myapplication.retrofit.SpaceXService
import io.objectbox.Box
import kotlinx.coroutines.*
import retrofit2.HttpException
import timber.log.Timber

class RocketRepository(private val service: SpaceXService) {

    fun fetchRockets(): LiveData<List<Rocket>> {
        val result = MutableLiveData<List<Rocket>>()

        CoroutineScope(Dispatchers.IO).launch {
            val request = service.getRocketsAsync()
            withContext(Dispatchers.Main) {
                try {
                    val response = request.await()
                    if (response.isSuccessful) {
                        result.value = response.body()
                    } else {
                        Timber.e("Error occurred with code ${response.code()}")
                    }
                } catch (e: HttpException) {
                    Timber.e("Error: ${e.message()}")
                } catch (e: Throwable) {
                    Timber.e("Error: ${e.message}")
                }
            }
        }

        return result
    }

    fun saveToDatabase(box: Box<Rocket>, data: List<Rocket>) {
        CoroutineScope(Dispatchers.IO).launch {
            box.put(data)
        }
    }
}