package com.example.myapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController

@Composable
fun detailScreen(navController: NavController, noteid : String,list: MutableList<TextObjekt>) {
    val note = list.firstOrNull{it.id == noteid}
    Column (modifier = Modifier.fillMaxSize().background(Color.LightGray),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        if (note != null) {
            Column(
            ) {
                Text(
                    text = "Titel:     " + note.titel
                )
                Text(text = "Text:     "  + note.text)
            }

        }

    }


}