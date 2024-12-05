package com.example.myapplication


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@Composable
fun ListView(navController: NavController,mutableList: MutableList<TextObjekt>) {
    Column {

    }
    showView(navController = navController, list = mutableList)
}
@Composable
fun showView(navController: NavController, list: MutableList<TextObjekt>) {

    Column (modifier = Modifier
        .fillMaxSize()
        .background(Color.LightGray)
        .padding(20.dp)
    ) {
        Button(
            onClick = {
                navController.navigate(Screen.EditAndDelete.route)
            })
        {
            Text(text = "Edit")
        }

        LazyColumn(

        ) {
            items(list) { textObject ->
                val selected = remember {
                    mutableStateOf(false)
                }
                Row(
                    Modifier
                        .background(Color.Gray)
                        .clickable { selected.value = true},
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {


                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = textObject.titel)
                        Text(text = textObject.text)
                    }
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {

                    }

                }
                if(selected.value){
                    navController.navigate("DetailScreen/${textObject.id}")
                    selected.value = false
                }

            }
        }

    }


}
