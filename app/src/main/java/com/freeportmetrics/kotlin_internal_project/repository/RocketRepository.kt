package com.freeportmetrics.kotlin_internal_project.repository

import androidx.lifecycle.LiveData
import com.freeportmetrics.kotlin_internal_project.model.Rocket
import com.freeportmetrics.kotlin_internal_project.retrofit.SpaceXService
import io.objectbox.BoxStore

class RocketRepository(service: SpaceXService, store: BoxStore) : BaseRepository<Rocket>(service, store) {

    override fun loadData(): LiveData<List<Rocket>> {
        return fetchData { service.getRocketsAsync() }
    }
}