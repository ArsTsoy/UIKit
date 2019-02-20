package com.kotlin.dialog.simple_dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.uikit.R
import com.kotlin.dialog.ChocoDialog
import com.kotlin.dialog.ChocoDialogInterface

class ChocoSimpleDialog : ChocoDialog() {


    companion object {
        const val TITLE = "TITLE"
        const val TEXT = "TEXT"
        const val LEFT_BUTTON_TEXT = "LEFT_BUTTON_TEXT"
        const val RIGHT_BUTTON_TEXT = "RIGHT_BUTTON_TEXT"

        private fun newInstance(builder: Builder): ChocoSimpleDialog {
            val dialog = ChocoSimpleDialog()
            val args = Bundle()
            args.putString(TITLE, builder.bTitle)
            args.putString(TEXT, builder.bText)
            args.putString(LEFT_BUTTON_TEXT, builder.bLeftButtonText)
            args.putString(RIGHT_BUTTON_TEXT, builder.bRightButtonText)
            dialog.setOnLeftClickListener(builder.bOnLeftClickListener)
            dialog.setOnRightClickListener(builder.bOnRightClickListener)
            dialog.arguments = args
            return dialog
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.simple_custom_dialog, container)
    }

    class Builder {
        var bTitle: String? = null
        var bText: String? = null
        var bLeftButtonText: String? = null
        var bRightButtonText: String? = null
        var bOnLeftClickListener: ChocoDialogInterface.OnClickListener? = null
        var bOnRightClickListener: ChocoDialogInterface.OnClickListener? = null

        fun setTitle(title: String): Builder {
            this.bTitle = title
            return this
        }

        fun setText(text: String): Builder {
            this.bText = text
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

        fun create(): ChocoSimpleDialog {
            return newInstance(this)
        }
    }
}
