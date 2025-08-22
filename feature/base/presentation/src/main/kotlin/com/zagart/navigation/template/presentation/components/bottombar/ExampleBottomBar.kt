package com.zagart.navigation.template.presentation.components.bottombar

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.zagart.navigation.template.presentation.navigation.Destination
import com.zagart.navigation.template.ui.ExampleBottomBarUi

@Composable
fun ExampleBottomBar(
    modifier: Modifier = Modifier,
    currentDestination: Destination,
    onBottomBarItemClick: (index: Int) -> Unit = {},
) {

    if (!currentDestination.isFullScreen) {
        ExampleBottomBarUi(
            modifier = modifier,
            selectedItemIndex = currentDestination.args.backstackIndex,
            onItemClicked = onBottomBarItemClick
        )
    }
}