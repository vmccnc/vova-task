package com.app.vova_task.presentation.navigation

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.app.vova_task.presentation.screens.HitScreen
import com.app.vova_task.presentation.screens.HitsMachinesScreen
import com.app.vova_task.presentation.screens.SplashScreen


@OptIn(ExperimentalMaterialApi::class)
@ExperimentalFoundationApi
@Composable
fun ChallengeApp(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {


    NavHost(
        navController = navController,
        startDestination = "splash_page",
        modifier
    ) {


        composable(route = "splash_page") {
            SplashScreen(navController = navController)
        }

        composable(route = "main_page") {
            HitsMachinesScreen {
                navController.navigate("picture/${it.id}")
            }
        }


        composable(
            route = "picture/{id}",
            arguments = listOf(navArgument("id") { type = NavType.LongType }),
            content = { entry ->
                val id = entry.arguments?.getLong("id")
                Log.d("ddd", "dm:: machineId = $id")
                HitScreen(id) {
                    navController.popBackStack()
                }
            })


    }
}


