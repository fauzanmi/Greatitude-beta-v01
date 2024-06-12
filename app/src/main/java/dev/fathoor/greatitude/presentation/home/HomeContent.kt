package dev.fathoor.greatitude.presentation.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.produceState
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import dev.fathoor.greatitude.presentation.theme.FontPlusJakarta

@Composable
fun HomeContent(
    session: Long,
    navController: NavHostController
) {
    val listState = rememberLazyListState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "$ 's Diary",
                        style = TextStyle(
                            color = Color(0xFF192C54),
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp,
                            fontFamily = FontPlusJakarta
                        )
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFFFFFFFF)
                )
            )
        },
        floatingActionButton = {
            AnimatedVisibility(
                visible = listState.isScrollingUp().value,
                enter = fadeIn(
                    animationSpec = spring(Spring.DampingRatioLowBouncy, Spring.StiffnessLow)
                ),
                exit = fadeOut(
                    animationSpec = spring(Spring.DampingRatioLowBouncy, Spring.StiffnessLow)
                )
            ) {
                ExtendedFloatingActionButton(
                    onClick = { },
                    containerColor = Color(0xFF9E5325),
                    contentColor = Color(0xFFFDF9ED),
                    icon = {
                        Icon(imageVector = Icons.Outlined.Edit, contentDescription = null)
                    },
                    text = {
                        Text(
                            text = "New Entry",
                            style = TextStyle(
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 13.sp,
                                fontFamily = FontPlusJakarta
                            )
                        )
                    }
                )
            }
        },
        containerColor = Color(0xFFFFFFFF)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                // Content
            }
        }
    }
}

@Composable
private fun LazyListState.isScrollingUp(): State<Boolean> {
    return produceState(initialValue = true) {
        var lastIndex = 0
        var lastScroll = Int.MAX_VALUE
        snapshotFlow {
            firstVisibleItemIndex to firstVisibleItemScrollOffset
        }.collect { (currentIndex, currentScroll) ->
            if (currentIndex != lastIndex || currentScroll != lastScroll) {
                value = currentIndex < lastIndex ||
                        (currentIndex == lastIndex && currentScroll < lastScroll)
                lastIndex = currentIndex
                lastScroll = currentScroll
            }
        }
    }
}
