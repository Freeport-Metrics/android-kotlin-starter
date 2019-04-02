package com.example.krzdabrowski.myapplication.repository

import androidx.lifecycle.LiveData
import io.objectbox.Box

interface BaseRepository<T> {

    fun fetchData(): LiveData<List<T>>
    fun saveToDatabase(box: Box<T>, data: List<T>)
}