package com.example.meditation_app_v2.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.meditation_app_v2.view_models.TimerDurationEntryViewModel
import com.example.meditation_app_v2.view_models.TimerDurationItemDetails
import com.example.meditation_app_v2.view_models.TimerDurationItemUiState
import kotlinx.coroutines.launch

@Composable
fun TimerDurationAddScreen(navController: NavController, timerDurationEntryViewModel: TimerDurationEntryViewModel) {
    TimerDurationAddScreenContent(navController = navController, timerDurationEntryViewModel = timerDurationEntryViewModel)
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun TimerDurationAddScreenContent(
    navController: NavController,
    timerDurationEntryViewModel: TimerDurationEntryViewModel
) {
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(text = "Pridajte časovač", textAlign = TextAlign.Center)
                }
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            ItemEntryBody(
                itemUiState = timerDurationEntryViewModel.itemUiState,
                onItemValueChange = timerDurationEntryViewModel::updateUiState,
                onSaveClick = {
                    coroutineScope.launch {
                        timerDurationEntryViewModel.saveItem()
                        navController.popBackStack()
                    }
                },

                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .fillMaxWidth()
            )
            //Spacer(modifier = Modifier.height(30.dp))
        }
    }
}

@Composable
fun ItemEntryBody(
    itemUiState: TimerDurationItemUiState,
    onItemValueChange: (TimerDurationItemDetails) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ItemInputForm(
            itemDetails = itemUiState.itemDetails,
            onValueChange = onItemValueChange,
            modifier = Modifier.fillMaxWidth()
        )

        SaveButton(
            onClick = onSaveClick,
            text = "Uložiť",
            enabled = itemUiState.isEntryValid,
        )
    }
}

@Composable
fun ItemInputForm(
    itemDetails: TimerDurationItemDetails,
    onValueChange: (TimerDurationItemDetails) -> Unit = {},
    modifier: Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            label = { Text("Hodiny") },
            value = itemDetails.hours,
            onValueChange = {
                onValueChange(itemDetails.copy(hours = it))
                            },

            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer
            ),

            textStyle = TextStyle(color = Color.Blue, fontWeight = FontWeight.Bold),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        OutlinedTextField(
            label = { Text("Minúty") },
            value = itemDetails.minutes,
            onValueChange = {
                onValueChange(itemDetails.copy(minutes = it))
                            },

            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer
            ),

            textStyle = TextStyle(color = Color.Blue, fontWeight = FontWeight.Bold),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        OutlinedTextField(
            label = { Text("Sekundy") },
            value = itemDetails.seconds,
            onValueChange = {
                onValueChange(itemDetails.copy(seconds = it))
                            },

            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer
            ),

            textStyle = TextStyle(color = Color.Blue, fontWeight = FontWeight.Bold),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
    }
}

@Composable
fun SaveButton(
    onClick: () -> Unit,
    enabled: Boolean,
    text: String
) {
    ElevatedButton(
        onClick = onClick,
        enabled = enabled,
        shape = RoundedCornerShape(10.dp),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 10.dp,
            pressedElevation = 6.dp
        ),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(149, 111, 237),
            contentColor = Color.Black
        ),
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .padding(10.dp)
    ) {
        Text(text = text)
    }
}

@Composable
@Preview(showBackground = true)
fun TimerDurationAddScreenPreview() {
    val navController = rememberNavController()
    //TimerDurationAddScreen(navController, TimerDurationEntryViewModel())
}