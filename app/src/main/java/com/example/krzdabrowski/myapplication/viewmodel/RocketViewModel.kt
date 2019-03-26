package com.example.krzdabrowski.myapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.krzdabrowski.myapplication.model.Rocket
import com.example.krzdabrowski.myapplication.repository.RocketRepository

class RocketViewModel(repository: RocketRepository): ViewModel() {

    private val rocketList = repository.fetchRockets()

    fun getRockets(): MutableLiveData<List<Rocket>> {
        return rocketList
    }

}

class RocketFactory(private val repository: RocketRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RocketViewModel(this.repository) as T
    }

}