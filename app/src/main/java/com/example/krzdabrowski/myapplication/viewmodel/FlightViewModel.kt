package com.example.krzdabrowski.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.krzdabrowski.myapplication.database.ObjectBox
import com.example.krzdabrowski.myapplication.model.Flight
import com.example.krzdabrowski.myapplication.repository.FlightRepository
import io.objectbox.Box
import io.objectbox.kotlin.boxFor

class FlightViewModel(private val repository: FlightRepository): ViewModel() {

    private lateinit var flightList: LiveData<List<Flight>>
    val box: Box<Flight> = ObjectBox.boxStore.boxFor()

    fun getFlightsFromRetrofit(): LiveData<List<Flight>> {
        flightList = repository.fetchNextFlights()
        return flightList
    }

    fun saveToDatabase(data: List<Flight>) {
        repository.saveToDatabase(box, data)
    }

}