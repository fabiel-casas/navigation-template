package com.zagart.navigation.template.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import kotlinx.coroutines.flow.collectLatest

@Composable
fun rememberBackStackNavController(initialDestination: Destination): SnapshotStateList<Destination> {
    val backStack = remember { mutableStateListOf<Destination>(initialDestination) }
    val navigationFlow = LocalNavigation.current
        .destinationFlow
    LaunchedEffect(navigationFlow) {
        navigationFlow.collectLatest { newDestination ->
            when (newDestination) {
                BackDestination -> backStack.removeLastOrNull()
                else -> {
                    backStack.add(newDestination)
                }
            }
        }
    }
    return backStack
}