package com.example.krzdabrowski.myapplication.adapter

import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.example.krzdabrowski.myapplication.R
import com.example.krzdabrowski.myapplication.helper.epochToDate
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
        view.tv_rocket_cost_per_launch.text = view.context.getString(R.string.rocket_name, data.cost / 1_000_000)
        view.tv_rocket_first_flight.text = view.context.getString(R.string.rocket_first_flight, data.firstFlight)
        view.tv_rocket_height.text = view.context.getString(R.string.rocket_height, data.height["meters"])
        view.tv_rocket_weight.text = view.context.getString(R.string.rocket_weight, data.weight["kg"]?.div(1_000))
        Picasso.get().load(data.image[0]).into(view.iv_rocket)

        view.setOnClickListener {
            if (data.url != null) {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = data.url.toUri()
                view.context.startActivity(intent)
            } else {
                Toast.makeText(view.context, view.context.getString(R.string.toast_no_link), Toast.LENGTH_SHORT).show()
            }
        }
    }
}

class FlightViewHolder(private val view: View) : RecyclerView.ViewHolder(view), GenericAdapter.Binder<Flight> {
    override fun bind(data: Flight) {
        view.tv_flight_name.text = view.context.getString(R.string.flight_name, data.name)
        view.tv_flight_date.text = view.context.getString(R.string.flight_event_date, epochToDate(data.launchDate))

        view.setOnClickListener {
            if (data.urls["reddit_campaign"] != null) {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = data.urls["reddit_campaign"].toString().toUri()
                view.context.startActivity(intent)
            } else {
                Toast.makeText(view.context, view.context.getString(R.string.toast_no_campaign), Toast.LENGTH_SHORT).show()
            }
        }
    }
}

class EventViewHolder(private val view: View) : RecyclerView.ViewHolder(view), GenericAdapter.Binder<Event> {
    override fun bind(data: Event) {
        view.tv_event_name.text = data.name
        view.tv_event_date.text = view.context.getString(R.string.flight_event_date, epochToDate(data.date))
        view.tv_event_info.text = data.info

        view.setOnClickListener {
            if (data.urls["article"] != null) {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = data.urls["article"].toString().toUri()
                view.context.startActivity(intent)
            } else {
                Toast.makeText(view.context, view.context.getString(R.string.toast_no_article), Toast.LENGTH_SHORT).show()
            }
        }
    }
}