package com.example.meditation_app_v2.buttons

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
Tento súbor reprezentuje tlačidlo pre menu kde si užívateľ vyberá zvonenie.

@author Matúš Kendera
 */

/**
Táto funkcia vytvára tlačidlo pre menu kde si užívateľ vyberá zvonenie.

@param onClick je odkaz na funckiu, ktorá hovorí čo sa má stať po kliknutí tohto tlačidla.
@color je farba tlačidla.
@param text je názov zvonenia
 */
@Composable
fun RingtoneMenuButton(
    onClick: () -> Unit,
    color: Color = Color(149, 111, 237),
    text: String
) {
    ElevatedButton(
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
        modifier = Modifier.fillMaxWidth(0.7f).padding(10.dp)
    ) {
        Text(text = text, fontSize = 15.sp)
    }
}

@Composable
@Preview(showBackground = true)
private fun RingtoneButtonPreview() {
    RingtoneMenuButton(
        onClick = { /*TODO*/ },
        text = "Something here"
    )
}