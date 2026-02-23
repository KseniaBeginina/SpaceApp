package com.beginina.spaceapp.presentation.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.beginina.spaceapp.MainViewModel
import com.beginina.spaceapp.R
import com.beginina.spaceapp.navigation.Routes
import com.beginina.spaceapp.presentation.components.FilledButton
import com.beginina.spaceapp.ui.theme.LightGray
import com.beginina.spaceapp.ui.theme.semibold16
import com.beginina.spaceapp.ui.theme.semibold36

@Composable
fun OnboardingPage(
    mainViewModel: MainViewModel,
    navController: NavController
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
    ){
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.onb_image),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black.copy(alpha = 0.8f)
                        )
                    )
                )
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 40.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ){
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ){
                Text(
                    text = stringResource(R.string.onb_title),
                    style = semibold36,
                    color = LightGray
                )
                Text(
                    text = stringResource(R.string.onb_text),
                    style = semibold16,
                    color = LightGray
                )
            }

            FilledButton(
                text = stringResource(R.string.onb_button)
            ) {
                mainViewModel.completeOnboarding()
                navController.navigate(Routes.MAIN){
                    popUpTo(Routes.ONBOARDING){
                        inclusive = true
                    }
                }
            }
        }
    }
}