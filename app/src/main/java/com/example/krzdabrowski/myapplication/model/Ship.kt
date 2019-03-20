package com.example.krzdabrowski.myapplication.model

import com.google.gson.annotations.SerializedName

data class Ship(
    @SerializedName("ship_id") val shipId: String,
    @SerializedName("ship_name") val shipName: String
)
