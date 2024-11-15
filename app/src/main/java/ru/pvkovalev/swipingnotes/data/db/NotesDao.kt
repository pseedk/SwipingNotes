package ru.pvkovalev.swipingnotes.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {

    @Query("SELECT * FROM notes ORDER BY notePosition")
    fun getNotes(): Flow<List<NotesItemDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNote(notesItemDbModel: NotesItemDbModel)

    @Delete
    suspend fun deleteNote(notesItemDbModel: NotesItemDbModel)

    @Update
    suspend fun editNote(notesItemDbModel: NotesItemDbModel)

    @Update
    suspend fun updateNotes(notesItemDbModel: NotesItemDbModel)

    @Query("SELECT MIN(notePosition) FROM notes")
    suspend fun getMinNoteOrder(): Int?
}