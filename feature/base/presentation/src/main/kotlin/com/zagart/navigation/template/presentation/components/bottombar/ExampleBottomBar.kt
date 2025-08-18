package com.zagart.navigation.template.presentation.components.bottombar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zagart.navigation.template.presentation.navigation.HomeBackstack
import com.zagart.navigation.template.presentation.navigation.LocalNavigation
import com.zagart.navigation.template.presentation.navigation.collectNavigationEvents
import com.zagart.navigation.template.presentation.navigation.isApplication
import com.zagart.navigation.template.ui.ExampleBottomBarUi

@Composable
fun ExampleBottomBar(
    modifier: Modifier = Modifier,
    viewModel: ExampleBottomBarViewModel = hiltViewModel(),
) {
    val currentDestination by LocalNavigation.current
        .destinationFlow
        .collectAsStateWithLifecycle(initialValue = HomeBackstack())
    viewModel.collectNavigationEvents()

    if (currentDestination.args.bottomBarScope.isApplication()) {
        ExampleBottomBarUi(
            modifier = modifier,
            selectedItemIndex = currentDestination.args.backstackIndex,
            onItemClicked = viewModel::onBottomBarItemClick
        )
    }
}