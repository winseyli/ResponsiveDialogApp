package com.example.android.responsivedialogapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.DialogFragment

abstract class AutoSizeDialogFragment : DialogFragment() {

    protected abstract val mergeLayoutRes: Int

    private val isLargeScreen
        get() = resources.getBoolean(R.bool.device_wide_tall)

    init {
        this.setStyle(DialogFragment.STYLE_NO_TITLE, 0)
    }

    override fun getTheme() = if (isLargeScreen) androidx.appcompat.R.style.ThemeOverlay_AppCompat_Dialog
                                else androidx.appcompat.R.style.ThemeOverlay_AppCompat_ActionBar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Set to adjust screen height automatically, when soft keyboard appears on screen.
        if (dialog != null) {
            dialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        }

        val view = inflater?.inflate(R.layout.dialog_fragment_auto_size, container, false) ?: return null

        view.findViewById<Toolbar?>(R.id.toolbar).apply {
            setNavigationOnClickListener {
                parentFragmentManager.beginTransaction().remove(this@AutoSizeDialogFragment).commit()
            }
        }

        val contentViewGroup: ViewGroup? = view.findViewById(R.id.contentViewGroup)
        inflater.inflate(mergeLayoutRes, contentViewGroup, true)

        return view
    }

}
