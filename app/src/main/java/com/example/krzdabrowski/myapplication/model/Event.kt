package com.example.krzdabrowski.myapplication.model

import com.google.gson.annotations.SerializedName
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class Event(
    @Id var id: Long = 0,
    @SerializedName("title") val name: String,
    @SerializedName("event_date_unix") val date: Long,
    @SerializedName("details") val info: String,
    @SerializedName("links") val urls: Map<String, String>
)
