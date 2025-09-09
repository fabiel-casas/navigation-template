package com.zagart.navigation.template.product.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zagart.navigation.template.feature.product.domain.ProductRepository
import com.zagart.navigation.template.feature.product.ui.components.ProductViewData
import com.zagart.navigation.template.feature.product.ui.details.ProductDetailsScreenState
import com.zagart.navigation.template.presentation.navigation.NavigationEventDelegate
import com.zagart.navigation.template.presentation.navigation.NavigationEventDelegateImpl
import com.zagart.navigation.template.presentation.navigation.ProductDetailsDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor() : ViewModel(),
    NavigationEventDelegate by NavigationEventDelegateImpl()  {

    private val _state = MutableStateFlow(ProductDetailsScreenState())
    val state = _state.asStateFlow()

    fun load(destination: ProductDetailsDestination) {
        viewModelScope.launch {
            val product = ProductRepository
                .getProductById(destination.id)
                .run { ProductViewData(id, title) }

            _state.update { currentState ->
                currentState.copy(
                    product = product,
                    title = "Product details",
                )
            }
        }
    }

    fun onBackAction() {
        onBack()
    }
}