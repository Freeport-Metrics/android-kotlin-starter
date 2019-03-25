package com.example.krzdabrowski.myapplication.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.krzdabrowski.myapplication.R
import com.example.krzdabrowski.myapplication.adapter.GenericAdapter
import com.example.krzdabrowski.myapplication.repository.EventRepository
import com.example.krzdabrowski.myapplication.repository.FlightRepository
import com.example.krzdabrowski.myapplication.repository.RocketRepository
import com.example.krzdabrowski.myapplication.viewmodel.*
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Timber.plant(Timber.DebugTree())

        val rocketVm = ViewModelProviders.of(this, RocketFactory(RocketRepository())).get(RocketViewModel::class.java)
        val flightVm = ViewModelProviders.of(this, FlightFactory(FlightRepository())).get(FlightViewModel::class.java)
        val eventVm = ViewModelProviders.of(this, EventFactory(EventRepository())).get(EventViewModel::class.java)

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
