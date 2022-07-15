package com.example.android.responsivedialogapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : TestAutoSizeDialogFragment.TestListener, AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.root_container, FirstFragment.newInstance())
                .commitAllowingStateLoss()
        }
    }

    override fun onCertainEvent() {
        TODO("Not yet implemented")
    }
}