package com.zagart.navigation.template.presentation.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class NavigationEventDelegateImpl : NavigationEventDelegate {

    private val _navigationFlow = MutableStateFlow<Destination?>(null)
    override val ViewModel.navigationDestinationFlow: StateFlow<Destination?>
        get() = _navigationFlow

    @Suppress("MemberVisibilityCanBePrivate")
    override fun ViewModel.sendDestination(destination: Destination) {
        viewModelScope.launch {
            val randomTime = (1000L..3000L).random()
            Log.i(
                "Navigation Event",
                "Simulating delay of $randomTime ms before navigating to ${destination::class.java.simpleName}"
            )
            delay(randomTime)
            _navigationFlow.emit(destination)
        }
    }
}

@Composable
fun ViewModel.collectNavigationEvents(screenName: String) {
    if (this !is NavigationEventDelegate) {
        throw IllegalStateException("ViewModel must implement NavigationEventDelegate to collect navigation events")
    }
    val navigationFlow = LocalNavigation.current
    LaunchedEffect(this.navigationDestinationFlow) {
        Log.i("Navigation", "Collecting navigation events for $screenName")
        navigationDestinationFlow.collectLatest { newDestination ->
            Log.i(
                "Navigation",
                "Screen: $screenName, New Destination: ${newDestination?.javaClass?.simpleName}"
            )
            newDestination?.let { destination ->
                navigationFlow.send(destination)
            }
        }
    }
}

interface NavigationEventDelegate {

    val ViewModel.navigationDestinationFlow : StateFlow<Destination?>

    fun ViewModel.sendDestination(destination: Destination)
}

fun ViewModel.onBack() {
    if (this is NavigationEventDelegate) {
        sendDestination(BackDestination)
    } else {
        Log.e("Navigation", "ViewModel must implement NavigationEventDelegate to handle back navigation")
    }
}