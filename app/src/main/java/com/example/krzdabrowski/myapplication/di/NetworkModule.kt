package com.example.krzdabrowski.myapplication.di

import com.example.krzdabrowski.myapplication.retrofit.SpaceXService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.Reusable
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
object NetworkModule {
    private const val BASE_URL = "https://api.spacexdata.com/v3/"

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideSpaceXApi(): SpaceXService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create(SpaceXService::class.java)
    }

}