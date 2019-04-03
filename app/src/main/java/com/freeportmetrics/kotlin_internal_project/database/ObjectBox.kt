package com.freeportmetrics.kotlin_internal_project.database

import android.content.Context
import com.freeportmetrics.kotlin_internal_project.model.MyObjectBox
import io.objectbox.BoxStore

object ObjectBox {

    fun init(context: Context): BoxStore {
        return MyObjectBox.builder().androidContext(context.applicationContext).build()
    }
}