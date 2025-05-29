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
import com.iasiris.muniapp.ui.theme.MuniAppTheme
import com.iasiris.muniapp.utils.paddingLarge
import com.iasiris.muniapp.view.ui.Home

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
        //TODO bottomBar = { BottomNavigationBar() }
    ) {
        AppScreen(Modifier.padding(it))
    }
}

@Composable
fun AppScreen(padding: Modifier) {
    Home(padding)
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    MuniAppTheme {
        MuniApp()
    }
}