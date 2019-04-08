package com.freeportmetrics.kotlin_internal_project.repository

import androidx.lifecycle.LiveData
import com.freeportmetrics.kotlin_internal_project.model.Flight
import com.freeportmetrics.kotlin_internal_project.retrofit.SpaceXService
import io.objectbox.BoxStore

class FlightRepository(service: SpaceXService, store: BoxStore) : BaseRepository<Flight>(service, store) {

    override fun loadData(): LiveData<List<Flight>> {
        return fetchData { service.getNextFlightsAsync() }
    }
}