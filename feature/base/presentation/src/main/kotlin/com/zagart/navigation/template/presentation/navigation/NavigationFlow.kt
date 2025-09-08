package com.zagart.navigation.template.presentation.navigation

import android.util.Log
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow

interface NavigationFlow {

    val destinationFlow: Flow<Destination>

    suspend fun send(destination: Destination)
}

class NavigationFlowImpl : NavigationFlow {

    private val _channel = Channel<Destination>()
    override val destinationFlow = _channel.receiveAsFlow()

    override suspend fun send(destination: Destination) {
        Log.i("Navigation", "Navigation Flow New Sending destination: $destination")
        _channel.send(destination)
    }
}