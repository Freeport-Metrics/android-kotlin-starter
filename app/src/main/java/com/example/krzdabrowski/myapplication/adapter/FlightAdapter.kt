package com.example.krzdabrowski.myapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.krzdabrowski.myapplication.R
import com.example.krzdabrowski.myapplication.model.Model
import kotlinx.android.synthetic.main.item_flight.view.*

class FlightAdapter(private val context: Context, private val flights: List<Model.Flight>)
                    : RecyclerView.Adapter<FlightAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_flight, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.flightNumber.text = flights[position].flightNumber.toString()
        holder.missionName.text = flights[position].missionName
    }

    override fun getItemCount(): Int = flights.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val flightNumber = view.flight_number!!
        val missionName = view.mission_name!!
    }
}