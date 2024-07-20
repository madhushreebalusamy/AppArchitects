package com.example.aavinapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewTransactionUI (userSession: SessionDetails, navController: NavController) {
    var transaction by remember { mutableStateOf<Transaction?>(null) }
    val coroutineScope = rememberCoroutineScope()
    Scaffold (
        topBar = { TopAppBar(title = {
            Button(onClick = { navController.navigate("home") }, colors = ButtonColors(
                containerColor = Color(200, 200, 255),
                contentColor = Color(0, 0, 0,255),
                disabledContentColor = Color(0, 0, 155),
                disabledContainerColor = Color(200, 200, 255, 100)
            )
            ) {
                Text(text = "Home")
            }
        })
}
    ) {
        innerPadding ->
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(innerPadding)
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            if (transaction == null) {
                // Fetch the transaction data
                LaunchedEffect(userSession) {
                    coroutineScope.launch(Dispatchers.IO) {
                        transaction = requestTransaction(userSession)
                    }
                }
                CircularProgressIndicator() // Show a loading indicator while fetching data
            } else {
                transaction?.let {
                    Text(text = "Quantity: ${it.quantity}", fontSize = 20.sp)
                    Text(text = "Quality: ${it.quality}", fontSize = 20.sp)
                    Text(text = "Date Of Transaction: ${it.dateOfTransaction}", fontSize = 20.sp)
                    Text(text = "Rate: ${it.rate}", fontSize = 20.sp)
                    Text(text = "Society Id: ${it.societyID}", fontSize = 20.sp)
                }
            }
        }
    }
}