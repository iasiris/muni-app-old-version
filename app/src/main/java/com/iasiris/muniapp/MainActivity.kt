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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.iasiris.muniapp.navigation.Routes
import com.iasiris.muniapp.ui.theme.MuniAppTheme
import com.iasiris.muniapp.utils.paddingLarge
import com.iasiris.muniapp.view.ui.home.Home
import com.iasiris.muniapp.view.ui.login.Login
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
            .background(MaterialTheme.colorScheme.background)
            .padding(top = paddingLarge, start = paddingLarge, end = paddingLarge),
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
        startDestination = Routes.Login.name
    ) { //TODO agregar if para chequear si usuario esta loggeado, si esta loggeado llevar directamente a home
        composable(Routes.Login.name) { Login(padding, navigationController) }
        composable(Routes.Home.name) { Home(padding, navigationController) }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    MuniAppTheme {
        MuniApp()
    }
}