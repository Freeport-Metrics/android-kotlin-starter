package com.example.krzdabrowski.myapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.krzdabrowski.myapplication.model.Rocket
import com.example.krzdabrowski.myapplication.repository.RocketRepository

class RocketViewModel(private val repository: RocketRepository): ViewModel() {

    fun getRockets(): MutableLiveData<List<Rocket>> {
        return repository.fetchRockets()
    }

}

class RocketFactory(private val repository: RocketRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RocketViewModel(this.repository) as T
    }

}