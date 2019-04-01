package com.example.krzdabrowski.myapplication.helper

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

abstract class BaseMapConverter<V> : PropertyConverter<Map<String, V>, V>

class MapToDoubleConverter : BaseMapConverter<Double>() {
    override fun convertToDatabaseValue(entityProperty: Map<String, Double>?): Double {
        return entityProperty?.get("meters") ?: 0.00
    }

    override fun convertToEntityProperty(databaseValue: Double?): Map<String, Double>? {
        return if (databaseValue != null) {
            mapOf(Pair("meters", databaseValue))
        } else {
            mapOf(Pair("meters", 0.00))
        }
    }
}

class MapToIntConverter : BaseMapConverter<Int>() {
    override fun convertToDatabaseValue(entityProperty: Map<String, Int>?): Int {
        return entityProperty?.get("kg") ?: 0
    }

    override fun convertToEntityProperty(databaseValue: Int?): Map<String, Int> {
        return if (databaseValue != null) {
            mapOf(Pair("kg", databaseValue))
        } else {
            mapOf(Pair("kg", 0))
        }
    }
}

class MapToStringConverter : BaseMapConverter<Any>() {
    override fun convertToDatabaseValue(entityProperty: Map<String, Any>?): String {
        return entityProperty?.get("reddit_campaign") as String? ?: ""
    }

    override fun convertToEntityProperty(databaseValue: Any?): Map<String, Any> {
        return if (databaseValue != null) {
            mapOf(Pair("reddit_campaign", databaseValue as String))
        } else {
            mapOf(Pair("reddit_campaign", ""))
        }
    }

}

class ListStringConverter : PropertyConverter<List<String>, String> {
    override fun convertToDatabaseValue(entityProperty: List<String>?): String {
        return entityProperty?.get(0) ?: ""
    }

    override fun convertToEntityProperty(databaseValue: String?): List<String> {
        return if (databaseValue != null) {
            listOf(databaseValue)
        } else {
            listOf()
        }
    }
}