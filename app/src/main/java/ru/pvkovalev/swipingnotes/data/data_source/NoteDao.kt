package ru.pvkovalev.swipingnotes.data.data_source

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import ru.pvkovalev.swipingnotes.domain.model.NoteModel

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(noteModel: NoteModel)

    @Delete
    suspend fun deleteNote(noteModel: NoteModel)

    @Update
    suspend fun updateNote(noteModel: NoteModel)

    @Query("SELECT * FROM notes_table")
    fun getAllNotes(): LiveData<List<NoteModel>>
}