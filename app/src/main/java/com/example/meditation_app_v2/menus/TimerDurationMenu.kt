package com.example.meditation_app_v2.menus

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.meditation_app_v2.R
import com.example.meditation_app_v2.RingtoneMenuButton
import com.example.meditation_app_v2.TimerMenuButton
import com.example.meditation_app_v2.app_ui.TimerViewModel

@Composable
fun TimerDurationMenu(timerViewModel: TimerViewModel, navController: NavController) {
    TimerDurationContent(timerViewModel = timerViewModel, navController = navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TimerDurationContent(
    timerViewModel: TimerViewModel,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val timerSeconds1: Long = 10
    val timerSeconds2: Long = 150
    val timerSeconds3: Long = 300

    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(text = "Výber dĺžky časovaču", textAlign = TextAlign.Center)
                }
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            TimerMenuButton(
                onClick = {
                    timerViewModel.changeTimerDuration(timerSeconds1)
                    navController.popBackStack()
                          },
                text = timerViewModel.formatTime(1000 * timerSeconds1)
            )

            TimerMenuButton(
                onClick = {
                    timerViewModel.changeTimerDuration(timerSeconds2)
                    navController.popBackStack()
                          },
                text = timerViewModel.formatTime(1000 * timerSeconds2)
            )

            TimerMenuButton(
                onClick = {
                    timerViewModel.changeTimerDuration(timerSeconds3)
                    navController.popBackStack()
                          },
                text = timerViewModel.formatTime(1000 * timerSeconds3)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun TimerDurationPreview() {
    val navController = rememberNavController()
    TimerDurationContent(TimerViewModel(), navController)
}