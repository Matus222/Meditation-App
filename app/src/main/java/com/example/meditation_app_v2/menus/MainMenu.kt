package com.example.meditation_app_v2.menus

import android.content.Context
import android.graphics.Typeface
import android.widget.TextClock
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFontFamilyResolver
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontSynthesis
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.meditation_app_v2.MainMenuButton
import com.example.meditation_app_v2.R
import com.example.meditation_app_v2.app_ui.TimerViewModel
import com.example.meditation_app_v2.app_ui.AppScreens
import com.example.meditation_app_v2.ui.theme.Meditation_App_V2Theme

/**
Tento súbor reprezentuje hlavné menu tejto aplikácia.

@author Matúš Kendera
 */

/**
Táto funkcia vytvorí komponenty tohto menu.

@param navController je odkaz na navController ktorý táto aplikácia používa
@param modifier je Modifier, ktorý táto funkcia používa
 */
@Composable
fun MainMenu(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val image = painterResource(R.drawable.flowersbackground)

    val buttonWidth: Dp = 80.dp
    val buttonHeight: Dp = 80.dp

    Clock()

    /**
    Vytvárame Box layout v ktorom načítavame obrázok na pozadie tohto menu.
    */
    Box(modifier) {
        Image(
            painter = image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            alpha = 0.5f
        )
    }

    /**
    Vytvárame Row layout v ktorom v ktorom sú 3 tlačítka na prepínanie medzi
    jednotlivými obrazovkami/menu.
    */
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(bottom = 150.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Bottom
    ) {
        MainMenuButton(
            onClick = { navController.navigate(AppScreens.Timer.name) },
            buttonWidth = buttonWidth,
            buttonHeight = buttonHeight,
            imageVector = Icons.Filled.AddCircle,
        )
        Spacer(modifier = Modifier.width(20.dp))

        MainMenuButton(
            onClick = { navController.navigate(AppScreens.Ringtone.name) },
            buttonWidth = buttonWidth,
            buttonHeight = buttonHeight,
            imageVector = Icons.Filled.Notifications,
        )
        Spacer(modifier = Modifier.width(20.dp))

        MainMenuButton(
            onClick = { navController.navigate(AppScreens.TimerDuration.name) },
            buttonWidth = buttonWidth,
            buttonHeight = buttonHeight,
            imageVector = Icons.Filled.Settings,
        )
    }
}

/**
Táto funkcia vytvára hodinky, ktoré ukazujú aktuálny čas na danom mobile.

@param style je MaterialTheme pre text času
 */
@Composable
fun Clock(
    style: TextStyle = MaterialTheme.typography.labelLarge
) {
    Column(
        Modifier
            .fillMaxSize()
            .fillMaxHeight()
            .fillMaxWidth().padding(top = 60.dp),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        val resolver = LocalFontFamilyResolver.current
        val face: Typeface = remember(resolver, style) {
            resolver.resolve(
                fontFamily = style.fontFamily,
                fontWeight = style.fontWeight ?: FontWeight.Normal,
                fontStyle = style.fontStyle ?: FontStyle.Normal,
                fontSynthesis = style.fontSynthesis ?: FontSynthesis.All
            )
        }.value as Typeface

        AndroidView(
            factory = { context ->
                TextClock(context).apply {
                    format12Hour?.let { this.format12Hour = "hh:mm:ss a" }
                    timeZone?.let { this.timeZone = it }
                    textSize.let { this.textSize = 50f }

                    setTextColor(textColors.withAlpha(1000))
                    typeface = face
                }
            }
        )
    }
}

/**
Táto funkcia vytvára navController, pomocou ktorého sa vieme prepínať medzi obrazovkami.

@param timerViewModel je odkaz na viewModel ktorý táto aplikácia používa
@param context je odkaz na context aktuálny kontext tejto aplikácie
 */
@Composable
fun MeditationApp(
    timerViewModel: TimerViewModel,
    context: Context,
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "Main",
        modifier = Modifier.fillMaxSize()
    ) {
        composable(route = AppScreens.Main.name) {
            MainMenu(navController = navController)
        }

        composable(route = AppScreens.Timer.name) {
            TimerMenu(timerViewModel = timerViewModel, context = context)
        }

        composable(route = AppScreens.Ringtone.name) {
            RingtoneMenu(timerViewModel = timerViewModel, navController = navController, context = context)
        }

        composable(route = AppScreens.TimerDuration.name) {
            TimerDurationMenu(timerViewModel = timerViewModel, navController = navController)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Meditation_App_V2Theme {
        val context = LocalContext.current
        MeditationApp(TimerViewModel(), context)
    }
}