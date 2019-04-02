package com.example.krzdabrowski.myapplication.database

import android.content.Context
import com.example.krzdabrowski.myapplication.model.MyObjectBox
import io.objectbox.BoxStore

object ObjectBox {

    fun init(context: Context): BoxStore {
        return MyObjectBox.builder().androidContext(context.applicationContext).build()
    }
}