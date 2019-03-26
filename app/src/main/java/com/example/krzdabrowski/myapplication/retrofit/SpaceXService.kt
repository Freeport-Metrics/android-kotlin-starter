package com.example.krzdabrowski.myapplication.retrofit

import com.example.krzdabrowski.myapplication.model.Flight
import com.example.krzdabrowski.myapplication.model.Rocket
import com.example.krzdabrowski.myapplication.model.Event
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface SpaceXService {

    @GET("rockets")
    fun getRocketsAsync(): Deferred<Response<List<Rocket>>>

    @GET("launches/upcoming")
    fun getNextFlightsAsync(): Deferred<Response<List<Flight>>>

    @GET("history")
    fun getPastEventsAsync(): Deferred<Response<List<Event>>>
}