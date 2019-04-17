package com.freeportmetrics.kotlin_internal_project.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.freeportmetrics.kotlin_internal_project.R
import com.freeportmetrics.kotlin_internal_project.helper.DialogHelper
import com.freeportmetrics.kotlin_internal_project.model.Flight
import com.freeportmetrics.kotlin_internal_project.model.Rocket

class GenericAdapter<T>(private val context: Context, private val items: List<T>, private val dialogHelper: DialogHelper)
                    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when {
            items.all { it is Rocket } -> RocketViewHolder(LayoutInflater.from(context).inflate(R.layout.item_rocket, parent, false), dialogHelper)
            items.all { it is Flight } -> FlightViewHolder(LayoutInflater.from(context).inflate(R.layout.item_flight, parent, false), dialogHelper)
            else -> EventViewHolder(LayoutInflater.from(context).inflate(R.layout.item_event, parent, false), dialogHelper)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as Binder<T>).bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    internal interface Binder<T> {
        fun bind(data: T)
    }
}