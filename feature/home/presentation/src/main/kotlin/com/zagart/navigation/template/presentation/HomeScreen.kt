package com.zagart.navigation.template.presentation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zagart.navigation.template.feature.home.ui.HomeScreenActions
import com.zagart.navigation.template.feature.home.ui.HomeScreenUi
import com.zagart.navigation.template.presentation.navigation.NavBarDestination
import com.zagart.navigation.template.presentation.navigation.collectNavigationEvents
import com.zagart.navigation.template.presentation.navigation.onBack
import com.zagart.navigation.template.ui.Tab

@Composable
fun HomeScreen(
    destination: NavBarDestination,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val actions = remember(viewModel) {
        HomeScreenActions(
            onBonusBoxBannerClick = { viewModel.onBonusBoxClick() },
            onProductClick = { viewData -> viewModel.onProductClick(viewData,) },
            onBonusGroupClick = { viewData ->
                viewModel.onBonusGroupClick(
                    viewData
                )
            }
        )
    }

    LaunchedEffect(destination) {
        viewModel.load(destination)
    }
    viewModel.collectNavigationEvents("HomeScreen")


    BackHandler(onBack = viewModel::onBack)
    HomeScreenUi(
        modifier = modifier,
        state = state,
        actions = actions,
    )
}