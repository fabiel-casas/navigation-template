package com.zagart.navigation.template.feature.bonus.ui.segment

import com.zagart.navigation.template.feature.bonus.ui.components.models.BonusGroupViewData
import com.zagart.navigation.template.feature.product.ui.components.ProductViewData
import com.zagart.navigation.template.ui.Tab

data class BonusGroupScreenState(
    val bonusGroup: BonusGroupViewData = BonusGroupViewData(),
    val products: List<ProductViewData> = listOf(),
    val showTopBar: Boolean = true,
    val showBottomBar: Boolean = true,
)