package ru.pvkovalev.swipingnotes.presentation

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import dagger.hilt.android.AndroidEntryPoint
import ru.pvkovalev.swipingnotes.R
import ru.pvkovalev.swipingnotes.utils.resizeSoftInputMode

@AndroidEntryPoint
class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        resizeSoftInputMode(this)
    }
}