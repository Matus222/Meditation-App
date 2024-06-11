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
import com.example.meditation_app_v2.TimerDurationButton
import com.example.meditation_app_v2.app_ui.TimerViewModel

/**
Tento súbor reprezentuje menu v ktorom si užívateľ vie vybrať dĺžku časovaču.

@author Matúš Kendera
 */


/**
Táto funkcia vytvorí komponenty tohto menu.

@param timerViewModel je odkaz na viewModel ktorý táto aplikácia používa
@param navController je odkaz na navController ktorý táto aplikácia používa
 */
@Composable
fun TimerDurationMenu(timerViewModel: TimerViewModel, navController: NavController) {
    TimerDurationContent(timerViewModel = timerViewModel, navController = navController)
}

/**
Táto funkcia reprezentuje komponenty pre toto menu

@param timerViewModel je odkaz na viewModel ktorý táto aplikácia používa
@param navController je odkaz na navController ktorý táto aplikácia používa
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TimerDurationContent(
    timerViewModel: TimerViewModel,
    navController: NavController,
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
            TimerDurationButton(
                onClick = {
                    timerViewModel.changeTimerDuration(timerSeconds1)
                    navController.popBackStack()
                          },
                text = timerViewModel.formatTime(1000 * timerSeconds1)
            )

            TimerDurationButton(
                onClick = {
                    timerViewModel.changeTimerDuration(timerSeconds2)
                    navController.popBackStack()
                          },
                text = timerViewModel.formatTime(1000 * timerSeconds2)
            )

            TimerDurationButton(
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