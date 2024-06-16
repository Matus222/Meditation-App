package com.example.meditation_app_v2.screens

import android.content.Context
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.sharp.Close
import androidx.compose.material.icons.sharp.Menu
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.inset
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.meditation_app_v2.data.formatTime
import com.example.meditation_app_v2.view_models.TimerViewModel
import com.example.meditation_app_v2.ui.theme.Meditation_App_V2Theme

/**
Tento súbor reprezentuje menu v ktorom užívateľ spustí časovač s vybranou dĺžkou.

@author Matúš Kendera
 */

/**
Táto funkcia vytvorí komponenty tohto menu a počúva, či sa zmenil UI state aplikácie.

@param timerViewModel je odkaz na viewModel ktorý táto aplikácia používa
@param context je odkaz na context aktuálny kontext tejto aplikácie
 */
@Composable
fun TimerMenu(timerViewModel: TimerViewModel, context: Context) {
    val timerUiState by timerViewModel.uiState.collectAsState()
    val timerMilliseconds = timerUiState.timerMilliseconds
    val fullTime = timerUiState.timerSeconds

    TimerContent(
        timerMilliseconds = timerMilliseconds,
        onStartClick = {timerViewModel.startTimer(context = context)},
        onPauseClick = {timerViewModel.pauseTimer()},
        onStopClick = {timerViewModel.stopTimer()}
    )

    /**
    Tu vypočítavame uhol (sweepAngle) pre animáciu kruhu časovača.
     */
    val timerPercentage = timerMilliseconds / 1000f / fullTime
    val invertedPercentage = 1.0f - timerPercentage
    val additionalAngle = 360f / fullTime

    val targetValue = (360f + additionalAngle) * (invertedPercentage)
    val sweepAngle: Float by animateFloatAsState(
        targetValue = targetValue,
        label = "SweepAngle",
        animationSpec = tween(durationMillis = 1000, easing = LinearEasing)
    )

    TimerAnimation(sweepAngle = sweepAngle)
}

/**
Táto funkcia reprezentuje komponenty pre toto menu

@param timerViewModel je odkaz na viewModel ktorý táto aplikácia používa.
@param timerMilliseconds je odkaz na počet milisekúnd ktoré ubehli od začatia časovača.
@param onStartClick je odkaz na funckiu, ktorá hovorí čo sa má stať po kliknutí tlačidla start
@param onPauseClick je odkaz na funckiu, ktorá hovorí čo sa má stať po kliknutí tlačidla pause
@param onStopClick je odkaz na funckiu, ktorá hovorí čo sa má stať po kliknutí tlačidla stop
 */
@Composable
fun TimerContent(
    timerMilliseconds: Long,
    onStartClick: () -> Unit,
    onPauseClick: () -> Unit,
    onStopClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = formatTime(timerMilliseconds),
            fontSize = 55.sp,
            fontFamily = FontFamily.SansSerif,
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 100.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.padding(top = 5.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            TimerButton(
                onClick = onStartClick,
                imageVector = Icons.Filled.PlayArrow
            )
            Spacer(modifier = Modifier.padding(10.dp))

            TimerButton(
                onClick = onPauseClick,
                imageVector = Icons.Sharp.Menu
            )
            Spacer(modifier = Modifier.padding(10.dp))

            TimerButton(
                onClick = onStopClick,
                imageVector = Icons.Sharp.Close
            )
        }
    }
}

/**
Táto funkcia vytvorí vizuálny kruh, ktorý reprezentuje čas ktorý uplinul od začatia časovaču.

@param sweepAngle je terajší uhol kruhu časovača.
 */
@Composable
fun TimerAnimation(
    sweepAngle: Float
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp

    val arcWidth = 40f
    val radiusOffset = 30
    val timerRadius = screenWidth.toFloat() - arcWidth/2 - radiusOffset

    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .requiredHeight(timerRadius.dp)
        ) {
            inset(
                horizontal = size.width/2 - timerRadius,
                vertical = size.height/2 - timerRadius
            ) {
                drawCircle(
                    color = Color.Gray,
                    radius = timerRadius,
                    center = center,
                    style = Stroke(width = arcWidth, cap = StrokeCap.Round),
                    alpha = 0.5f
                )

                drawArc(
                    startAngle = 0f,
                    sweepAngle = sweepAngle,
                    useCenter = false,
                    color = Color(149, 111, 237),
                    style = Stroke(width = arcWidth, cap = StrokeCap.Square)
                )
            }
        }
    }
}

/**
Táto funkcia reprezentuje jedno z 3 tlačidiel časovaču (start, pause a stop).

@param onClick je odkaz na funckiu, ktorá hovorí čo sa má stať po kliknutí tohto tlačidla.
@param imageVector je odkaz na vektorový obrázok pre toto tlačidlo.
 */
@Composable
fun TimerButton(
    onClick: () -> Unit,
    imageVector: ImageVector
) {
    ElevatedButton(
        onClick = onClick,
        shape = CircleShape,
        modifier= Modifier.size(80.dp),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 10.dp,
            pressedElevation = 6.dp
        ),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(149, 111, 237),
            contentColor = Color.Black
        ),
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(1.0F)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TimerMenuPreview() {
    Meditation_App_V2Theme {
        val context = LocalContext.current
        TimerMenu(timerViewModel = TimerViewModel(), context = context)
    }
}