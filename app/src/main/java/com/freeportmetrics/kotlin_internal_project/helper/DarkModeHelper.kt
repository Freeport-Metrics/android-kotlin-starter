package com.freeportmetrics.kotlin_internal_project.helper

import android.content.Context
import android.view.Menu
import android.widget.Button
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import com.freeportmetrics.kotlin_internal_project.R

internal class DarkModeHelper(private val context: Context) {
    internal fun onModeChanged(newMode: Boolean, delegate: AppCompatDelegate) {
        if (newMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
        delegate.applyDayNight()
    }

    internal fun setButtonColors(darkMode: Boolean, active: Button, vararg notActiveOnes: Button) {
        active.backgroundTintList = context.getColorStateList(R.color.colorAccent)
        for (notActive in notActiveOnes) {
            if (darkMode) {
                notActive.backgroundTintList = context.getColorStateList(R.color.button_material_dark)
            } else {
                notActive.backgroundTintList = context.getColorStateList(R.color.button_material_light)
            }
        }
    }
}