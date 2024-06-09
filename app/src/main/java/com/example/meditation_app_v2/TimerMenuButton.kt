package com.example.meditation_app_v2

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TimerMenuButton(
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
        modifier = Modifier.fillMaxWidth(1f).padding(10.dp)
    ) {
        Text(text = text)
    }
}

@Composable
@Preview(showBackground = true)
private fun TimerMenuButtonPreview() {
    TimerMenuButton(
        onClick = { /*TODO*/ },
        text = "SOMe"
    )
}