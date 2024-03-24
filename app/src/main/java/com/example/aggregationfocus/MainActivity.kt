package com.example.aggregationfocus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.aggregationfocus.ui.theme.AggregationFocusTheme
import com.example.aggregationfocus.navigation.Screen
import com.example.aggregationfocus.navigation.SetupNavGraph
import com.example.aggregationfocus.util.Constants
import io.realm.kotlin.mongodb.App

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AggregationFocusTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    SetupNavGraph(
                        startDestination = getStartDestination(),
                        navController = navController
                    )
                }
            }
        }
    }
}

private fun getStartDestination(): String {
    val user = App.create(Constants.APP_ID).currentUser
    return if (user != null && user.loggedIn) Screen.Home.route
    else Screen.Authentication.route
}