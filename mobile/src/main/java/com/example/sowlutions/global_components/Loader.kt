package com.example.bayztrackerz.global_components

import android.app.Dialog
import android.content.Context
import android.view.Window
import android.view.WindowManager
import android.widget.ProgressBar

object Loader {
    private var dialog: Dialog? = null

    fun show(context: Context) {
        // Only create a new dialog if there isn't one already shown
        if (dialog == null) {
            dialog = Dialog(context)
            dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog?.setContentView(ProgressBar(context))
            dialog?.setCancelable(false)

            val lp = WindowManager.LayoutParams()
            lp.copyFrom(dialog?.window?.attributes)
            lp.width = WindowManager.LayoutParams.WRAP_CONTENT
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT

            dialog?.window?.attributes = lp
            dialog?.show()
        }
    }

    fun hide() {
        dialog?.dismiss()
        dialog = null
    }
}