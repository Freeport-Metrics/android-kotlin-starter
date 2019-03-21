package com.example.krzdabrowski.myapplication.helper

import java.text.SimpleDateFormat
import java.util.*

fun epochToDate(epoch: Long): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US)
    return dateFormat.format(java.util.Date(epoch * 1000))
}