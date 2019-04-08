package com.freeportmetrics.kotlin_internal_project.repository

import androidx.lifecycle.LiveData
import com.freeportmetrics.kotlin_internal_project.model.Event
import com.freeportmetrics.kotlin_internal_project.retrofit.SpaceXService
import io.objectbox.BoxStore

class EventRepository(service: SpaceXService, store: BoxStore) : BaseRepository<Event>(service, store) {

    override fun loadData(): LiveData<List<Event>> {
        return fetchData { service.getPastEventsAsync() }
    }
}