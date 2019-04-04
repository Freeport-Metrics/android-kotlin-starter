package com.freeportmetrics.kotlin_internal_project.model

import com.freeportmetrics.kotlin_internal_project.helper.LinksConverter
import com.google.gson.annotations.SerializedName
import io.objectbox.annotation.Convert
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class Flight(
    @Id(assignable = true) var id: Long = 0,
    @SerializedName("mission_name") val name: String,
    @SerializedName("launch_date_unix") val launchDate: Long,
    @SerializedName("links") @Convert(converter = LinksConverter::class, dbType = String::class) val urls: FlightLink?
)

// another class needed due to more complicated 'links' JSON
data class FlightLink(
    @SerializedName("reddit_campaign") val reddit_campaign: String?
)