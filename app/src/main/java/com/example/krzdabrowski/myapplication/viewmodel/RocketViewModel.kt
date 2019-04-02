package com.example.krzdabrowski.myapplication.viewmodel

import androidx.lifecycle.LiveData
import com.example.krzdabrowski.myapplication.model.Rocket
import com.example.krzdabrowski.myapplication.repository.RocketRepository

class RocketViewModel(private val repository: RocketRepository): BaseViewModel<Rocket>() {

    private lateinit var rocketList: LiveData<List<Rocket>>

    override fun getDataFromRetrofit(): LiveData<List<Rocket>> {
        rocketList = repository.fetchData()
        return rocketList
    }

    override fun saveToDatabase(data: List<Rocket>) {
        repository.saveToDatabase(data)
    }
}