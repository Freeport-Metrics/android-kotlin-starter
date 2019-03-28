package com.example.krzdabrowski.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.krzdabrowski.myapplication.model.Rocket
import com.example.krzdabrowski.myapplication.repository.RocketRepository

class RocketViewModel(repository: RocketRepository): ViewModel() {

    private val rocketList = repository.fetchRockets()

    fun getRockets(): LiveData<List<Rocket>> {
        return rocketList
    }
}