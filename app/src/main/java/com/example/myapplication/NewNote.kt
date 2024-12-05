package com.example.myapplication


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


//class TextObjekt(val titel: String, val text: String)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(navController: NavController,list: MutableList<TextObjekt>) {
    Column(modifier = Modifier.background(Color.Blue)) {
        booken(list = list, navController = navController)
    }
}
//@OptIn(ExperimentalMaterial3Api::class)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun booken(list: MutableList<TextObjekt> ,navController: NavController)
{
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.LightGray)) {
        Button(onClick = { navController.navigateUp() }) {
            //Text(text =" Detail: $name")
            Text(text = "Back")
        }
        var titel by rememberSaveable {
            mutableStateOf("") }
        var text by rememberSaveable {
            mutableStateOf("")
        }
        val isTitleError = !ValidationUtils.isInputValid(titel)
        val isTextError = !ValidationUtils.isInputValid((text))

        Column (modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center)
        {


            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                TextField(
                    value = titel,
                    onValueChange = { newTitel ->
                        titel = newTitel
                    },
                    label = { Text(text = "titel") },
                    isError = isTitleError
                )

                if (isTitleError) {
                    Text(text = "Write more for titel")
                }

                TextField(
                    value = text,
                    onValueChange = { newText ->
                        text = newText
                    },
                    label = { Text(text = "text") },
                    isError = isTextError
                )

                if (isTextError) {
                    Text(text = "Write more for text")
                }

                Button(enabled = !isTitleError && !isTextError,
                    onClick = {
                        list.add(TextObjekt(titel = titel, text = text))
                        println(list)
                        titel = ""
                        text = ""

                    }
                ) {
                    Text("Spara")
                }

            }

            Button(onClick = { navController.navigate(Screen.ListView.route) }) {
                Text(text = "Show List")

            }
        }

    }


}







