package com.zagart.navigation.template.presentation.components.bottombar

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.zagart.navigation.template.presentation.navigation.Destination
import com.zagart.navigation.template.ui.ExampleBottomBarUi

@Composable
fun ExampleBottomBar(
    modifier: Modifier = Modifier,
    currentDestination: Destination,
    selectedIndex: Int = 0,
    onBottomBarItemClick: (index: Int) -> Unit = {},
) {

    if (!currentDestination.isFullScreen) {
        ExampleBottomBarUi(
            modifier = modifier,
            selectedItemIndex = selectedIndex,
            onItemClicked = onBottomBarItemClick
        )
    }
}