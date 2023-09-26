package ru.pvkovalev.swipingnotes.domain.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
@Parcelize
@Entity (tableName = "notes_table")
data class NoteModel(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo("note_text") val noteText: String?,
    @ColumnInfo("note_position") var notePosition: Int? = null
) : Parcelable
