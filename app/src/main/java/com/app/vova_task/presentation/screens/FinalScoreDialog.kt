package com.app.vova_task.presentation.screens

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun FinalScoreDialog(

    onPlayAgain: () -> Unit,

) {


    AlertDialog(
        onDismissRequest = {

        },
        title = { Text("From DB") },
        text = { Text("Internate is not avaluble . Data from cache.") },
//        modifier = modifier,

        confirmButton = {
            TextButton(onClick = onPlayAgain) {
                Text(text = "Ok")
            }
        }
    )
}


@Preview(showBackground = true)
@Composable
fun FinalScoreDialog_Preview() {

    FinalScoreDialog(  {})

}