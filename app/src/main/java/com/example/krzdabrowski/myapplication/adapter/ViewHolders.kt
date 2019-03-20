package com.example.krzdabrowski.myapplication.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.krzdabrowski.myapplication.model.Flight
import com.example.krzdabrowski.myapplication.model.Rocket
import com.example.krzdabrowski.myapplication.model.Ship
import kotlinx.android.synthetic.main.item_rocket.view.*

class FlightViewHolder(private val view: View) : RecyclerView.ViewHolder(view), GenericAdapter.Binder<Flight> {
    override fun bind(data: Flight) {
        view.tv_id.text = data.flightNumber.toString()
        view.tv_details.text = data.missionName
    }
}

class RocketViewHolder(private val view: View) : RecyclerView.ViewHolder(view), GenericAdapter.Binder<Rocket> {
    override fun bind(data: Rocket) {
        view.tv_id.text = data.rocketId.toString()
        view.tv_details.text = data.rocketName
    }
}

class ShipViewHolder(private val view: View) : RecyclerView.ViewHolder(view), GenericAdapter.Binder<Ship> {
    override fun bind(data: Ship) {
        view.tv_id.text = data.shipId
        view.tv_details.text = data.shipName
    }
}