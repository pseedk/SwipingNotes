package ru.pvkovalev.swipingnotes.data.db

import ru.pvkovalev.swipingnotes.domain.model.NotesItem
import javax.inject.Inject

class NotesMapper @Inject constructor() {

    fun mapEntityToDbModel(notesItem: NotesItem) =
        NotesItemDbModel(
            id = notesItem.id,
            content = notesItem.content,
            notePosition = notesItem.notePosition,
            imageResId = notesItem.imageResId,
            imageBackResId = notesItem.imageBackResId
        )

    fun mapDbModelToEntity(notesItemDbModel: NotesItemDbModel) =
        NotesItem(
            id = notesItemDbModel.id,
            content = notesItemDbModel.content,
            notePosition = notesItemDbModel.notePosition,
            imageResId = notesItemDbModel.imageResId,
            imageBackResId = notesItemDbModel.imageBackResId
        )

    fun mapListDbModelToListEntity(list: List<NotesItemDbModel>) =
        list.map {
            mapDbModelToEntity(it)
        }

}