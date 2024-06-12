package dev.fathoor.greatitude.presentation.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.delay

@Composable
fun AppSplashScreen(
    viewModel: SplashViewModel = hiltViewModel(),
    navigateToHome: (Long) -> Unit,
    navigateToAuth: () -> Unit
) {
    val session by viewModel.session.collectAsState(0)
    val isLoading by viewModel.isLoading.collectAsState(true)

    LaunchedEffect(isLoading) {
        delay(2000L)

        if (session.toInt() != 0) {
            navigateToHome(session)
        } else {
            navigateToAuth()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE4B24E)),
        contentAlignment = Alignment.Center
    ) {

    }
}
