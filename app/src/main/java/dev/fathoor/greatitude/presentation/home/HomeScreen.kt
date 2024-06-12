package dev.fathoor.greatitude.presentation.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    session: Long,
    navController: NavHostController
) {
    val userSession by viewModel.session.collectAsState(0)

    HomeContent(
        session = if (session.toInt() == 0) userSession else session,
        navController = navController
    )
}
