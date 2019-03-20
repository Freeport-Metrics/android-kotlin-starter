package com.example.krzdabrowski.myapplication.model

import com.google.gson.annotations.SerializedName

data class Rocket(
    @SerializedName("rocket_name") val rocketName: String,
    @SerializedName("cost_per_launch") val cost: Int,
    @SerializedName("first_flight") val firstFlight: String,
    @SerializedName("height") val height: Map<String, Double>,
    @SerializedName("mass") val weight: Map<String, Int>,
    @SerializedName("flickr_images") val image: List<String>,
    @SerializedName("wikipedia") val link: String
)
