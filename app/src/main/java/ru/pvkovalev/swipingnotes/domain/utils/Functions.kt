package ru.pvkovalev.swipingnotes.domain.utils

import android.app.Activity
import android.content.Context
import android.os.Build
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.activity.OnBackPressedCallback

fun hideStatusBar(activity: Activity) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        activity.window.decorView.windowInsetsController!!.hide(
            android.view.WindowInsets.Type.statusBars()
        )
    } else {
        activity.window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}

fun showKeyboard(context: Context?, view: View) {
    val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(view, 0)
}

fun hideKeyboard(context: Context?, view: View) {
    val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

fun resizeSoftInputMode(activity: Activity?) {
    activity?.window?.setSoftInputMode(WindowManager.LayoutParams. SOFT_INPUT_ADJUST_RESIZE)
}

