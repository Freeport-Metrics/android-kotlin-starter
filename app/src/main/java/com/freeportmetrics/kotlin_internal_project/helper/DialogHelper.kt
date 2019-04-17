package com.freeportmetrics.kotlin_internal_project.helper

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.core.content.edit
import com.freeportmetrics.kotlin_internal_project.R
import kotlinx.android.synthetic.main.dialog_message.view.*

private const val BUTTON_DEFAULT_ID = 0
private const val BUTTON_POSITIVE_ID = -1
private const val PREF_DIALOG = "PREF_DIALOG"
private const val PREF_KEY_DIALOG_BUTTON = "PREF_KEY_DIALOG_BUTTON"
private const val PREF_KEY_DIALOG_DONT_ASK_AGAIN = "PREF_KEY_DIALOG_DONT_ASK_AGAIN"

class DialogHelper(private val context: Context) {
    private val sharedPrefs = context.getSharedPreferences(PREF_DIALOG, Context.MODE_PRIVATE)

    fun handleCheckBox(intent: Intent) {
        if (sharedPrefs.getBoolean(PREF_KEY_DIALOG_DONT_ASK_AGAIN, false)) {
            if (sharedPrefs.getInt(PREF_KEY_DIALOG_BUTTON, BUTTON_DEFAULT_ID) == BUTTON_POSITIVE_ID) {
                context.startActivity(intent)
            }
        } else {
            showDialog { context.startActivity(intent) }
        }
    }

    private fun showDialog(moveToWebsite: () -> Unit) {
        val builder =
            AlertDialog.Builder(context)  // possible change to MaterialAlertDialogBuilder when it comes out of alpha
        val customDialog = (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(
            R.layout.dialog_message,
            null
        )

        with(builder) {
            setTitle(context.resources.getString(R.string.dialog_title))
            setMessage(context.resources.getString(R.string.dialog_message))
            setView(customDialog)
            setPositiveButton(context.resources.getString(R.string.dialog_btn_positive)) { _, which ->
                context.getSharedPreferences(PREF_DIALOG, Context.MODE_PRIVATE).edit {
                    putInt(PREF_KEY_DIALOG_BUTTON, which)
                }
                moveToWebsite()
            }
            setNegativeButton(context.resources.getString(R.string.dialog_btn_negative)) { dialog, which ->
                context.getSharedPreferences(PREF_DIALOG, Context.MODE_PRIVATE).edit {
                    putInt(PREF_KEY_DIALOG_BUTTON, which)
                }
                dialog.dismiss()
            }
            customDialog.checkBox.setOnClickListener {
                context.getSharedPreferences(PREF_DIALOG, Context.MODE_PRIVATE).edit {
                    putBoolean(PREF_KEY_DIALOG_DONT_ASK_AGAIN, customDialog.checkBox.isChecked)
                }
            }
            show()
        }
    }
}