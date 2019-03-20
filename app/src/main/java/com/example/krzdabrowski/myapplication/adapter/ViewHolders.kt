package com.example.krzdabrowski.myapplication.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.krzdabrowski.myapplication.model.Flight
import com.example.krzdabrowski.myapplication.model.Rocket
import com.example.krzdabrowski.myapplication.model.Ship
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_flight.view.*
import kotlinx.android.synthetic.main.item_rocket.view.*
import kotlinx.android.synthetic.main.item_ship.view.*

class RocketViewHolder(private val view: View) : RecyclerView.ViewHolder(view), GenericAdapter.Binder<Rocket> {
    override fun bind(data: Rocket) {
        view.tv_rocket_name.text = data.rocketName
        view.tv_cost_per_launch.text = data.cost.toString()
        view.tv_first_flight.text = data.firstFlight
        view.tv_height.text = data.height["meters"].toString()
        view.tv_weight.text = data.weight["kg"].toString()
        Picasso.get().load(data.image[0]).into(view.iv_rocket)
    }
}

class FlightViewHolder(private val view: View) : RecyclerView.ViewHolder(view), GenericAdapter.Binder<Flight> {
    override fun bind(data: Flight) {
        view.tv_mission_name.text = data.missionName
        view.tv_launch_date.text = data.launchDate.toString()
    }
}

class ShipViewHolder(private val view: View) : RecyclerView.ViewHolder(view), GenericAdapter.Binder<Ship> {
    override fun bind(data: Ship) {
        view.tv_ship_name.text = data.shipName
        view.tv_year_built.text = data.yearBuilt.toString()
    }
}