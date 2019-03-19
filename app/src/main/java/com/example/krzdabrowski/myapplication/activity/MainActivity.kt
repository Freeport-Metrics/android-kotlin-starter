package com.example.krzdabrowski.myapplication.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.krzdabrowski.myapplication.R
import com.example.krzdabrowski.myapplication.model.Model
import com.example.krzdabrowski.myapplication.retrofit.SpaceXLaunchService
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

        downloadDataFromApi()
    }

    private fun downloadDataFromApi() {
        val call = service.getUpcomingLaunches()
        call.enqueue(object : Callback<List<Model.Flight>> {
            override fun onResponse(call: Call<List<Model.Flight>>, response: Response<List<Model.Flight>>) {
                val data = response.body()
                Timber.d("$data")
                if (data != null) {
                    Timber.d("First mission name is: ${data[0].missionName}")
                }
            }

            override fun onFailure(call: Call<List<Model.Flight>>, t: Throwable) {
                Timber.d("Error occurred: $t")
            }
        })
    }
}
