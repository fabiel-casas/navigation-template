package com.zagart.navigation.template.presentation.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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
            Log.i("Navigation Event", "Sending destination: $destination")
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
    val newDestination = this.navigationDestinationFlow
        .collectAsStateWithLifecycle(initialValue = null)
    LaunchedEffect(newDestination.value) {
        Log.i("Navigation Event", "Screen: $screenName, New Destination: ${newDestination.value?.javaClass?.simpleName}")
        newDestination.value?.let { destination ->
            navigationFlow.send(destination)
        }
    }
}

interface NavigationEventDelegate {

    val ViewModel.navigationDestinationFlow : StateFlow<Destination?>

    fun ViewModel.sendDestination(destination: Destination)
}

fun ViewModel.onBack() {
    if (this is NavigationEventDelegate) {
        sendDestination(BackDestination())
    } else {
        Log.e("Navigation", "ViewModel must implement NavigationEventDelegate to handle back navigation")
    }
}