package com.zagart.navigation.template.presentation.navigation

import android.util.Log
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
            val startNavBarIndex = backStack.indexOfFirst { it::class == destination::class }
            when {
                destination is NavBarDestination && startNavBarIndex > -1 -> {
                    val endNavBarIndex = backStack.indexOfFirst {
                        it::class != destination::class && it is NavBarDestination
                    }
                    val newList = mutableListOf<Destination>()
                    val group = backStack.toList().subList(startNavBarIndex, endNavBarIndex)
                    backStack.forEachIndexed { index, destination ->
                        if (index < startNavBarIndex || index >= endNavBarIndex) {
                            newList.add(destination)
                        }
                    }
                    newList.addAll(group)
                    backStack.clear()
                    backStack.addAll(newList)
                }

                else -> {
                    backStack.add(destination)
                }
            }
            Log.i("Navigation", "BackStack Adding destination: ${backStack.toList()}")
        }
    }

    override fun popDestination() {
        backStack.removeLastOrNull()
    }
}