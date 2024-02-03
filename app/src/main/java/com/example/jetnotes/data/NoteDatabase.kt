package com.example.jetnotes.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.jetnotes.model.Notes
import com.example.jetnotes.model.NotesdatabaseDao

@Database(entities = [Notes::class], version = 1, exportSchema = false)
abstract class NoteDatabase :RoomDatabase() {
    abstract fun notedao():NotesdatabaseDao
}