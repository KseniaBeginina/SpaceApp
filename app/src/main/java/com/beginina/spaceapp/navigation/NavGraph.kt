package com.beginina.spaceapp.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.beginina.domain.models.ApodModel
import com.beginina.spaceapp.MainViewModel
import com.beginina.spaceapp.presentation.pages.ApodPage
import com.beginina.spaceapp.presentation.pages.ApodViewModel
import com.beginina.spaceapp.presentation.pages.Error
import com.beginina.spaceapp.presentation.pages.Indicator
import com.beginina.spaceapp.presentation.pages.MainPage
import com.beginina.spaceapp.presentation.pages.OnboardingPage

@Composable
fun NavGraph(startDestination: String){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination
    ){
        composable(Routes.ONBOARDING){
            val mainViewModel: MainViewModel = hiltViewModel()
            OnboardingPage(
                mainViewModel = mainViewModel,
                navController = navController)
        }
        composable(Routes.MAIN){
            val apodViewModel: ApodViewModel = hiltViewModel()
            MainPage(
                apodViewModel = apodViewModel,
                navController = navController)
        }
        composable(Routes.ABOUT) {
            val apodViewModel: ApodViewModel = hiltViewModel()
            val apodId = navController.previousBackStackEntry?.savedStateHandle?.get<Int>("apod_id")
            var apod by remember { mutableStateOf<ApodModel?>(null) }

            var isLoading by remember { mutableStateOf(true) }
            var isError by remember { mutableStateOf(false) }

            LaunchedEffect(apodId) {
                if (apodId != null) {
                    try {
                        isLoading = true
                        apod = apodViewModel.getApodById(apodId)
                        isError = apod == null
                    } catch (e: Exception) {
                        isError = true
                    } finally {
                        isLoading = false
                    }
                } else {
                    isError = true
                    isLoading = false
                }
            }

            when {
                isLoading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Indicator()
                    }
                }
                isError -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Error()
                    }
                }
                apod != null -> {
                    ApodPage(
                        apodModel = apod!!,
                        navController = navController
                    )
                }
            }
        }
    }
}