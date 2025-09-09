package com.zagart.navigation.template.feature.cooking.presentation

import androidx.lifecycle.ViewModel
import com.zagart.navigation.template.presentation.navigation.Destination
import com.zagart.navigation.template.presentation.navigation.NavigationEventDelegate
import com.zagart.navigation.template.presentation.navigation.NavigationEventDelegateImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CookingViewModel @Inject constructor() : ViewModel(),
    NavigationEventDelegate by NavigationEventDelegateImpl()  {

    fun load(destination: Destination) {
    }

    fun onBackAction() {
        onBack()
    }
}