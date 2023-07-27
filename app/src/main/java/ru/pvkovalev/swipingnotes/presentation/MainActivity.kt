package ru.pvkovalev.swipingnotes.presentation

import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.core.app.PendingIntentCompat.getActivity
import androidx.fragment.app.FragmentActivity
import ru.pvkovalev.swipingnotes.R
import ru.pvkovalev.swipingnotes.utils.APP_ACTIVITY
import ru.pvkovalev.swipingnotes.utils.hideStatusBar
import ru.pvkovalev.swipingnotes.utils.resizeSoftInputMode


class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        APP_ACTIVITY = this
             resizeSoftInputMode(APP_ACTIVITY)
    }
}