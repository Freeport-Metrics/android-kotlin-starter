package com.example.krzdabrowski.myapplication.repository

import com.example.krzdabrowski.myapplication.di.DaggerRepositoryComponent
import com.example.krzdabrowski.myapplication.di.NetworkModule
import com.example.krzdabrowski.myapplication.di.RepositoryComponent

abstract class BaseRepository {

    private val injector: RepositoryComponent = DaggerRepositoryComponent
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    private fun inject() {
        when (this) {
            is RocketRepository -> injector.inject(this)
            is FlightRepository -> injector.inject(this)
            is EventRepository -> injector.inject(this)
        }
    }

}