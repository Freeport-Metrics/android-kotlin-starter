package com.example.krzdabrowski.myapplication.model

import com.google.gson.annotations.SerializedName

data class Rocket(
    @SerializedName("id") val rocketId: Int,
    @SerializedName("rocket_name") val rocketName: String
)
