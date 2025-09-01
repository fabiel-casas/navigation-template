package com.zagart.navigation.template.feature.cooking.presentation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.zagart.navigation.template.presentation.navigation.CookingNavBarDestination
import com.zagart.navigation.template.presentation.navigation.collectNavigationEvents
import com.zagart.navigation.template.presentation.navigation.onBack
import com.zagart.navigation.template.ui.DummyScreen

@Composable
fun CookingScreen(
    destination: CookingNavBarDestination,
    modifier: Modifier = Modifier,
    viewModel: CookingViewModel = hiltViewModel(),
) {
    LaunchedEffect(destination) {
        viewModel.load(destination)
    }
    viewModel.collectNavigationEvents("CookingScreen")

    BackHandler(onBack = viewModel::onBack)
    DummyScreen(
        modifier = modifier,
        title = "Cooking",
    )
}