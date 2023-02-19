package com.app.vova_task.presentation.screens

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun FinalScoreDialog(
    clickOk: () -> Unit,
) {


    AlertDialog(
        onDismissRequest = { },
        title = { Text("From DB") },
        text = { Text("Internet is not available. Data from cache.") },
        confirmButton = {
            TextButton(clickOk) {
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