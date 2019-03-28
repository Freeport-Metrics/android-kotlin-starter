package com.example.krzdabrowski.myapplication.model

import com.google.gson.annotations.SerializedName
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class Rocket(
    @Id var id: Long = 0,
    @SerializedName("rocket_name") val name: String,
    @SerializedName("cost_per_launch") val cost: Int,
    @SerializedName("first_flight") val firstFlight: String,
    @SerializedName("height") val height: Map<String, Double>,
    @SerializedName("mass") val weight: Map<String, Int>,
    @SerializedName("flickr_images") val image: List<String>,
    @SerializedName("wikipedia") val url: String
)
