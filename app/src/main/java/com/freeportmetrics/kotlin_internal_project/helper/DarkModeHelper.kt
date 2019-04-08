package com.freeportmetrics.kotlin_internal_project.helper

import android.content.Context
import android.view.Menu
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

    internal fun handleIconChange(menu: Menu?, darkMode: Boolean) {
        val item = menu?.findItem(R.id.mnu_set_theme)

        if (darkMode) {
            item?.icon = getDrawable(context, R.drawable.ic_btn_light_mode)
        } else {
            item?.icon = getDrawable(context, R.drawable.ic_btn_dark_mode)
        }
    }
}