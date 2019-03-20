package com.example.krzdabrowski.myapplication.model

import com.google.gson.annotations.SerializedName

data class Ship(
    @SerializedName("ship_name") val shipName: String,
    @SerializedName("year_built") val yearBuilt: Int,
    @SerializedName("url") val link: String,
    @SerializedName("image") val image: String
)
