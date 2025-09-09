package com.zagart.navigation.template.feature.bonus.presentation.bonusbox

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zagart.navigation.template.feature.bonus.ui.bonusbox.BonusBoxScreenActions
import com.zagart.navigation.template.feature.bonus.ui.bonusbox.BonusBoxScreenUi
import com.zagart.navigation.template.feature.bonus.ui.components.BonusLanesActions
import com.zagart.navigation.template.presentation.navigation.BonusBoxDestination
import com.zagart.navigation.template.presentation.navigation.collectNavigationEvents

@Composable
fun BonusBoxScreen(
    destination: BonusBoxDestination,
    modifier: Modifier = Modifier,
    viewModel: BonusBoxViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val actions = remember(viewModel) {
        BonusBoxScreenActions(
            bonusLanesActions = BonusLanesActions(
                onAdvertisementClick = {},
                onBonusBoxBannerClick = { viewModel.onBonusBoxClick() },
                onBonusGroupClick = { viewModel.onBonusGroupClick(it) },
                onProductClick = { viewModel.onProductClick(it) }
            ),
            onBack = viewModel::onBackAction,
        )
    }

    LaunchedEffect(destination) {
        viewModel.load(destination)
    }
    viewModel.collectNavigationEvents("BonusBoxScreen")

    BackHandler(onBack = viewModel::onBackAction)
    BonusBoxScreenUi(
        state = state,
        modifier = modifier,
        actions = actions,
    )
}