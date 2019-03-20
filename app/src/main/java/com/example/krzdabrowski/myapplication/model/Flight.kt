package com.example.krzdabrowski.myapplication.model

import com.google.gson.annotations.SerializedName

// Moshi with @Json annotation oddly doesn't parse
data class Flight (
    @SerializedName("mission_name") val missionName: String,
    @SerializedName("launch_date_unix") val launchDate: Int,
    @SerializedName("links") val link: Map<String, Any>
)
