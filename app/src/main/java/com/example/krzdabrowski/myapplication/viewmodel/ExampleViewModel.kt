package com.example.krzdabrowski.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import com.example.krzdabrowski.myapplication.model.Rocket
import com.example.krzdabrowski.myapplication.model.Rocket_
import io.objectbox.Box
import io.objectbox.android.ObjectBoxLiveData
import timber.log.Timber

class ExampleViewModel: ViewModel() {

    private lateinit var rocketDb: ObjectBoxLiveData<Rocket>

    fun getRocketsFromDb(rocketBox: Box<Rocket>): ObjectBoxLiveData<Rocket> {
        val rocketQuery = rocketBox.query().order(Rocket_.name).build()
        Timber.d("query is: ${rocketBox.query().order(Rocket_.name).build().count()}")
        rocketDb = ObjectBoxLiveData(rocketQuery)
        return rocketDb
    }
}