package com.example.jetnotes.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import com.example.jetnotes.R
import com.example.jetnotes.components.noteText
import com.example.jetnotes.components.notebutton
import com.example.jetnotes.model.Notes


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun notescreen(
    notes: List<Notes>
    , onaddnottes:(Notes)  -> Unit
    , onremovenote:(Notes) -> Unit
){
var title = remember {
    mutableStateOf("")
}
var notestext = remember {
    mutableStateOf("")
}
    val context = LocalContext.current
    Column(modifier = Modifier.padding(6.dp)) {
// top bar
        TopAppBar(title = { Text(text = stringResource(id = R.string.app_name)) },
            colors = topAppBarColors(
                containerColor = Color(0xFFB125EA)
            ),
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(20.dp)),
            actions = {
                Icon(
                    imageVector = Icons.Rounded.Notifications,
                    contentDescription = "icon"
                )
            })


//content
        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            noteText(valuestate = title, label = "Title",
                modifier = Modifier
                    .background(color = Color.White),
                onvaluechange = {
                    if (title.value.all{ char->
                        char.isLetter()||char.isWhitespace()
                        }
                    ) title.value = it
else title.value = title.value
                }
            )
Spacer(modifier = Modifier.height(30.dp))
            noteText(valuestate = notestext, label = "Add a note",
                modifier = Modifier
                    .background(color = Color.White)
            , maxline = 30
            , onvaluechange = {
                notestext.value = it
                })

            Spacer(modifier = Modifier.height(30.dp))

//button
            notebutton(modifier = Modifier
                , text = "Save", onclick = {
                    if (title.value!=""&&notestext.value!=""){
onaddnottes(Notes(title = title.value, description = notestext.value,))// localtime = LocalDateTime.now()))
                    Toast.makeText(context,"Notes added successfully",Toast.LENGTH_SHORT).show()


                            title.value = ""
                            notestext.value =""



                    }
                    else{
                        Toast.makeText(context,"Add title or description",Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            )

       LazyColumn(modifier = Modifier.weight(1f)){
           items(notes){
               Surface(modifier = Modifier
                   .padding(10.dp)
                   .fillMaxWidth()
                   .clip(RoundedCornerShape(topEnd = 20.dp, bottomStart = 20.dp)), color = Color.LightGray) {
                   Column(modifier = Modifier
                       .padding(10.dp)
                       .clickable {
                           onremovenote(it)
                       }) {
       //val date =it.localtime.dayOfMonth
       //val month = it.localtime.month.toString()

                       Text(text = it.title, fontSize = 5.em, maxLines = 2, )
                       Text(text = it.description)
                       //  Text(text = "$date"+" "+month+" "+it.localtime.year+ " ("+it.localtime.hour+":"+it.localtime.minute+")", fontSize = 2.em)
                   }
               }
           }
       }



        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun prev(){
    notescreen(notes = emptyList(), onaddnottes = {}){}
}