package com.freeportmetrics.kotlin_internal_project.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.freeportmetrics.kotlin_internal_project.R
import com.freeportmetrics.kotlin_internal_project.model.Flight
import com.freeportmetrics.kotlin_internal_project.model.Rocket

class GenericAdapter<T>(private val context: Context, private val items: List<T>)
                    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when {
            items.all { it is Rocket } -> RocketViewHolder(LayoutInflater.from(context).inflate(R.layout.item_rocket, parent, false))
            items.all { it is Flight } -> FlightViewHolder(LayoutInflater.from(context).inflate(R.layout.item_flight, parent, false))
            else -> EventViewHolder(LayoutInflater.from(context).inflate(R.layout.item_event, parent, false))
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