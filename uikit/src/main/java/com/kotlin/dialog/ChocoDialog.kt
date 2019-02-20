package com.kotlin.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.util.DisplayMetrics
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import com.example.uikit.R

abstract class ChocoDialog : DialogFragment() {
    private var bigTitleTV: TextView? = null
    private var regularTextTV: TextView? = null
    private var leftButton: Button? = null
    private var rightButton: Button? = null

    private var onRightClickListener: ChocoDialogInterface.OnClickListener? = null
    private var onLeftClickListener: ChocoDialogInterface.OnClickListener? = null

    companion object {
        const val TITLE = "TITLE"
        const val TEXT = "TEXT"
        const val LEFT_BUTTON_TEXT = "LEFT_BUTTON_TEXT"
        const val RIGHT_BUTTON_TEXT = "RIGHT_BUTTON_TEXT"
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window?.requestFeature(Window.FEATURE_NO_TITLE)
//        dialog.window?.attributes?.width = (getDeviceMetrics(dialog.context).widthPixels * 0.5).toInt()
        dialog.window?.setBackgroundDrawableResource(R.drawable.dialog_container)
        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = arguments
        val title = args?.getString(TITLE)
        val text = args?.getString(TEXT)
        val leftButtonText = args?.getString(LEFT_BUTTON_TEXT)
        val rightButtonText = args?.getString(RIGHT_BUTTON_TEXT)

        bigTitleTV = view.findViewById(R.id.bigTitleTV)
        regularTextTV = view.findViewById(R.id.regularTextTV)
        leftButton = view.findViewById(R.id.leftButton)
        rightButton = view.findViewById(R.id.rightButton)

        bigTitleTV?.text = title
        regularTextTV?.text = text
        leftButton?.text = leftButtonText
        rightButton?.text = rightButtonText

        leftButton?.setOnClickListener { onLeftClickListener?.onClick() }
        rightButton?.setOnClickListener { onRightClickListener?.onClick() }
    }

    private fun getDeviceMetrics(context: Context): DisplayMetrics {
        val metrics = DisplayMetrics()
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = wm.defaultDisplay
        display.getMetrics(metrics)
        return metrics
    }

    fun setOnRightClickListener(onRightClickListener: ChocoDialogInterface.OnClickListener?) {
        this.onRightClickListener = onRightClickListener
    }

    fun setOnLeftClickListener(onLeftClickListener: ChocoDialogInterface.OnClickListener?) {
        this.onLeftClickListener = onLeftClickListener
    }
}
