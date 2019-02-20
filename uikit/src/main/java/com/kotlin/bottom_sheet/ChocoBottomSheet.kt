package com.kotlin.bottom_sheet

import android.app.Dialog
import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.uikit.R
import kotlinx.android.synthetic.main.bottom_sheet_dialog.*


class ChocoBottomSheet : BottomSheetDialogFragment() {

    companion object {
        const val TEXT = "TEXT"
        const val TITLE = "TITLE"

        fun newInstance(title: String, text: String): ChocoBottomSheet {

            val args = Bundle()
            args.putString(TITLE, title)
            args.putString(TEXT, text)

            val fragment = ChocoBottomSheet()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)

        dialog.setOnShowListener {
            val bottomSheetView = dialog.findViewById<View>(android.support.design.R.id.design_bottom_sheet)
            BottomSheetBehavior.from(bottomSheetView!!).state = BottomSheetBehavior.STATE_EXPANDED
            BottomSheetBehavior.from(bottomSheetView).skipCollapsed = true
        }
        return dialog
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.bottom_sheet_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bottom_sheet_title.text = arguments?.getString(TITLE)
        bottom_sheet_text.text = arguments?.getString(TEXT)
    }
}
