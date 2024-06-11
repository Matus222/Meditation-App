package com.example.meditation_app_v2.menus

import android.content.Context
import android.media.MediaPlayer
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.meditation_app_v2.R
import com.example.meditation_app_v2.RingtoneMenuButton
import com.example.meditation_app_v2.app_ui.TimerViewModel

/**
Tento súbor reprezentuje menu v ktorom si užívateľ vie vybrať zvonenie.

@author Matúš Kendera
 */


/**
Táto funkcia vytvorí komponenty tohto menu.
 */
@Composable
fun RingtoneMenu(timerViewModel: TimerViewModel, navController: NavController, context: Context) {
    RingtoneContent(
        timerViewModel = timerViewModel,
        navController = navController,
        context = context
    )
}

/**
Táto funkcia reprezentuje komponenty pre toto menu

@param timerViewModel je odkaz na viewModel ktorý táto aplikácia používa
@param navController je odkaz na navController ktorý táto aplikácia používa
@param context je odkaz na context aktuálny kontext tejto aplikácie
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RingtoneContent(
    timerViewModel: TimerViewModel,
    navController: NavController,
    context: Context,
) {
    var previewRingtone by remember { mutableStateOf(MediaPlayer()) }

    /**
    Táto funkcia spustí zvonenie keď užívateľ klikne na PreviewButton
    @param ringtone je zvonenie ktoré chce užívateľ spustiť
    */
    fun playPreviewRingtone(ringtone: Int) {
        previewRingtone.stop()
        previewRingtone = MediaPlayer.create(context, ringtone)
        previewRingtone.start()
    }

    /**
    Táto funkcia vypne zvonenie keď si užívateľ klikne na RingtoneMenuButton
    */
    fun cleanUp() {
        previewRingtone.stop()
        navController.popBackStack()
    }

    /**
    Vytvárame Scaffold layout kde sú umiestnené všetky tlačidlá tohto menu
    */
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(text = "Výber zvonenia", textAlign = TextAlign.Center)
                }
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            Row {
                RingtoneMenuButton(
                    onClick = {
                        timerViewModel.changeRingtone(R.raw.ringtone1)
                        cleanUp()
                              },
                    text = "Ringtone 1"
                )
                PreviewButton(onClick = { playPreviewRingtone(R.raw.ringtone1) })
            }

            Row {
                RingtoneMenuButton(
                    onClick = {
                        timerViewModel.changeRingtone(R.raw.ringtone2)
                        cleanUp()
                              },
                    text = "Ringtone 2"
                )
                PreviewButton(onClick = { playPreviewRingtone(R.raw.ringtone2)  })
            }

            Row {
                RingtoneMenuButton(
                    onClick = {
                        timerViewModel.changeRingtone(R.raw.ringtone3)
                        cleanUp()
                              },
                    text = "Ringtone 3"
                )
                PreviewButton(onClick = { playPreviewRingtone(R.raw.ringtone3) })
            }
        }
    }
}

/**
Táto funkcia reprezentuje tlačidlo pomocou ktorého si užívateľ vie vypočuť zvonenie

@param onClick je odkaz na funkciu ktorá hovorí čo sa má stať po spustení tlačidla
@param color je farba tohto tlačidla
 */
@Composable
private fun PreviewButton(
    onClick: () -> Unit,
    color: Color = Color(149, 111, 237),
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(10.dp),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 10.dp,
            pressedElevation = 6.dp
        ),
        colors = ButtonDefaults.buttonColors(
            containerColor = color,
            contentColor = Color.Black
        ),

        modifier = Modifier.fillMaxWidth(0.75f).padding(10.dp)

    ) {
        Icon(
            imageVector = Icons.Filled.PlayArrow,
            contentDescription = null,
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun RingtoneMenuPreview() {
    val context = LocalContext.current
    val navControler = rememberNavController()
    RingtoneMenu(TimerViewModel(), navControler, context)
}