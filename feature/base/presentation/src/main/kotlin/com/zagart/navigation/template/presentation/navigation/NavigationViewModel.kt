package com.zagart.navigation.template.presentation.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

open class NavigationViewModel : ViewModel() {

    private val _navigationFlow = MutableStateFlow<Destination?>(null)
    val navigationDestinationFlow: StateFlow<Destination?> = _navigationFlow

    open fun onBack() {
        sendDestination(BackDestination())
    }

    @Suppress("MemberVisibilityCanBePrivate")
    protected fun sendDestination(destination: Destination) {
        viewModelScope.launch { _navigationFlow.emit(destination) }
    }
}

@Composable
fun NavigationViewModel.collectNavigationEvents() {
    val navigationFlow = LocalNavigation.current
    val newDestination = navigationDestinationFlow.collectAsStateWithLifecycle(initialValue = null)
    LaunchedEffect(newDestination.value) {
        Log.i("Navigation Event", "New destination: ${newDestination.value}")
        newDestination.value?.let { destination ->
            navigationFlow.send(destination)
        }
    }
}