package com.example.krzdabrowski.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.krzdabrowski.myapplication.database.ObjectBox
import com.example.krzdabrowski.myapplication.model.Rocket
import com.example.krzdabrowski.myapplication.repository.RocketRepository
import io.objectbox.Box
import io.objectbox.kotlin.boxFor

class RocketViewModel(private val repository: RocketRepository): ViewModel() {

    private lateinit var rocketList: LiveData<List<Rocket>>
    val box: Box<Rocket> = ObjectBox.boxStore.boxFor()

    fun getRocketsFromRetrofit(): LiveData<List<Rocket>> {
        rocketList = repository.fetchRockets()
        return rocketList
    }

    fun saveToDatabase(data: List<Rocket>?) {
        repository.saveToDatabase(box, data)
    }
}