package com.example.krzdabrowski.myapplication.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.krzdabrowski.myapplication.R
import com.example.krzdabrowski.myapplication.adapter.GenericAdapter
import com.example.krzdabrowski.myapplication.retrofit.SpaceXService
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import retrofit2.HttpException
import retrofit2.Response
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private val service by lazy {
        SpaceXService.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Timber.plant(Timber.DebugTree())

        rv_generic.layoutManager = LinearLayoutManager(this)
        rv_generic.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        btn_rockets.setOnClickListener { downloadRockets() }
        btn_next_flights.setOnClickListener { downloadNextFlights() }
        btn_ships.setOnClickListener { downloadPastEvents() }
    }

    private fun downloadRockets() {
        CoroutineScope(Dispatchers.IO).launch {
            val request = service.getRocketsAsync()
            withContext(Dispatchers.Main) {
                try {
                    val response = request.await()
                    if (response.isSuccessful) {
                        populateAdapter(response)
                    } else {
                        Timber.d("Error occurred with code ${response.code()}")
                    }
                } catch (e: HttpException) {
                    Timber.d("Error: ${e.message()}")
                } catch (e: Throwable) {
                    Timber.d("Error: ${e.message}")
                }
            }
        }
    }

    private fun downloadNextFlights() {
        CoroutineScope(Dispatchers.IO).launch {
            val request = service.getNextFlightsAsync()
            withContext(Dispatchers.Main) {
                try {
                    val response = request.await()
                    if (response.isSuccessful) {
                        populateAdapter(response)
                    } else {
                        Timber.d("Error occurred with code ${response.code()}")
                    }
                } catch (e: HttpException) {
                    Timber.d("Error: ${e.message()}")
                } catch (e: Throwable) {
                    Timber.d("Error: ${e.message}")
                }
            }
        }
    }

    private fun downloadPastEvents() {
        CoroutineScope(Dispatchers.IO).launch {
            val request = service.getPastEventsAsync()
            withContext(Dispatchers.Main) {
                try {
                    val response = request.await()
                    if (response.isSuccessful) {
                        populateAdapter(response)
                    } else {
                        Timber.d("Error occurred with code ${response.code()}")
                    }
                } catch (e: HttpException) {
                    Timber.d("Error: ${e.message()}")
                } catch (e: Throwable) {
                    Timber.d("Error: ${e.message}")
                }
            }
        }
    }

    private fun <T> populateAdapter(response: Response<List<T>>) {
        val data = response.body()
        if (data != null) {
            rv_generic.adapter = GenericAdapter(this@MainActivity, data)
        }
    }
}
