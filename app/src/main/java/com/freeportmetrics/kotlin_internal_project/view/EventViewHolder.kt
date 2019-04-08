package com.freeportmetrics.kotlin_internal_project.view

import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.freeportmetrics.kotlin_internal_project.R
import com.freeportmetrics.kotlin_internal_project.helper.epochToDate
import com.freeportmetrics.kotlin_internal_project.model.Event
import kotlinx.android.synthetic.main.item_event.view.*

class EventViewHolder(private val view: View) : RecyclerView.ViewHolder(view), GenericAdapter.Binder<Event> {
    override fun bind(data: Event) {
        view.tv_event_name.text = data.name
        view.tv_event_date.text = view.context.getString(R.string.flight_event_date, epochToDate(data.date))
        view.tv_event_info.text = data.info

        view.setOnClickListener {
            if (data.urls?.get("article") != null) {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = data.urls["article"].toString().toUri()
                view.context.startActivity(intent)
            } else {
                Toast.makeText(view.context, view.context.getString(R.string.toast_no_article), Toast.LENGTH_SHORT).show()
            }
        }
    }
}