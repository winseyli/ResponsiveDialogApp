package com.example.android.responsivedialogapp

import android.os.Bundle
import androidx.fragment.app.Fragment

class TestAutoSizeDialogFragment : AutoSizeDialogFragment() {

    interface TestListener {
        fun onCertainEvent()
    }

    companion object {

        fun show(fragment: Fragment, tag: String) {
            val dialogFragment = newInstance()
            dialogFragment.setTargetFragment(fragment, 0)

            dialogFragment.show(fragment.parentFragmentManager, tag)
        }

        private fun newInstance(): TestAutoSizeDialogFragment {
            val dialogFragment = TestAutoSizeDialogFragment()

            val args = Bundle()
            dialogFragment.arguments = args

            return dialogFragment
        }

    }

    private lateinit var listener: TestListener

    override val mergeLayoutRes: Int
        get() = R.layout.merge_auto_size_fragment_test

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val targetFragment = this.targetFragment
        val activity = this.activity
        listener = when {
            targetFragment is TestListener -> targetFragment
            activity is TestListener -> activity
            else -> throw ClassCastException("Activity: $activity, or target fragment: $targetFragment must implement ${TestListener::class.java.name}")
        }
    }

}