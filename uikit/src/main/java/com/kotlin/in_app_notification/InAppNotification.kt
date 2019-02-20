package com.kotlin.in_app_notification

import android.content.res.Resources
import android.graphics.PorterDuff
import android.graphics.drawable.StateListDrawable
import android.support.annotation.ColorRes
import android.support.annotation.Dimension
import android.support.annotation.DrawableRes
import android.support.design.widget.Snackbar
import android.support.v4.graphics.ColorUtils
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.uikit.R
import de.hdodenhof.circleimageview.CircleImageView

class InAppNotification private constructor(
        parent: View,
        title: String,
        text: String,
        @ColorRes backgroundColorInt: Int
) {
    var onNotificationClickListener: OnNotificationClickListener? = null
    private val mSnackbar: Snackbar = Snackbar.make(parent, "", Snackbar.LENGTH_SHORT)
    private val custom: View = LayoutInflater.from(parent.context).inflate(R.layout.in_app_notification, null)
    private val resources: Resources = parent.resources

    init {
        (mSnackbar.view as ViewGroup).removeAllViews()
        (mSnackbar.view as ViewGroup).addView(custom)

        val titleTV = custom.findViewById<TextView>(R.id.notificationTitle)
        titleTV.text = title
        val textTV = custom.findViewById<TextView>(R.id.notificationText)
        textTV.text = text

        val close = custom.findViewById<CircleImageView>(R.id.notificationClose)
        close.setOnClickListener { mSnackbar.dismiss() }

        val notification = custom.findViewById<ViewGroup>(R.id.in_app_notification)
        val shapeDrawable = notification.background as StateListDrawable
        val colorBackground = resources.getColor(backgroundColorInt, null)
        shapeDrawable.setColorFilter(colorBackground, PorterDuff.Mode.MULTIPLY)

        if (isColorDark(colorBackground)) {
            titleTV.setTextColor(resources.getColor(R.color.absolute_white, null))
            textTV.setTextColor(resources.getColor(R.color.absolute_white, null))
            close.setColorFilter(resources.getColor(R.color.absolute_white, null))
            close.borderColor = resources.getColor(R.color.absolute_white, null)
        }
        configSnackDesign()

        custom.setOnClickListener(View.OnClickListener {
            onNotificationClickListener?.let {
                it.onNotificationClick()
            }
        })
    }

    private fun isColorDark(color: Int): Boolean {
        return ColorUtils.calculateLuminance(color) < 0.7
    }

    private fun configSnackDesign() {
        val params = mSnackbar.view.layoutParams as ViewGroup.MarginLayoutParams
        params.setMargins(dpToPx(16), dpToPx(0), dpToPx(16), dpToPx(90))

        mSnackbar.view.layoutParams = params
        mSnackbar.view.setBackgroundResource(R.drawable.foreground_notification)
        mSnackbar.view.setPadding(dpToPx(0), dpToPx(0), dpToPx(0), dpToPx(0))
        mSnackbar.view.elevation = 10f
    }

    private fun dpToPx(@Dimension(unit = Dimension.DP) dip: Int): Int {
        val displayMetrics = resources.displayMetrics
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip.toFloat(), displayMetrics).toInt()
    }

    private fun setImage(custom: View, @DrawableRes drawableImageID: Int) {
        val image = custom.findViewById<CircleImageView>(R.id.notificationImage)
        image.setImageResource(drawableImageID)
    }

    private fun setImage(custom: View, imageURL: String) {
        val image = custom.findViewById<CircleImageView>(R.id.notificationImage)
        Glide.with(custom)
                .load(imageURL)
                .into(image)
    }

    fun show() {
        mSnackbar.show()
    }

    fun setIndefinite(isIndefinite: Boolean) {
        mSnackbar.duration = if (isIndefinite) Snackbar.LENGTH_INDEFINITE else Snackbar.LENGTH_SHORT
    }

    companion object {

        fun make(
                parent: View,
                title: String,
                text: String,
                @ColorRes backgroundColorInt: Int,
                @DrawableRes imageResId: Int
        ): InAppNotification {
            val notification = InAppNotification(parent, title, text, backgroundColorInt)
            notification.setImage(notification.custom, imageResId)
            return notification
        }

        fun make(
                parent: View,
                title: String,
                text: String,
                @ColorRes backgroundColorInt: Int,
                imageURL: String
        ): InAppNotification {
            val notification = InAppNotification(parent, title, text, backgroundColorInt)
            notification.setImage(notification.custom, imageURL)
            return notification
        }
    }
}
