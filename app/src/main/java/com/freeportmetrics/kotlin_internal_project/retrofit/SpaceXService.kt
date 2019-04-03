package com.freeportmetrics.kotlin_internal_project.retrofit

import com.freeportmetrics.kotlin_internal_project.BuildConfig
import com.freeportmetrics.kotlin_internal_project.model.Flight
import com.freeportmetrics.kotlin_internal_project.model.Rocket
import com.freeportmetrics.kotlin_internal_project.model.Event
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

    companion object {
        fun create(): SpaceXService {
            return Retrofit.Builder()
                .baseUrl(BuildConfig.SPACEX_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
                .create(SpaceXService::class.java)
        }
    }
}