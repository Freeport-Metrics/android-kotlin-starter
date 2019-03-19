package com.example.krzdabrowski.myapplication.retrofit

import com.example.krzdabrowski.myapplication.model.Model
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface SpaceXLaunchService {

    @GET("launches/upcoming")
    fun getUpcomingLaunches(): Call<List<Model.Flight>>

    companion object {
        fun create(): SpaceXLaunchService {
            val retrofit = Retrofit.Builder()
                    .baseUrl("https://api.spacexdata.com/v3/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            return retrofit.create(SpaceXLaunchService::class.java)
        }
    }
}