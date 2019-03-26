package com.example.krzdabrowski.myapplication.di

import com.example.krzdabrowski.myapplication.repository.EventRepository
import com.example.krzdabrowski.myapplication.repository.FlightRepository
import com.example.krzdabrowski.myapplication.repository.RocketRepository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class)])
interface RepositoryComponent {
    fun inject(rocketRepository: RocketRepository)
    fun inject(flightRepository: FlightRepository)
    fun inject(eventRepository: EventRepository)

    @Component.Builder
    interface Builder {
        fun build(): RepositoryComponent
        fun networkModule(networkModule: NetworkModule): Builder
    }
}