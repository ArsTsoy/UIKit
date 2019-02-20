package com.kotlin.dialog.advanced_dialog

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.annotation.DrawableRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.uikit.R
import com.kotlin.dialog.ChocoDialog
import com.kotlin.dialog.ChocoDialogInterface
import kotlinx.android.synthetic.main.advanced_custom_dialog.*

class ChocoAdvancedDialog : ChocoDialog() {

    companion object {
        private const val TITLE = "TITLE"
        private const val TEXT = "TEXT"
        private const val LEFT_BUTTON_TEXT = "LEFT_BUTTON_TEXT"
        private const val RIGHT_BUTTON_TEXT = "RIGHT_BUTTON_TEXT"
        const val RESOURCE_ID = "RESOURCE_ID"
        const val RESOURCE_URL = "RESOURCE_URL"

        private fun newInstance(builder: Builder): ChocoAdvancedDialog {
            val dialog = ChocoAdvancedDialog()
            val args = Bundle()

            args.putString(TITLE, builder.bTitle)
            args.putString(TEXT, builder.bText)
            args.putString(LEFT_BUTTON_TEXT, builder.bLeftButtonText)
            args.putString(RIGHT_BUTTON_TEXT, builder.bRightButtonText)
            args.putInt(RESOURCE_ID, builder.bResourceId)
            args.putString(RESOURCE_URL, builder.bImageURL)
            dialog.setOnLeftClickListener(builder.bOnLeftClickListener)
            dialog.setOnRightClickListener(builder.bOnRightClickListener)
            dialog.arguments = args
            return dialog
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.advanced_custom_dialog, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = arguments

        val animation = AnimationUtils.loadAnimation(view.context, R.anim.loading_animation)
        animation.repeatCount = Animation.INFINITE
        imageLoading.startAnimation(animation)

        val imageResourceId = args?.getInt(RESOURCE_ID)
        val imageUrl = args?.getString(RESOURCE_URL)

        imageResourceId?.let {
            imageIV?.setImageResource(it)
        }

        imageUrl?.let {
            Glide.with(view)
                    .load(it)
                    .listener(object : RequestListener<Drawable> {
                        override fun onLoadFailed(e: GlideException?, model: Any, target: Target<Drawable>, isFirstResource: Boolean): Boolean {
                            return false
                        }

                        override fun onResourceReady(resource: Drawable, model: Any, target: Target<Drawable>, dataSource: DataSource, isFirstResource: Boolean): Boolean {
                            imageLoading.animation = null
                            imageLoading.visibility = View.INVISIBLE
                            return false
                        }
                    })
                    .into(imageIV!!)
        }

    }

    class Builder {
        var bTitle: String? = null
            private set
        var bText: String? = null
            private set
        var bLeftButtonText: String? = null
            private set
        var bRightButtonText: String? = null
            private set
        var bResourceId: Int = 0
            private set
        var bImageURL: String? = null
            private set
        var bOnLeftClickListener: ChocoDialogInterface.OnClickListener? = null
            private set
        var bOnRightClickListener: ChocoDialogInterface.OnClickListener? = null
            private set

        fun setTitle(title: String): Builder {
            this.bTitle = title
            return this
        }

        fun setText(text: String): Builder {
            this.bText = text
            return this
        }

        fun setDrawableImage(@DrawableRes resourceId: Int): Builder {
            this.bResourceId = resourceId
            return this
        }

        fun setURLImage(imageURL: String): Builder {
            this.bImageURL = imageURL
            return this
        }

        fun setLeftButtonText(lButtonText: String, onClickListener: ChocoDialogInterface.OnClickListener): Builder {
            this.bLeftButtonText = lButtonText
            this.bOnLeftClickListener = onClickListener
            return this
        }

        fun setRightButtonText(rButtonText: String, onClickListener: ChocoDialogInterface.OnClickListener): Builder {
            this.bRightButtonText = rButtonText
            this.bOnRightClickListener = onClickListener
            return this
        }

        fun create(): ChocoAdvancedDialog {
            return newInstance(this)
        }
    }
}
