package com.example.myapplication


import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons

import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapplication.ui.theme.MyApplicationTheme
import java.util.UUID

data class TextObjekt(
    val id: String = UUID.randomUUID().toString(),
    var titel: String,
    var text: String
)
class ValidationUtils {
    companion object {
        fun isInputValid(input: String): Boolean {
            return input.isNotEmpty() && (input.length in 3..50)
        }
    }
}
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val list = remember {
                mutableStateListOf<TextObjekt>()

            }

            val navController = rememberNavController()
            var showBackButton by rememberSaveable {
                mutableStateOf(false)
            }
            var topBarTitel by rememberSaveable {
                mutableStateOf("Home")
            }
            var editButton by rememberSaveable {
                mutableStateOf(false)
            }

            MyApplicationTheme(){
                Scaffold(
                    topBar = {
                        TopAppBar(
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                titleContentColor = MaterialTheme.colorScheme.primary,
                            ),

                            title = {
                                Text(text = topBarTitel)
                            }, navigationIcon = {
                                Row (){
                                    if (showBackButton) {
                                        IconButton(onClick = { navController.navigateUp() }) {
                                            Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                                        }
                                    }
                                    else {
                                        null
                                    }
                                    if(editButton){
                                        IconButton(onClick = { navController.navigate(Screen.EditAndDelete.route)}
                                        ) {
                                            Icon(Icons.Default.Create, contentDescription = "create")
                                        }

                                    }
                                    else{
                                        null
                                    }
                                }

                            }
                        )
                    }
                ) {  innerPadding ->
                    Box(modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize())


                    NavHost(navController = navController, startDestination = Screen.HomeScreen.route){
                        composable(route = Screen.HomeScreen.route){
                            showBackButton = false
                            editButton = false
                            topBarTitel = "Home"
                            HomeScreen(navController = navController)
                        }
                        composable(route = Screen.NewNote.route ){
                            showBackButton = true
                            editButton = false
                            topBarTitel = "New Note"
                            DetailScreen(navController = navController,list)
                        }
                        composable(route = Screen.ListView.route){
                            showBackButton = true
                            editButton = true
                            topBarTitel = "My Note"
                            showView(navController = navController, list)
                        }
                        composable(route = "DetailScreen/{noteid}", arguments = listOf(
                            navArgument("noteid"){type = NavType.StringType}
                        )){ backStackEntry ->
                            val noteid = backStackEntry.arguments?.getString("noteid") ?: ""
                            showBackButton = true
                            editButton = false
                            topBarTitel = "Detail Screen"
                            detailScreen(navController = navController,noteid, list)
                        }
                        composable(route = Screen.EditAndDelete.route){
                            showBackButton = true
                            editButton = false
                            topBarTitel = "EDIT"
                            editList(navController = navController, list)
                        }


                    }
                }

            }
        }
    }
}
