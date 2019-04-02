package com.example.krzdabrowski.myapplication.repository

import androidx.lifecycle.LiveData

interface BaseRepository<T> {

    fun fetchData(): LiveData<List<T>>
    fun saveToDatabase(data: List<T>)
}