package com.zagart.navigation.template.feature.bonus.presentation.overview

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zagart.navigation.template.feature.bonus.ui.components.BonusLanesActions
import com.zagart.navigation.template.feature.bonus.ui.overview.BonusScreenActions
import com.zagart.navigation.template.feature.bonus.ui.overview.BonusScreenUi
import com.zagart.navigation.template.presentation.navigation.BonusNavBarDestination
import com.zagart.navigation.template.presentation.navigation.collectNavigationEvents
import com.zagart.navigation.template.presentation.navigation.onBack
import com.zagart.navigation.template.ui.Tab

@Composable
fun BonusScreen(
    destination: BonusNavBarDestination,
    modifier: Modifier = Modifier,
    viewModel: BonusViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val actions = remember(viewModel) {
        BonusScreenActions(
            bonusLanesActions = BonusLanesActions(
                onBonusBoxBannerClick = { viewModel.onBonusBoxClick() },
                onProductClick = { viewData -> viewModel.onProductClick(viewData) },
                onBonusGroupClick = { viewData ->
                    viewModel.onBonusGroupClick(
                        viewData
                    )
                }
            ),
        )
    }

    LaunchedEffect(destination) {
        viewModel.load(destination)
    }
    viewModel.collectNavigationEvents("BonusScreen")

    BackHandler(onBack = viewModel::onBack)
    BonusScreenUi(
        state = state,
        modifier = modifier,
        actions = actions,
    )
}