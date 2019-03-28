package com.example.krzdabrowski.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.krzdabrowski.myapplication.model.Event
import com.example.krzdabrowski.myapplication.repository.EventRepository

class EventViewModel(repository: EventRepository): ViewModel() {

    private val pastEventsList = repository.fetchPastEvents()

    fun getPastEvents(): LiveData<List<Event>> {
        return pastEventsList
    }

}