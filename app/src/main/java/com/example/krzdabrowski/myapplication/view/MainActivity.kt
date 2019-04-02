package com.example.krzdabrowski.myapplication.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.krzdabrowski.myapplication.R
import com.example.krzdabrowski.myapplication.database.ObjectBox
import com.example.krzdabrowski.myapplication.di.networkModule
import com.example.krzdabrowski.myapplication.di.repositoryModule
import com.example.krzdabrowski.myapplication.di.viewModelModule
import com.example.krzdabrowski.myapplication.viewmodel.*
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.startKoin
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private val rocketVm: RocketViewModel by viewModel()
    private val flightVm: FlightViewModel by viewModel()
    private val eventVm: EventViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ObjectBox.init(this ) //TODO: inject
        Timber.plant(Timber.DebugTree())
        startKoin {
            androidLogger()
            androidContext(this@MainActivity)
            modules(listOf(repositoryModule, networkModule, viewModelModule))
        }

        rv_generic?.layoutManager = LinearLayoutManager(this)
        rv_generic.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        btn_rockets.setOnClickListener {
            if (rocketVm.box.all == null || rocketVm.box.all.isEmpty()) {
                downloadAndSaveData(rocketVm)
            } else {
                populateAdapter(rocketVm.box.all)
            }
        }

        btn_next_flights.setOnClickListener {
            if (flightVm.box.all == null || flightVm.box.all.isEmpty()) {
                downloadAndSaveData(flightVm)
            } else {
                populateAdapter(flightVm.box.all)
            }
        }

        btn_events.setOnClickListener {
            if (eventVm.box.all == null || eventVm.box.all.isEmpty()) {
                downloadAndSaveData(eventVm)
            } else {
                populateAdapter(eventVm.box.all)
            }
        }
    }

    private fun <T> downloadAndSaveData(viewModel: BaseViewModel<T>) {
        val liveData = viewModel.getDataFromRetrofit()
        liveData.observe(this, Observer {
                events -> populateAdapter(events)
            if (liveData.value != null) viewModel.saveToDatabase(liveData.value!!)
        })

    }

    private fun <T> populateAdapter(data: List<T>) {
        rv_generic.adapter = GenericAdapter(this@MainActivity, data)
    }
}
