package com.example.krzdabrowski.myapplication.model

import com.google.gson.annotations.SerializedName

// Moshi with @Json annotation oddly doesn't parse
data class Flight (
    @SerializedName("flight_number") val flightNumber: Int,
    @SerializedName("mission_name") val missionName: String
)
