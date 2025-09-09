package com.zagart.navigation.template.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import kotlinx.coroutines.flow.collectLatest

@Composable
fun rememberBackStackNavController(initialDestination: Destination): BackStackNavController {
    val backStackNackController = remember {
        BackStackNavControllerImpl(initialDestination)
    }
    val navigationFlow = LocalNavigation.current
        .destinationFlow
    LaunchedEffect(navigationFlow) {
        navigationFlow.collectLatest { newDestination ->
            when (newDestination) {
                BackDestination -> backStackNackController.popDestination()
                else -> {
                    backStackNackController.addDestination(newDestination)
                }
            }
        }
    }
    return backStackNackController
}

interface BackStackNavController {
    val backStack: SnapshotStateList<Destination>
    val currentNavBarDestination: NavBarDestination?

    fun popDestination()
}

class BackStackNavControllerImpl(
    initialDestination: Destination
) : BackStackNavController {
    override val backStack = mutableStateListOf<Destination>(initialDestination)
    override val currentNavBarDestination: NavBarDestination?
        get() = backStack.lastOrNull { it is NavBarDestination } as? NavBarDestination

    fun addDestination(destination: Destination) {
        if (destination != currentNavBarDestination) {
            backStack.add(destination)
        }
    }

    override fun popDestination() {
        backStack.removeLastOrNull()
    }
}