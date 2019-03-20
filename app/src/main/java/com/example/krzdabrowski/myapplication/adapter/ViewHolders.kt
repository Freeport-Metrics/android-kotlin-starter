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
        view.tv_rocket_cost_per_launch.text = data.cost.toString()
        view.tv_rocket_first_flight.text = data.firstFlight
        view.tv_rocket_height.text = data.height["meters"].toString()
        view.tv_rocket_weight.text = data.weight["kg"].toString()
        Picasso.get().load(data.image[0]).into(view.iv_rocket)
    }
}

class FlightViewHolder(private val view: View) : RecyclerView.ViewHolder(view), GenericAdapter.Binder<Flight> {
    override fun bind(data: Flight) {
        view.tv_flight_name.text = data.name
        view.tv_flight_date.text = data.launchDate.toString()
    }
}

class EventViewHolder(private val view: View) : RecyclerView.ViewHolder(view), GenericAdapter.Binder<Event> {
    override fun bind(data: Event) {
        view.tv_event_name.text = data.name
        view.tv_event_date.text = data.date.toString()
        view.tv_event_info.text = data.info
    }
}