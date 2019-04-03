package com.freeportmetrics.kotlin_internal_project.viewmodel

import androidx.lifecycle.LiveData
import com.freeportmetrics.kotlin_internal_project.model.Rocket
import com.freeportmetrics.kotlin_internal_project.repository.RocketRepository

class RocketViewModel(private val repository: RocketRepository) : BaseViewModel<Rocket>() {

    private lateinit var rocketList: LiveData<List<Rocket>>

    override fun getDataFromRetrofit(): LiveData<List<Rocket>> {
        rocketList = repository.fetchData()
        return rocketList
    }

    override fun saveToDatabase(data: List<Rocket>) {
        repository.saveToDatabase(data)
    }
}