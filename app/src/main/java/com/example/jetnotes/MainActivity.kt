package com.example.jetnotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jetnotes.screens.Notesviewmodel

import com.example.jetnotes.screens.notescreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

notesapp()


        }
    }
}

@Composable
fun notesapp(noviewModel: Notesviewmodel= viewModel<Notesviewmodel>()){
    val notelist = noviewModel.noteList.collectAsState().value
    notescreen(notelist , onaddnottes = {
noviewModel.addnote(it)
    }){
noviewModel.deletenode(it)
    }

}