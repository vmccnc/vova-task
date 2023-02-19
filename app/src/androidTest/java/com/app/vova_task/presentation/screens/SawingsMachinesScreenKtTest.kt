package com.app.vova_task.presentation.screens

import androidx.activity.ComponentActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.NavController
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.app.vova_task.presentation.navigation.ChallengeApp
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalFoundationApi::class)
internal class SawingsMachinesScreenKtTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var navController: TestNavHostController

    @Before
    fun setupCupcakeNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())

            ChallengeApp(navController = navController)
        }
    }

    @Test
    fun navHost_verifyStartDestination() {
//        assertEquals(CupcakeScreen.Start.name, navController.currentBackStackEntry?.destination?.route)

        val str = navController.currentBackStackEntry?.destination?.route
        println("dm:: Destination: $str")
        navController.assertCurrentRouteName("splash_page")

//        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.my_string)
//        composeTestRule.onNodeWithText("Annette")
    }

}



fun NavController.assertCurrentRouteName(expectedRouteName: String) {
    Assert.assertEquals(expectedRouteName, this.currentBackStackEntry?.destination?.route)
}
