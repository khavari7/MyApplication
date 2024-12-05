package com.example.myapplication


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.TextObjekt
import com.example.myapplication.ValidationUtils
@Composable
fun editList(navController: NavController, mutableList: MutableList<TextObjekt>) {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.LightGray)
        .padding(20.dp)) {
        Button(onClick = { navController.navigateUp() }) {
            //Text(text =" Detail: $name")
            Text(text = "Back")
        }
        var selectedTextObjekt by remember {
            mutableStateOf<TextObjekt?>(null) }
        var editTitel by remember {
            mutableStateOf("")
        }
        var editText by remember {
            mutableStateOf("")
        }
        if(selectedTextObjekt != null){
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val isTitleError =   !ValidationUtils.isInputValid(editTitel)
                val isTextError =  !ValidationUtils.isInputValid(editText);


                TextField(
                    value = editTitel, onValueChange = { editTitel = it },
                    label = { Text(text = "Titel")},
                    isError = isTitleError
                )
                if (isTitleError) {
                    Text(text = "Write more for titel")
                }
                TextField(
                    value = editText, onValueChange = { editText = it },
                    label = { Text(text = "Text")},
                    isError = isTextError
                )
                if (isTextError) {
                    Text(text = "Write more for Text")
                }
                Button(enabled = !isTitleError && !isTextError, onClick = {

                    selectedTextObjekt?.let { textObjekt ->
                        textObjekt.titel = editTitel
                        textObjekt.text = editText
                    }

                    editTitel = ""
                    editText = ""
                    selectedTextObjekt = null
                }) {Text(text = "Save")

                }
                Button(onClick = {
                    editText = ""
                    editTitel = ""
                    selectedTextObjekt = null
                }) {
                    Text(text = "Cancel")
                }
            }
        }
        else {
            LazyColumn{
                items(mutableList){
                        textObjekt ->
                    Row (
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(8.dp)
                    ){
                        Column {
                            Text(text = textObjekt.titel)
                            Text(text = textObjekt.text)
                        }
                        Button(onClick = {
                            selectedTextObjekt = textObjekt
                            editTitel = textObjekt.titel
                            editText = textObjekt.text
                        }) {
                            Text(text = "Edit")

                        }
                        Button(onClick = { mutableList.remove(textObjekt) }) {
                            Text(text = "Delete")
                        }

                    }
                }
            }
        }
    }

}