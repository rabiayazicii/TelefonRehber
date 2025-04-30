package com.example.telefonrehber.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.telefonrehber.ui.data.model.Person
import com.example.telefonrehber.ui.screen.PersonDetailScreen
import com.example.telefonrehber.ui.screen.PersonMainScreen
import com.example.telefonrehber.ui.screen.PersonRegisterScreen
import com.google.gson.Gson

@Composable
fun MainNavigation() {

    val rememberNavController= rememberNavController()
    NavHost(navController = rememberNavController, startDestination = "main"){
        composable("main"){
            PersonMainScreen(navController = rememberNavController)
        }
        composable("register"){
            PersonRegisterScreen(navController = rememberNavController)
        }
        composable("detail/{person}", arguments = listOf(navArgument("person"){type= NavType.StringType})){
            val json=it.arguments?.getString("person")
            val person=Gson().fromJson(json, Person::class.java)
            PersonDetailScreen(person=person,navController = rememberNavController)
        }
    }

}