package com.example.krzdabrowski.myapplication.model

import com.google.gson.annotations.SerializedName

data class Event(
    @SerializedName("title") val name: String,
    @SerializedName("event_date_unix") val date: Long,
    @SerializedName("details") val info: String,
    @SerializedName("links") val urls: Map<String, String>
)
