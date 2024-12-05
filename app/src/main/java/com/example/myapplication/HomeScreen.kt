package com.example.myapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {
    var text by rememberSaveable {
        mutableStateOf("")
    }
    Column (modifier = Modifier.fillMaxSize().background(Color.LightGray),verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){
        Button(onClick = {
            navController.navigate(Screen.NewNote.route)
        }) {
            Text(text = " + Add a new note")

        }
    }


}