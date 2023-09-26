package ru.pvkovalev.swipingnotes.presentation.fragments.main_fragment

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainFragmentViewModelFactory(private val application: Application) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainFragmentViewModel::class.java))
            @Suppress("UNCHECKED_CAST")
            return MainFragmentViewModel(application) as T
       throw IllegalArgumentException("Unknown ViewModel class")
    }
}