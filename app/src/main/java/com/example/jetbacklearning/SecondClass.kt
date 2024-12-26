package com.example.jetbacklearning

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun SecondClass(navController: NavHostController, username: String?=null, password: String?=null) {
        Column {
            Text("Home Screen" + "$username")
            Text("password " + "$password")

            Button(onClick = { navController.navigate("setting") }) {
                Text("Go to Detail")
            }
        }

}
@Composable
fun DetailScreen() {
    Scaffold { _ ->

    }
}