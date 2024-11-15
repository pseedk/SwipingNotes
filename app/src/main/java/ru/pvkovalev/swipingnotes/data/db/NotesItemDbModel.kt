package ru.pvkovalev.swipingnotes.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class NotesItemDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val content: String,
    var notePosition: Int,
    val imageResId: Int,
    val imageBackResId: Int
)
