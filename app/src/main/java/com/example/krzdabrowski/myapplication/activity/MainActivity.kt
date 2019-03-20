package com.example.krzdabrowski.myapplication.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.krzdabrowski.myapplication.R
import com.example.krzdabrowski.myapplication.adapter.GenericAdapter
import com.example.krzdabrowski.myapplication.model.Flight
import com.example.krzdabrowski.myapplication.model.Rocket
import com.example.krzdabrowski.myapplication.model.Ship
import com.example.krzdabrowski.myapplication.retrofit.SpaceXLaunchService
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private val service by lazy {
        SpaceXLaunchService.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Timber.plant(Timber.DebugTree())

        rv_generic.layoutManager = LinearLayoutManager(this)
        rv_generic.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        btn_rockets.setOnClickListener { downloadRockets() }
        btn_next_launches.setOnClickListener { downloadNextFlights() }
        btn_ships.setOnClickListener { downloadShips() }
    }

    private fun downloadRockets() {
        val call = service.getRockets()
        call.enqueue(object : Callback<List<Rocket>> {
            override fun onResponse(call: Call<List<Rocket>>, response: Response<List<Rocket>>) {
                populateAdapter(response)
            }

            override fun onFailure(call: Call<List<Rocket>>, t: Throwable) {
                Timber.d("Error occurred: $t")
            }
        })
    }

    private fun downloadNextFlights() {
        val call = service.getUpcomingLaunches()
        call.enqueue(object : Callback<List<Flight>> {
            override fun onResponse(call: Call<List<Flight>>, response: Response<List<Flight>>) {
                populateAdapter(response)
            }

            override fun onFailure(call: Call<List<Flight>>, t: Throwable) {
                Timber.d("Error occurred: $t")
            }
        })
    }

    private fun downloadShips() {
        val call = service.getShips()
        call.enqueue(object : Callback<List<Ship>> {
            override fun onResponse(call: Call<List<Ship>>, response: Response<List<Ship>>) {
                populateAdapter(response)
            }

            override fun onFailure(call: Call<List<Ship>>, t: Throwable) {
                Timber.d("Error occurred: $t")
            }
        })
    }

    private fun <T> populateAdapter(response: Response<List<T>>) {
        val data = response.body()
        if (data != null) {
            rv_generic.adapter = GenericAdapter(this@MainActivity, data)
        }
    }
}
