package com.freeportmetrics.kotlin_internal_project.repository

import androidx.lifecycle.LiveData

interface BaseRepository<T> {

    fun fetchData(): LiveData<List<T>>
    fun saveToDatabase(data: List<T>)
}