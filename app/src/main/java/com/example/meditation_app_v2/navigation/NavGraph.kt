package com.example.meditation_app_v2.navigation

import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.meditation_app_v2.app_ui.AppScreens
import com.example.meditation_app_v2.view_models.TimerViewModel
import com.example.meditation_app_v2.screens.MainScreen
import com.example.meditation_app_v2.screens.RingtoneScreen
import com.example.meditation_app_v2.screens.TimerDurationAddScreen
import com.example.meditation_app_v2.screens.TimerDurationScreen
import com.example.meditation_app_v2.screens.TimerMenu
import com.example.meditation_app_v2.view_models.TimerDurationEntryViewModel

@Composable
fun NavGraph(
    navController: NavHostController,
    timerViewModel: TimerViewModel,
    timerDurationEntryViewModel: TimerDurationEntryViewModel,
    context: Context
) {
    NavHost(
        navController = navController,
        startDestination = AppScreens.Main.name,
        modifier = Modifier.fillMaxSize()
    ) {
        composable(route = AppScreens.Main.name) {
            MainScreen(navController = navController)
        }

        composable(route = AppScreens.Timer.name) {
            TimerMenu(timerViewModel = timerViewModel, context = context)
        }

        composable(route = AppScreens.Ringtone.name) {
            RingtoneScreen(timerViewModel = timerViewModel, navController = navController, context = context)
        }

        composable(route = AppScreens.TimerDuration.name) {
            TimerDurationScreen(timerViewModel = timerViewModel, timerDurationEntryViewModel = timerDurationEntryViewModel, navController = navController)
        }
        
        composable(route = AppScreens.TimerDurationAdd.name) {
            TimerDurationAddScreen(navController = navController, timerDurationEntryViewModel = timerDurationEntryViewModel)
        }
    }
}