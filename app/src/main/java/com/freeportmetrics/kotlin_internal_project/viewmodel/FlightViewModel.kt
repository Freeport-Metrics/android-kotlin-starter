package com.freeportmetrics.kotlin_internal_project.viewmodel

import androidx.lifecycle.LiveData
import com.freeportmetrics.kotlin_internal_project.model.Flight
import com.freeportmetrics.kotlin_internal_project.repository.FlightRepository

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