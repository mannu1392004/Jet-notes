package com.example.jetnotes.repository

import com.example.jetnotes.model.Notes
import com.example.jetnotes.model.NotesdatabaseDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class noterepository @Inject constructor(private val notesdatabaseDao: NotesdatabaseDao) {
    suspend fun addnotes(notes: Notes) = notesdatabaseDao.insert(notes)
    suspend fun deletenote(notes: Notes) = notesdatabaseDao.deletenotes(notes)
    suspend fun deleteallnotes() = notesdatabaseDao.deleteall()
    suspend fun updatenotes(notes: Notes) = notesdatabaseDao.update(notes)
    suspend fun getallnotes() : Flow<List<Notes>> = notesdatabaseDao.getnotes().flowOn(Dispatchers.IO)
        .conflate()

}