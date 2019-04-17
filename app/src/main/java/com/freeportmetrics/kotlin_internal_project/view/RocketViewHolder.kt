package com.freeportmetrics.kotlin_internal_project.view

import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.freeportmetrics.kotlin_internal_project.R
import com.freeportmetrics.kotlin_internal_project.helper.*
import com.freeportmetrics.kotlin_internal_project.model.Rocket
import kotlinx.android.synthetic.main.item_rocket.view.*

class RocketViewHolder(private val view: View, private val dialogHelper: DialogHelper) : RecyclerView.ViewHolder(view), GenericAdapter.Binder<Rocket> {

    override fun bind(data: Rocket) {
        view.tv_rocket_name.text = data.name
        view.tv_rocket_cost_per_launch.text = view.context.getString(R.string.rocket_name, data.cost / 1_000_000)
        view.tv_rocket_first_flight.text = view.context.getString(R.string.rocket_first_flight, data.firstFlight)
        view.tv_rocket_height.text = view.context.getString(R.string.rocket_height, data.height["meters"])
        view.tv_rocket_weight.text = view.context.getString(R.string.rocket_weight, data.weight["kg"]?.div(1_000))
        Glide.with(view.context).load(data.image[0]).into(view.iv_rocket)

        view.setOnClickListener {
            if (data.url != null) {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = data.url.toUri()
                dialogHelper.handleCheckBox(intent)
            } else {
                Toast.makeText(view.context, view.context.getString(R.string.toast_no_link), Toast.LENGTH_SHORT).show()
            }
        }
    }
}