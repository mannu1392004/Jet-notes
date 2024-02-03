package com.example.jetnotes.screens

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetnotes.model.Notes
import com.example.jetnotes.repository.noterepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class Notesviewmodel@Inject constructor(val repository :noterepository): ViewModel() {

   private val _noteList = MutableStateFlow<List<Notes>>(emptyList())
   var noteList = _noteList.asStateFlow()
    init {
        viewModelScope.launch(Dispatchers.IO){
            repository.getallnotes().distinctUntilChanged()
                .collect{
                    listofnotes->
                    if (listofnotes.isNullOrEmpty()){

                        _noteList.value = emptyList()
                    }
                    else{
                        _noteList.value = listofnotes
                    }
                }
        }

    }

    fun addnote(Note:Notes)=viewModelScope.launch { repository.addnotes(Note) }
     fun deletenode(Note: Notes) = viewModelScope.launch { repository.deletenote(
        Note
    ) }

}