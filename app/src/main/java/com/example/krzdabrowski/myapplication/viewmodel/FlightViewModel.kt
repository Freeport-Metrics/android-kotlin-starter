package com.example.krzdabrowski.myapplication.viewmodel

import androidx.lifecycle.LiveData
import com.example.krzdabrowski.myapplication.model.Flight
import com.example.krzdabrowski.myapplication.repository.FlightRepository

class FlightViewModel(private val repository: FlightRepository) : BaseViewModel<Flight>() {

    private lateinit var flightList: LiveData<List<Flight>>

    override fun getDataFromRetrofit(): LiveData<List<Flight>> {
        flightList = repository.fetchData()
        return flightList
    }

    override fun saveToDatabase(data: List<Flight>) {
        repository.saveToDatabase(data)
    }
}