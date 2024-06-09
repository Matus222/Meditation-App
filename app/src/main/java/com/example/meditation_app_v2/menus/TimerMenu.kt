package com.example.meditation_app_v2.menus

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.meditation_app_v2.R
import com.example.meditation_app_v2.app_ui.TimerUiState
import com.example.meditation_app_v2.app_ui.TimerViewModel
import com.example.meditation_app_v2.ui.theme.Meditation_App_V2Theme
import java.util.concurrent.TimeUnit

@Composable
fun TimerMenu(timerViewModel: TimerViewModel, context: Context) {
    val timerUiState by timerViewModel.uiState.collectAsState()
    val timerMilliseconds = timerUiState.timerMilliseconds

    TimerContent(
        timerViewModel = timerViewModel,
        timerMilliseconds = timerMilliseconds,
        onStartClick = {timerViewModel.startTimer(context = context)},
        onPauseClick = {timerViewModel.pauseTimer()},
        onStopClick = {timerViewModel.stopTimer()})
}

@Composable
fun TimerContent(
    timerViewModel: TimerViewModel,
    timerMilliseconds: Long,
    onStartClick: () -> Unit,
    onPauseClick: () -> Unit,
    onStopClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = timerViewModel.formatTime(timerMilliseconds), fontSize = 50.sp)

        Spacer(modifier = Modifier.padding(top = 5.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = onStartClick) {
                Text("Start")
            }

            Spacer(modifier = Modifier.width(15.dp))
            Button(onClick = onPauseClick) {
                Text("Pause")
            }

            Spacer(modifier = Modifier.width(15.dp))
            Button(onClick = onStopClick) {
                Text("Stop")
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun TimerMenuPreview() {
    Meditation_App_V2Theme {
        //TimerMenu(timerViewModel = TimerViewModel())
    }
}