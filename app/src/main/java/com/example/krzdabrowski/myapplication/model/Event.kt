package com.example.krzdabrowski.myapplication.model

import com.example.krzdabrowski.myapplication.helper.MapToStringConverter
import com.google.gson.annotations.SerializedName
import io.objectbox.annotation.Convert
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class Event(
    @Id(assignable = true) var id: Long = 0,
    @SerializedName("title") val name: String?,
    @SerializedName("event_date_unix") val date: Long?,
    @SerializedName("details") val info: String?,
    @SerializedName("links") @Convert(converter = MapToStringConverter::class, dbType = String::class) val urls: Map<String, String?>?
)
