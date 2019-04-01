package com.example.krzdabrowski.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.krzdabrowski.myapplication.model.Rocket
import com.example.krzdabrowski.myapplication.repository.RocketRepository

class RocketViewModel(private val repository: RocketRepository): ViewModel() {

    private lateinit var rocketList: LiveData<List<Rocket>>

    fun getRocketsFromRetrofit(): LiveData<List<Rocket>> {
        rocketList = repository.fetchRockets()

        return rocketList
    }

//    fun getRocketsFromDb(rocketBox: Box<Rocket>): LiveData<Rocket> {
//        val rocketQuery = rocketBox.query().order(Rocket_.name).build()
//        Timber.d("query is: ${rocketBox.query().order(Rocket_.name).build().count()}")
//        rocketList = ObjectBoxLiveData(rocketQuery)
//
////        rocketDb = repository.getRocketsFromDatabase(rocketBox)
//        return rocketList
//    }
}