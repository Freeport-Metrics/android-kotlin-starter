package com.example.krzdabrowski.myapplication.viewmodel

import androidx.lifecycle.LiveData
import com.example.krzdabrowski.myapplication.model.Event
import com.example.krzdabrowski.myapplication.repository.EventRepository

class EventViewModel(private val repository: EventRepository) : BaseViewModel<Event>() {

    private lateinit var eventsList: LiveData<List<Event>>

    override fun getDataFromRetrofit(): LiveData<List<Event>> {
        eventsList = repository.fetchData()
        return eventsList
    }

    override fun saveToDatabase(data: List<Event>) {
        repository.saveToDatabase(data)
    }
}