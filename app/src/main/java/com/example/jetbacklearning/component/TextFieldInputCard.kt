package com.example.jetbacklearning.component

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun setUserInput(
    modifierCard: Modifier,
    text: String,
    visualTransformation: VisualTransformation,
    interactionSource: MutableInteractionSource,
    callBackChangeUser: (String) -> Unit
){
    Card(
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        modifier = modifierCard,

    ) {
        BasicTextField(
            value = text,
            onValueChange = {callBackChangeUser(it)},
            visualTransformation = visualTransformation,
            singleLine = true,
        ) { innerTextField ->
            TextFieldDefaults.DecorationBox(
                value = text,
                visualTransformation = visualTransformation,
                innerTextField = innerTextField,
                singleLine = true,
                enabled = true,
                placeholder = { Text(text = "write your name") },
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent, // Hides the underline
                    unfocusedIndicatorColor = Color.Transparent, // Hides the underline
                    disabledIndicatorColor = Color.Transparent, // Hides the underline when disabled
                    errorIndicatorColor = Color.Transparent // Hides the underline when there's an error
                ),
                interactionSource = interactionSource,
                contentPadding = PaddingValues(15.dp), // this is how you can remove the padding
            )
        }
    }
}