package com.freeportmetrics.kotlin_internal_project.helper

import com.freeportmetrics.kotlin_internal_project.model.Flight.Link
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

abstract class BaseMapConverter<V>(private val property: String) : PropertyConverter<Map<String, V?>, V?> {
    override fun convertToDatabaseValue(entityProperty: Map<String, V?>): V? {
        return entityProperty[property]
    }

    override fun convertToEntityProperty(databaseValue: V?): Map<String, V?> {
        return if (databaseValue != null) {
            mapOf(Pair(property, databaseValue))
        } else {
            mapOf(Pair(property, null))
        }
    }
}

class MapToDoubleConverter : BaseMapConverter<Double>("meters")

class MapToIntConverter : BaseMapConverter<Int>("kg")

class MapToStringConverter : BaseMapConverter<String>("article")

class LinksConverter : PropertyConverter<Link, String> {
    override fun convertToDatabaseValue(entityProperty: Link?): String? {
        return entityProperty?.reddit_campaign
    }

    override fun convertToEntityProperty(databaseValue: String?): Link? {
        return if (databaseValue != null) {
            Link(databaseValue)
        } else {
            Link(null)
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