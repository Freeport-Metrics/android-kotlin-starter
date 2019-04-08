package com.freeportmetrics.kotlin_internal_project.viewmodel

import androidx.lifecycle.LiveData
import com.freeportmetrics.kotlin_internal_project.model.Event
import com.freeportmetrics.kotlin_internal_project.repository.EventRepository

class EventViewModel(private val repository: EventRepository) : BaseViewModel<Event>() {

    private lateinit var eventsList: LiveData<List<Event>>

    override fun getDataFromRetrofit(): LiveData<List<Event>> {
        eventsList = repository.loadData()
        return eventsList
    }

    override fun saveToDatabase(data: List<Event>) {
        repository.saveToDatabase(data)
    }
}