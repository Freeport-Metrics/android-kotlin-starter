package com.example.krzdabrowski.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.krzdabrowski.myapplication.model.Flight
import com.example.krzdabrowski.myapplication.repository.FlightRepository

class FlightViewModel(repository: FlightRepository): ViewModel() {

    private val nextFlightsList = repository.fetchNextFlights()

    fun getNextFlights(): LiveData<List<Flight>> {
        return nextFlightsList
    }

}