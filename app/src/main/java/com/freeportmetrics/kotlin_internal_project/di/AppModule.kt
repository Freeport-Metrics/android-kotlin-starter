package com.freeportmetrics.kotlin_internal_project.di

import com.freeportmetrics.kotlin_internal_project.database.ObjectBox
import com.freeportmetrics.kotlin_internal_project.helper.DarkModeHelper
import com.freeportmetrics.kotlin_internal_project.helper.DialogHelper
import com.freeportmetrics.kotlin_internal_project.repository.EventRepository
import com.freeportmetrics.kotlin_internal_project.repository.FlightRepository
import com.freeportmetrics.kotlin_internal_project.repository.RocketRepository
import com.freeportmetrics.kotlin_internal_project.retrofit.SpaceXService
import com.freeportmetrics.kotlin_internal_project.viewmodel.EventViewModel
import com.freeportmetrics.kotlin_internal_project.viewmodel.FlightViewModel
import com.freeportmetrics.kotlin_internal_project.viewmodel.RocketViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val helperModule = module {
    single { DarkModeHelper(androidContext()) }
    single { DialogHelper(androidContext()) }
}

val networkModule = module {
    single { SpaceXService.create() }
}

val boxModule = module {
    single { ObjectBox.init(androidContext()) }
}

val repositoryModule = module {
    single { RocketRepository(get(), get()) }
    single { FlightRepository(get(), get()) }
    single { EventRepository(get(), get()) }
}

val viewModelModule = module {
    viewModel { RocketViewModel(get()) }
    viewModel { FlightViewModel(get()) }
    viewModel { EventViewModel(get()) }
}
