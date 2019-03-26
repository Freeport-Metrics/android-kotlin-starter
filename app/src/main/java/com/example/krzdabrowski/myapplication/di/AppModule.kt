package com.example.krzdabrowski.myapplication.di

import com.example.krzdabrowski.myapplication.repository.EventRepository
import com.example.krzdabrowski.myapplication.repository.FlightRepository
import com.example.krzdabrowski.myapplication.repository.RocketRepository
import com.example.krzdabrowski.myapplication.retrofit.SpaceXService
import com.example.krzdabrowski.myapplication.viewmodel.EventViewModel
import com.example.krzdabrowski.myapplication.viewmodel.FlightViewModel
import com.example.krzdabrowski.myapplication.viewmodel.RocketViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val networkModule = module {
    single { SpaceXService.create() }
}

val repositoryModule = module {
    single { RocketRepository(get()) }
    single { FlightRepository(get()) }
    single { EventRepository(get()) }
}

val viewModelModule = module {
    viewModel { RocketViewModel(get()) }
    viewModel { FlightViewModel(get()) }
    viewModel { EventViewModel(get()) }
}