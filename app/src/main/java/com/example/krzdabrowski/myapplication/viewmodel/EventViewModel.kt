package com.example.krzdabrowski.myapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.krzdabrowski.myapplication.model.Event
import com.example.krzdabrowski.myapplication.repository.EventRepository

class EventViewModel(private val repository: EventRepository): ViewModel() {

    fun getPastEvents(): MutableLiveData<List<Event>> {
        return repository.getPastEvents()
    }

}

class EventFactory(private val repository: EventRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return EventViewModel(repository) as T
    }

}