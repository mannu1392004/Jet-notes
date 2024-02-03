package com.example.jetnotes.components

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.em

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun noteText(modifier: Modifier=Modifier,valuestate:MutableState<String>,
             label:String,maxline:Int=1,
    onImeAction: () -> Unit  = {}   ,onvaluechange:(String) -> Unit ){

val keyboardcontroller  = LocalSoftwareKeyboardController.current

    TextField(value = valuestate.value, onValueChange ={onvaluechange.invoke(it)}
    , modifier = modifier,
       maxLines = maxline, label = { Text(text = label , fontSize = 4.em)},
        colors = TextFieldDefaults.textFieldColors(containerColor = Color.Transparent)
        , keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Next
        )
        , keyboardActions = KeyboardActions(onDone = {onImeAction()
        keyboardcontroller?.hide()})
    )
}

@Composable
fun notebutton(
    modifier: Modifier,
    text:String,
    onclick : ()-> Unit,
enabled:Boolean=true
){
  Button(onClick = {onclick.invoke() }, modifier = modifier
  , shape = RectangleShape,
      colors = ButtonDefaults.buttonColors(Color.DarkGray)) {
      Text(text = text)
  }
}

