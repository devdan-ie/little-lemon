package com.example.littlelemon.util

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.littlelemon.pages.HomeScreen
import com.example.littlelemon.pages.OnboardingScreen
import com.example.littlelemon.pages.ProfileScreen

@Composable
fun NavigationComposable(navController: NavHostController) {
    val savedUser = SharedPreferencesHelper.getUserFromSharedPreferences(navController.context)
    val isLoggedIn = savedUser != null

    NavHost(navController, startDestination = if (isLoggedIn) {
        AppDestinations.home
    } else {
        AppDestinations.onboarding
    }) {
        composable(AppDestinations.onboarding) {
            OnboardingScreen(navController)
        }
        composable(AppDestinations.home) {
            HomeScreen(navController)
        }
        composable(AppDestinations.profile) {
            ProfileScreen(navController)
        }
    }
}
