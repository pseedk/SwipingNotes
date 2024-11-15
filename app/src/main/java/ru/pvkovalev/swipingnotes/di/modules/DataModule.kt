package ru.pvkovalev.swipingnotes.di.modules

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.pvkovalev.swipingnotes.data.db.AppDatabase
import ru.pvkovalev.swipingnotes.data.db.NotesDao
import ru.pvkovalev.swipingnotes.data.db.NotesMapper
import ru.pvkovalev.swipingnotes.data.repository.NotesRepositoryImpl
import ru.pvkovalev.swipingnotes.domain.repository.NotesRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun providesRepository(notesDao: NotesDao, mapper: NotesMapper): NotesRepository =
        NotesRepositoryImpl(notesDao, mapper)

    @Singleton
    @Provides
    fun providesNotesDao(
        application: Application
    ): NotesDao {
        return AppDatabase.getInstance(application).notesDao()
    }
}