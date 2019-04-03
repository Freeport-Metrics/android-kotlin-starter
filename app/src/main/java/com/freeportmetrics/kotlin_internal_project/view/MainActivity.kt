package com.freeportmetrics.kotlin_internal_project.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.freeportmetrics.kotlin_internal_project.R
import com.freeportmetrics.kotlin_internal_project.di.boxModule
import com.freeportmetrics.kotlin_internal_project.di.networkModule
import com.freeportmetrics.kotlin_internal_project.di.repositoryModule
import com.freeportmetrics.kotlin_internal_project.di.viewModelModule
import com.freeportmetrics.kotlin_internal_project.helper.NEXT_FLIGHTS
import com.freeportmetrics.kotlin_internal_project.helper.PAST_EVENTS
import com.freeportmetrics.kotlin_internal_project.helper.ROCKETS
import com.freeportmetrics.kotlin_internal_project.model.Event
import com.freeportmetrics.kotlin_internal_project.model.Flight
import com.freeportmetrics.kotlin_internal_project.model.Rocket
import com.freeportmetrics.kotlin_internal_project.viewmodel.BaseViewModel
import com.freeportmetrics.kotlin_internal_project.viewmodel.EventViewModel
import com.freeportmetrics.kotlin_internal_project.viewmodel.FlightViewModel
import com.freeportmetrics.kotlin_internal_project.viewmodel.RocketViewModel
import io.objectbox.BoxStore
import io.objectbox.kotlin.boxFor
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.startKoin
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    private val boxStore: BoxStore by inject()
    private val rocketVm: RocketViewModel by viewModel()
    private val flightVm: FlightViewModel by viewModel()
    private val eventVm: EventViewModel by viewModel()
    private var currentDataType = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Timber.plant(Timber.DebugTree())
        startKoin {
            androidLogger()
            androidContext(this@MainActivity)
            modules(listOf(repositoryModule, networkModule, boxModule, viewModelModule))
        }

        rv_generic?.layoutManager = LinearLayoutManager(this)
        rv_generic.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        setListeners()
    }

    private fun setListeners() {
        val rocketBox = boxStore.boxFor<Rocket>()
        val flightBox = boxStore.boxFor<Flight>()
        val eventBox = boxStore.boxFor<Event>()

        btn_rockets.setOnClickListener {
            currentDataType = ROCKETS
            if (rocketBox.all == null || rocketBox.all.isEmpty()) {
                downloadAndSaveData(rocketVm)
            } else {
                populateAdapter(rocketBox.all)
            }
        }

        btn_next_flights.setOnClickListener {
            currentDataType = NEXT_FLIGHTS
            if (flightBox.all == null || flightBox.all.isEmpty()) {
                downloadAndSaveData(flightVm)
            } else {
                populateAdapter(flightBox.all)
            }
        }

        btn_events.setOnClickListener {
            currentDataType = PAST_EVENTS
            if (eventBox.all == null || eventBox.all.isEmpty()) {
                downloadAndSaveData(eventVm)
            } else {
                populateAdapter(eventBox.all)
            }
        }

        swipe_refresh.setOnRefreshListener {
            when (currentDataType) {
                ROCKETS -> downloadAndSaveData(rocketVm)
                NEXT_FLIGHTS -> downloadAndSaveData(flightVm)
                PAST_EVENTS -> downloadAndSaveData(eventVm)
            }
        }
    }

    private fun <T> downloadAndSaveData(viewModel: BaseViewModel<T>) {
        val liveData = viewModel.getDataFromRetrofit()
        liveData.observe(this, Observer { data ->
            if (data != null) {
                populateAdapter(data)
                viewModel.saveToDatabase(data)
                swipe_refresh.isRefreshing = false
            }
        })
    }

    private fun <T> populateAdapter(data: List<T>) {
        rv_generic.adapter = GenericAdapter(this@MainActivity, data)
    }
}
