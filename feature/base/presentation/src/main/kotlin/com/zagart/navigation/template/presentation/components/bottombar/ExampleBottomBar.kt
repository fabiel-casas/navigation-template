package com.zagart.navigation.template.presentation.components.bottombar

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.zagart.navigation.template.presentation.navigation.BackStackNavController
import com.zagart.navigation.template.presentation.navigation.toIndex
import com.zagart.navigation.template.ui.ExampleBottomBarUi

@Composable
fun ExampleBottomBar(
    modifier: Modifier = Modifier,
    currentDestination: BackStackNavController,
    onBottomBarItemClick: (index: Int) -> Unit = {},
) {
    if (!currentDestination.backStack.last().isFullScreen) {
        ExampleBottomBarUi(
            modifier = modifier,
            selectedItemIndex = currentDestination.currentNavBarDestination.toIndex(),
            onItemClicked = onBottomBarItemClick
        )
    }
}