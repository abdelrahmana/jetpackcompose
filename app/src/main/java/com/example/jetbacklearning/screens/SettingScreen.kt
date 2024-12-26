package com.example.jetbacklearning.screens

import android.widget.Toast
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.example.jetbacklearning.component.setUserInput
import kotlinx.coroutines.launch
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScreen(navController: NavHostController) {
    val myViewModel: ViewModelSetting = viewModel() // Create ViewModel instance

    var text by remember { mutableStateOf("") }
    var textPassword by remember { mutableStateOf("") }
    val interactionSource = remember { MutableInteractionSource() }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val visualTransformation = if (text.isNotEmpty()) {
        VisualTransformation.None
    } else {
        VisualTransformation.None // Show the hint text as visual transformation
    }
    Scaffold(snackbarHost = { SnackbarHost(hostState = snackbarHostState) }) { padding ->
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val context = LocalContext.current
            val (button, userName, password) = createRefs()

            setUserInput(
                modifierCard = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 25.dp)
                    .constrainAs(userName) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom, margin = 150.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }, text, visualTransformation, interactionSource
            ) { textValue -> text = textValue }
            /*TextField(
                value = text,
                onValueChange = { text = it },
                singleLine = true,
                visualTransformation = visualTransformation,
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent, // Hides the underline
                    unfocusedIndicatorColor = Color.Transparent, // Hides the underline
                    disabledIndicatorColor = Color.Transparent, // Hides the underline when disabled
                    errorIndicatorColor = Color.Transparent // Hides the underline when there's an error
                ),
                label = { Text(stringResource(id = R.string.app_name)) },
            )
        }*/
            setUserInput(
                modifierCard = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 25.dp)
                    .constrainAs(password) {
                        top.linkTo(userName.bottom, margin = 16.dp)
                        start.linkTo(userName.start)
                        end.linkTo(userName.end)
                    }, textPassword, visualTransformation, interactionSource
            ) { textValue -> textPassword = textValue }
            /* TextField(
                 value = text,
                 onValueChange = { password = it },
                 placeholder = { Text(text = "dasdas")},
                 //label = { Text("Enter your text") },
                 modifier = Modifier.constrainAs(password) {
                     top.linkTo(userName.bottom, margin = 16.dp)
                     start.linkTo(userName.start)
                     end.linkTo(userName.end)

                 }
             )*/
            Button(
                onClick = { /* Do something */
                    if (!checkValidation(text, textPassword))
                        Toast.makeText(context, "missing user name or password", Toast.LENGTH_SHORT)
                            .show()
                    else
                        scope.launch{
                            myViewModel.setAddValue()
                        }
                },
                // Assign reference "button" to the Button composable
                // and constrain it to the top of the ConstraintLayout
                modifier = Modifier
                    .defaultMinSize(minWidth = 150.dp)
                    .constrainAs(button) {
                        top.linkTo(password.bottom, margin = 16.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            ) {
                Text("Button")
            }
            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier.wrapContentHeight(Alignment.Top) // Align at the top center
                    .padding(16.dp)
            )

            // Assign reference "text" to the Text composable
            // and constrain it to the bottom of the Button composable
        }
    }
    LaunchedEffect(Unit){
        myViewModel.eventFlow.collect{
            navController.navigate("detailsScreen/$text/$textPassword"){
                popUpTo("setting") { inclusive = true } // Clears screen1 from the back stack

            }
            snackbarHostState.showSnackbar("good to go "+it)
        }
    }
}

fun checkValidation(text: String, textPassword: String): Boolean {
    return text.isNotEmpty() && textPassword.isNotEmpty()
}
