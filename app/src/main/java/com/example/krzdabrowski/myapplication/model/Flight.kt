package com.example.krzdabrowski.myapplication.model

import com.example.krzdabrowski.myapplication.helper.MapToStringConverter
import com.google.gson.annotations.SerializedName
import io.objectbox.annotation.Convert
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class Flight (
    @Id(assignable = true) var id: Long = 0,
    @SerializedName("mission_name") val name: String?,
    @SerializedName("launch_date_unix") val launchDate: Long?,
    @SerializedName("links") @Convert(converter = MapToStringConverter::class, dbType = String::class) val urls: Map<String, Any>?
)
