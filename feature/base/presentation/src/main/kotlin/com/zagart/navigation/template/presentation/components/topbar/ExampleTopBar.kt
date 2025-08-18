package com.zagart.navigation.template.presentation.components.topbar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zagart.navigation.template.presentation.navigation.HomeBackstack
import com.zagart.navigation.template.presentation.navigation.LocalNavigation
import com.zagart.navigation.template.presentation.navigation.NavigationViewModel
import com.zagart.navigation.template.presentation.navigation.isApplication
import com.zagart.navigation.template.ui.ExampleTopBarUi

@Composable
fun ExampleTopBar(
    modifier: Modifier = Modifier,
) {
    val currentDestination by LocalNavigation.current
        .destinationFlow
        .collectAsStateWithLifecycle(HomeBackstack())

    if (currentDestination.args.topBarScope.isApplication()) {
        ExampleTopBarUi(
            modifier = modifier,
            title = currentDestination::class.simpleName ?: "Appie",
        )
    }
}