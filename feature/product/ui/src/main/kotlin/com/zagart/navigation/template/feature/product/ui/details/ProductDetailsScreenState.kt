package com.zagart.navigation.template.feature.product.ui.details

import androidx.compose.runtime.Immutable
import com.zagart.navigation.template.feature.product.ui.components.ProductViewData
import com.zagart.navigation.template.ui.Tab

@Immutable
data class ProductDetailsScreenState(
    val product: ProductViewData = ProductViewData(),
    val title: String = "",
)
