package com.example.aavinapp


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionUI(userSession: SessionDetails, navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Button(onClick = {
                                 navController.navigate("home")
                                 }, colors = ButtonColors(
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
        Column(modifier = Modifier.padding(innerPadding).fillMaxWidth().fillMaxHeight(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Fill The Below Details", fontSize = 30.sp)
            var quantity by remember {
                mutableStateOf("")
            }
            var quality by remember {
                mutableStateOf("")
            }

            TextField(value = quantity, onValueChange = { quantity = it }, label = {
                Text(text = "Quantity")
            })
            TextField(value = quality, onValueChange = { quality = it }, label = {
                Text(text = "Quality")
            })

            Button(onClick = {
                println("Bill venuma")
                navController.navigate("viewTransaction")
            }) {
                Text(text = "Generate Bill")
            }

        }
    }
}