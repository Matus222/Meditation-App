package com.example.meditation_app_v2

import android.graphics.ColorSpace
import android.provider.CalendarContract.Colors
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun MainMenuButton(
    onClick: () -> Unit,
    buttonWidth: Dp,
    buttonHeight: Dp,
    color: Color = Color(149, 111, 237),
    imageVector: ImageVector
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
        modifier = Modifier.size(buttonWidth, buttonHeight)
    ) {
        Icon(imageVector = imageVector,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(1F))
    }
}

@Composable
@Preview(showBackground = true)
fun Preview() {
    MainMenuButton({}, 50.dp, 50.dp, imageVector = Icons.Filled.Person)
}