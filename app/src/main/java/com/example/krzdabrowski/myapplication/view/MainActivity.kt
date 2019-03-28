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
import com.example.krzdabrowski.myapplication.model.Rocket
import com.example.krzdabrowski.myapplication.viewmodel.*
import io.objectbox.Box
import io.objectbox.kotlin.boxFor
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
        Timber.plant(Timber.DebugTree())
        startKoin {
            androidLogger()
            androidContext(this@MainActivity)
            modules(listOf(repositoryModule, networkModule, viewModelModule))
        }

        ObjectBox.init(this) // TODO: inject
        val rocketBox: Box<Rocket> = ObjectBox.boxStore.boxFor()

        rv_generic?.layoutManager = LinearLayoutManager(this)
        rv_generic.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        btn_rockets.setOnClickListener { rocketVm.getRockets().observe(this, Observer { rockets -> populateAdapter(rockets) }) }
        btn_next_flights.setOnClickListener { flightVm.getNextFlights().observe(this, Observer { flights -> populateAdapter(flights) }) }
        btn_events.setOnClickListener { eventVm.getPastEvents().observe(this, Observer { events -> populateAdapter(events) }) }
    }

    private fun <T> populateAdapter(data: List<T>) {
        rv_generic.adapter = GenericAdapter(this@MainActivity, data)
    }
}
