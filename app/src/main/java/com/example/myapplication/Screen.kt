package com.example.myapplication

sealed class Screen(val route: String){
    object HomeScreen: Screen(route = "home")
    object NewNote: Screen(route = "NewNote")
    object ListView: Screen(route = "ListView")
    object EditAndDelete: Screen(route = "EditAndDelete")
    object DetailScreen: Screen(route = "DetailScreen/noteid")

}