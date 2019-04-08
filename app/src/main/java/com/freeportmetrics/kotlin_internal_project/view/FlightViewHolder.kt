package com.freeportmetrics.kotlin_internal_project.view

import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.freeportmetrics.kotlin_internal_project.R
import com.freeportmetrics.kotlin_internal_project.helper.epochToDate
import com.freeportmetrics.kotlin_internal_project.model.Flight
import kotlinx.android.synthetic.main.item_flight.view.*

class FlightViewHolder(private val view: View) : RecyclerView.ViewHolder(view), GenericAdapter.Binder<Flight> {
    override fun bind(data: Flight) {
        view.tv_flight_name.text = view.context.getString(R.string.flight_name, data.name)
        view.tv_flight_date.text = view.context.getString(R.string.flight_event_date, epochToDate(data.launchDate))

        view.setOnClickListener {
            if (data.urls?.reddit_campaign != null) {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = data.urls.reddit_campaign.toUri()
                view.context.startActivity(intent)
            } else {
                Toast.makeText(view.context, view.context.getString(R.string.toast_no_campaign), Toast.LENGTH_SHORT).show()
            }
        }
    }
}