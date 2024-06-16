package com.example.meditation_app_v2.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.meditation_app_v2.app_ui.AppScreens
import androidx.compose.foundation.lazy.items
import com.example.meditation_app_v2.buttons.AddButton
import com.example.meditation_app_v2.data.formatTime
import com.example.meditation_app_v2.buttons.TimerDurationButton
import com.example.meditation_app_v2.view_models.TimerDurationEntryViewModel
import com.example.meditation_app_v2.view_models.TimerDurationItemDetails
import com.example.meditation_app_v2.view_models.TimerViewModel

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
fun TimerDurationScreen(
    timerViewModel: TimerViewModel,
    timerDurationEntryViewModel: TimerDurationEntryViewModel,
    navController: NavController
) {
    TimerDurationContent(
        timerViewModel = timerViewModel,
        timerDurationEntryViewModel = timerDurationEntryViewModel,
        navController = navController
    )
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
    timerDurationEntryViewModel: TimerDurationEntryViewModel,
    navController: NavController
) {
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
        LazyColumn(
            modifier = Modifier.padding(innerPadding)
        ) {
            items(timerDurationEntryViewModel.items) { item ->
                ItemView(
                    item = item,
                    timerViewModel = timerViewModel,
                    navController = navController
                )
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.Bottom
    ) {
        AddButton(
            onClick = {
                navController.navigate(AppScreens.TimerDurationAdd.name)
            }
        )
    }
}

@Composable
fun ItemView(
    item: TimerDurationItemDetails,
    timerViewModel: TimerViewModel,
    navController: NavController
) {
    val hoursToSeconds = if (item.hours.isNotBlank()) item.hours.toLong() * 3600 else 0
    val minutesToSeconds = if (item.minutes.isNotBlank()) item.minutes.toLong() * 60 else 0
    val seconds = if (item.seconds.isNotBlank()) item.seconds.toLong() else 0

    val timerDurationInSeconds: Long = hoursToSeconds + minutesToSeconds + seconds
    TimerDurationButton(
        onClick = {
            timerViewModel.changeTimerDuration(timerDurationInSeconds)
            navController.popBackStack()
                  },
        text = formatTime(1000 * timerDurationInSeconds)
    )
}

@Composable
@Preview(showBackground = true)
fun TimerDurationPreview() {
    val navController = rememberNavController()
    //TimerDurationScreen(TimerViewModel(), TimerDurationEntryViewModel(), navController)
}