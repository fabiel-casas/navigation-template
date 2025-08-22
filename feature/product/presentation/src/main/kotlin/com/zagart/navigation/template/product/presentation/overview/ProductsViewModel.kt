package com.zagart.navigation.template.product.presentation.overview

import androidx.lifecycle.ViewModel
import com.zagart.navigation.template.presentation.navigation.NavigationEventDelegate
import com.zagart.navigation.template.presentation.navigation.NavigationEventDelegateImpl
import com.zagart.navigation.template.presentation.navigation.ProductsDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor() : ViewModel(),
    NavigationEventDelegate by NavigationEventDelegateImpl()  {

    fun load(destination: ProductsDestination) {
    }
}