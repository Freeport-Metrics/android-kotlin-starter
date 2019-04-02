package com.example.krzdabrowski.myapplication.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.krzdabrowski.myapplication.R
import com.example.krzdabrowski.myapplication.di.boxModule
import com.example.krzdabrowski.myapplication.di.networkModule
import com.example.krzdabrowski.myapplication.di.repositoryModule
import com.example.krzdabrowski.myapplication.di.viewModelModule
import com.example.krzdabrowski.myapplication.helper.NEXT_FLIGHTS
import com.example.krzdabrowski.myapplication.helper.PAST_EVENTS
import com.example.krzdabrowski.myapplication.helper.ROCKETS
import com.example.krzdabrowski.myapplication.model.Event
import com.example.krzdabrowski.myapplication.model.Flight
import com.example.krzdabrowski.myapplication.model.Rocket
import com.example.krzdabrowski.myapplication.viewmodel.*
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

        val rocketBox = boxStore.boxFor<Rocket>()
        val flightBox = boxStore.boxFor<Flight>()
        val eventBox = boxStore.boxFor<Event>()

        rv_generic?.layoutManager = LinearLayoutManager(this)
        rv_generic.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

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
        liveData.observe(this, Observer {
            events -> populateAdapter(events)
            if (liveData.value != null) viewModel.saveToDatabase(liveData.value!!)
            swipe_refresh.isRefreshing = false
        })
    }

    private fun <T> populateAdapter(data: List<T>) {
        rv_generic.adapter = GenericAdapter(this@MainActivity, data)
    }
}
