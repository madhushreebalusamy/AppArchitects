package com.example.aavinapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.aavinapp.ui.theme.AavinAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AavinAppTheme {
                AavinApp()
            }
        }
    }
}


@Preview
@Composable
fun AavinApp() {
    val navController = rememberNavController()
    var userSession by remember {
        mutableStateOf(SessionDetails("Milk Man", "-", "-", null, null))
    }
    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeUI(userSession, navController) }
        composable("login") { LoginUI(userSession, navController) }
        composable("signup") { SignUpUI(userSession, navController) }
        composable("newTransaction") { TransactionUI(userSession, navController) }
        composable("viewTransaction") { ViewTransactionUI(userSession, navController) }

//        composable("forgotPassword") { ForgotPasswordUI(userSession, navController) }
//        composable("profile") { ProfileUI(userSession, navController) }
//        composable("transaction") { TransactionUI(userSession, navController) }
    }
}