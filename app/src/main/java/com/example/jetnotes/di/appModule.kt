package com.example.jetnotes.di

import android.content.Context
import androidx.room.Room
import com.example.jetnotes.data.NoteDatabase
import com.example.jetnotes.model.NotesdatabaseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object appModule {
@Singleton
@Provides
fun providenotesdao(notesdatabas:NoteDatabase):NotesdatabaseDao=notesdatabas.notedao()


    @Singleton
    @Provides
    fun providesappdatabase(@ApplicationContext context: Context):NoteDatabase
    =Room.databaseBuilder(
        context,
        NoteDatabase::class.java,
        "notes_db"
    ).fallbackToDestructiveMigration()
        .build()



}