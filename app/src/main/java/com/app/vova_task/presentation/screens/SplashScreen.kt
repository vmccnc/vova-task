package com.app.vova_task.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.app.vova_task.R
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.delay

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@DelicateCoroutinesApi
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SplashScreen(
    navController: NavController,
) {

    SplashScreenContent()
    LaunchedEffect(Unit) {
        delay(3000)
        navController.navigate("main_page")
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenContent() {

    Scaffold { tt ->
        tt

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            content = {

                Image(
                    painter = painterResource(id = R.drawable.code_challenge),
                    contentDescription = "",
                    modifier = Modifier.fillMaxWidth().padding(16.dp)
                )

                Text(text = "Android - Coding Challenge")
            })

    }
}
