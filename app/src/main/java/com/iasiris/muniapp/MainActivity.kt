package com.iasiris.muniapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.iasiris.feature.home.home.Home
import com.iasiris.feature.login.login.Login
import com.iasiris.muniapp.ui.theme.MuniAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MuniAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    MuniApp()
                }
            }
        }
    }
}

@Composable
fun MuniApp() {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),

        containerColor = MaterialTheme.colorScheme.background
    ) {
        AppScreen(Modifier.padding(it))
    }
}

@Composable
fun AppScreen(padding: Modifier) {
    val navigationController = rememberNavController()
    NavHost(
        navController = navigationController,
        startDestination = "Home"
    ) { //TODO agregar if para chequear si usuario esta loggeado, si esta loggeado llevar directamente a home
        composable("Login") { Login(padding, navigationController) }
        composable("Home") { Home(padding, navigationController) }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    MuniAppTheme {
        MuniApp()
    }
}