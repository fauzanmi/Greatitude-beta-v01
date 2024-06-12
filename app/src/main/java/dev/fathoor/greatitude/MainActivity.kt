package dev.fathoor.greatitude

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.fathoor.greatitude.navigation.NavigationHost
import dev.fathoor.greatitude.navigation.screen.Nav
import dev.fathoor.greatitude.presentation.theme.GreatitudeTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GreatitudeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    NavigationHost(
                        navController = rememberNavController(),
                        startDestination = Nav.Splash.route
                    )
                }
            }
        }
    }
}
