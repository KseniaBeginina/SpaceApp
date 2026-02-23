package com.beginina.spaceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.beginina.spaceapp.navigation.NavGraph
import com.beginina.spaceapp.ui.theme.SpaceAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SpaceAppTheme {
                val startDestination = mainViewModel.startDestination.value
                NavGraph(startDestination = startDestination)
            }
        }
    }
}