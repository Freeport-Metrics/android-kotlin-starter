package com.example.krzdabrowski.myapplication.model

import com.google.gson.annotations.SerializedName

// Moshi with @Json annotation oddly doesn't parse
data class Flight (
    @SerializedName("mission_name") val name: String,
    @SerializedName("launch_date_unix") val launchDate: Long,
    @SerializedName("links") val urls: Map<String, Any>
)
