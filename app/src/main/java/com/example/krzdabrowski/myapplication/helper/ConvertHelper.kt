package com.example.krzdabrowski.myapplication.helper

import com.example.krzdabrowski.myapplication.model.FlightLink
import io.objectbox.converter.PropertyConverter
import java.text.SimpleDateFormat
import java.util.*

fun epochToDate(epoch: Long?): String {
    if (epoch != null) {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US)
        return dateFormat.format(java.util.Date(epoch * 1000))
    }
    return ""
}

abstract class BaseMapConverter<V> : PropertyConverter<Map<String, V?>, V?>

class MapToDoubleConverter : BaseMapConverter<Double>() {
    override fun convertToDatabaseValue(entityProperty: Map<String, Double?>): Double? {
        return entityProperty["meters"]
    }

    override fun convertToEntityProperty(databaseValue: Double?): Map<String, Double?> {
        return if (databaseValue != null) {
            mapOf(Pair("meters", databaseValue))
        } else {
            mapOf(Pair("meters", null))
        }
    }
}

class MapToIntConverter : BaseMapConverter<Int>() {
    override fun convertToDatabaseValue(entityProperty: Map<String, Int?>): Int? {
        return entityProperty["kg"]
    }

    override fun convertToEntityProperty(databaseValue: Int?): Map<String, Int?> {
        return if (databaseValue != null) {
            mapOf(Pair("kg", databaseValue))
        } else {
            mapOf(Pair("kg", null))
        }
    }
}

class MapToStringConverter : BaseMapConverter<String>() {
    override fun convertToDatabaseValue(entityProperty: Map<String, String?>): String? {
        return entityProperty["article"]
    }

    override fun convertToEntityProperty(databaseValue: String?): Map<String, String?> {
        return if (databaseValue != null) {
            mapOf(Pair("article", databaseValue))
        } else {
            mapOf(Pair("article", null))
        }
    }
}

class LinksConverter : PropertyConverter<FlightLink, String> {
    override fun convertToDatabaseValue(entityProperty: FlightLink?): String? {
        return entityProperty?.reddit_campaign
    }

    override fun convertToEntityProperty(databaseValue: String?): FlightLink? {
        return if (databaseValue != null) {
            FlightLink(databaseValue)
        } else {
            FlightLink(null)
        }
    }
}

class ListStringConverter : PropertyConverter<List<String>, String> {
    override fun convertToDatabaseValue(entityProperty: List<String>?): String? {
        return entityProperty?.get(0)
    }

    override fun convertToEntityProperty(databaseValue: String?): List<String>? {
        return if (databaseValue != null) {
            listOf(databaseValue)
        } else {
            listOf()
        }
    }
}