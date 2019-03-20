package com.example.krzdabrowski.myapplication.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.krzdabrowski.myapplication.model.Flight
import com.example.krzdabrowski.myapplication.model.Rocket
import com.example.krzdabrowski.myapplication.model.Event
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_event.view.*
import kotlinx.android.synthetic.main.item_flight.view.*
import kotlinx.android.synthetic.main.item_rocket.view.*

class RocketViewHolder(private val view: View) : RecyclerView.ViewHolder(view), GenericAdapter.Binder<Rocket> {
    override fun bind(data: Rocket) {
        view.tv_rocket_name.text = data.name
        view.tv_rocket_cost_per_launch.text = "Cost per launch: \$${data.cost/1_000_000}m"
        view.tv_rocket_first_flight.text = "First flight: ${data.firstFlight}"
        view.tv_rocket_height.text = "Height: ${data.height["meters"].toString()}m"
        view.tv_rocket_weight.text = "Weight: ${data.weight["kg"].toString()}kg"
        Picasso.get().load(data.image[0]).into(view.iv_rocket)
    }
}

class FlightViewHolder(private val view: View) : RecyclerView.ViewHolder(view), GenericAdapter.Binder<Flight> {
    override fun bind(data: Flight) {
        view.tv_flight_name.text = "Mission name: ${data.name}"
        view.tv_flight_date.text = "Date: ${data.launchDate}"
    }
}

class EventViewHolder(private val view: View) : RecyclerView.ViewHolder(view), GenericAdapter.Binder<Event> {
    override fun bind(data: Event) {
        view.tv_event_name.text = data.name
        view.tv_event_date.text = "Date: ${data.date}"
        view.tv_event_info.text = data.info
    }
}