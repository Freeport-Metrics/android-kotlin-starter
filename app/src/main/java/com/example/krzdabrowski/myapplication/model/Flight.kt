package com.example.krzdabrowski.myapplication.model

import com.google.gson.annotations.SerializedName
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

// Moshi with @Json annotation oddly doesn't parse
@Entity
data class Flight (
    @Id var id: Long = 0,
    @SerializedName("mission_name") val name: String,
    @SerializedName("launch_date_unix") val launchDate: Long,
    @SerializedName("links") val urls: Map<String, Any>
)
