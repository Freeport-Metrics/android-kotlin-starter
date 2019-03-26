package com.example.krzdabrowski.myapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.krzdabrowski.myapplication.model.Flight
import com.example.krzdabrowski.myapplication.repository.FlightRepository

class FlightViewModel(repository: FlightRepository): ViewModel() {

    private val nextFlightsList = repository.fetchNextFlights()

    fun getNextFlights(): MutableLiveData<List<Flight>> {
        return nextFlightsList
    }

}

class FlightFactory(private val repository: FlightRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FlightViewModel(this.repository) as T
    }

}