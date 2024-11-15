package ru.pvkovalev.swipingnotes.presentation.fragments.add

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.pvkovalev.swipingnotes.R
import ru.pvkovalev.swipingnotes.domain.model.NotesItem
import ru.pvkovalev.swipingnotes.domain.usecases.AddNoteUseCase
import ru.pvkovalev.swipingnotes.domain.usecases.DeleteNoteUseCase
import ru.pvkovalev.swipingnotes.domain.usecases.GetMinNotesOrderUseCase
import ru.pvkovalev.swipingnotes.domain.usecases.GetNotesUseCase
import javax.inject.Inject

@HiltViewModel
class AddFragmentViewModel @Inject constructor(
    private val addNoteUseCase: AddNoteUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
    private val getMinNotesOrderUseCase: GetMinNotesOrderUseCase,
    private val getNotesUseCase: GetNotesUseCase
) : ViewModel() {

    private val imageResources = mapOf(
        R.drawable.im_sticker1 to R.drawable.im_back_sticker1,
        R.drawable.im_sticker2 to R.drawable.im_back_sticker2,
        R.drawable.im_sticker3 to R.drawable.im_back_sticker3,
        R.drawable.im_sticker4 to R.drawable.im_back_sticker4,
        R.drawable.im_sticker5 to R.drawable.im_back_sticker5,
        R.drawable.im_sticker6 to R.drawable.im_back_sticker6,
        R.drawable.im_sticker7 to R.drawable.im_back_sticker7,
        R.drawable.im_sticker8 to R.drawable.im_back_sticker8,
        R.drawable.im_sticker9 to R.drawable.im_back_sticker9,
        R.drawable.im_sticker10 to R.drawable.im_back_sticker10,
        R.drawable.im_sticker11 to R.drawable.im_back_sticker11,
        R.drawable.im_sticker12 to R.drawable.im_back_sticker12,
        R.drawable.im_sticker13 to R.drawable.im_back_sticker13,
        R.drawable.im_sticker15 to R.drawable.im_back_sticker15,
        R.drawable.im_sticker16 to R.drawable.im_back_sticker16,
    ).entries.random()

    private val _backgroundImage = MutableLiveData<Int>()
    val backgroundImage = _backgroundImage

    init {
        backgroundImage.value = imageResources.value
    }

    fun addNote(noteContent: String) {
        viewModelScope.launch {
            val order = getMinNotesOrderUseCase.invoke()
            val note = NotesItem(
                content = noteContent,
                imageResId = imageResources.key,
                imageBackResId = imageResources.value,
                notePosition = order?.minus(1) ?: 0
            )
            addNoteUseCase.invoke(note)
            getNotesUseCase.invoke()
        }
    }

    fun deleteNote(notesItem: NotesItem) {
        viewModelScope.launch {
            deleteNoteUseCase.invoke(notesItem)
        }
    }
}