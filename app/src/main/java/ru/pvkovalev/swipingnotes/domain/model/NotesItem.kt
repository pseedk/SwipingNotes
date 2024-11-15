package ru.pvkovalev.swipingnotes.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NotesItem(
    val id: Int = UNDEFINED_ID,
    val content: String,
    var notePosition: Int = UNDEFINED_ID,
    val imageResId: Int,
    val imageBackResId: Int
) : Parcelable {
    companion object {
        const val UNDEFINED_ID = 0
    }
}
