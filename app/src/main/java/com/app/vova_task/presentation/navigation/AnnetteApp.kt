package com.app.vova_task.presentation.navigation

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.app.vova_task.presentation.screens.SawingsMachinesScreen
import com.app.vova_task.presentation.screens.SplashScreen


@OptIn(ExperimentalMaterialApi::class)
@ExperimentalFoundationApi
@Composable
fun AnnetteApp(
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
            SawingsMachinesScreen() {
                navController.navigate("machine/${it.machineId}")
            }
        }


//        composable(
//            route = "machine/{id}",
//            arguments = listOf(navArgument("id") { type = NavType.IntType }),
//            content = { entry ->
//                val id = entry.arguments?.getInt("id")
//                Log.d("ddd", "dm:: machineId = $id")
//                ServiceScreen(id) {
//                    navController.popBackStack()
//                }
//            })


    }
}


