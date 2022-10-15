package com.practice.hiltapplicationSample.presentations.navigations

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.practice.hiltapplicationSample.presentations.screens.AvatarCreationScreen
import com.practice.hiltapplicationSample.presentations.screens.AvatarListScreen

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "avatar_creation"
    ) {
        composable("avatar_creation") {
            AvatarCreationScreen(navigateToList = {
                navController.navigate("avatar_list")
            })
        }
        composable("avatar_list") {
            AvatarListScreen(backToCreation = {
                navController.popBackStack()
            })
        }
    }
}
