package com.freeportmetrics.kotlin_internal_project.model

import com.freeportmetrics.kotlin_internal_project.helper.ListStringConverter
import com.freeportmetrics.kotlin_internal_project.helper.MapToDoubleConverter
import com.freeportmetrics.kotlin_internal_project.helper.MapToIntConverter
import com.google.gson.annotations.SerializedName
import io.objectbox.annotation.Convert
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class Rocket(
    @Id(assignable = true) var id: Long = 0,
    @SerializedName("rocket_name") val name: String?,
    @SerializedName("cost_per_launch") val cost: Int?,
    @SerializedName("first_flight") val firstFlight: String?,
    @SerializedName("wikipedia") val url: String?,
    @SerializedName("flickr_images") @Convert(converter = ListStringConverter::class, dbType = String::class) val image: List<String>?,
    @SerializedName("height") @Convert(converter = MapToDoubleConverter::class, dbType = Double::class) val height: Map<String, Double?>?,
    @SerializedName("mass") @Convert(converter = MapToIntConverter::class, dbType = Int::class) val weight: Map<String, Int?>?
)