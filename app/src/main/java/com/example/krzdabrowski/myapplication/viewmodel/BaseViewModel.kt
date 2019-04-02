package com.example.krzdabrowski.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<T> : ViewModel() {

    abstract fun getDataFromRetrofit(): LiveData<List<T>>
    abstract fun saveToDatabase(data: List<T>)
}