package com.zagart.navigation.template.feature.home.ui

import androidx.compose.runtime.Immutable
import com.zagart.navigation.template.feature.bonus.ui.bonusbox.BonusBoxBannerViewData
import com.zagart.navigation.template.feature.bonus.ui.components.models.BonusGroupViewData
import com.zagart.navigation.template.feature.product.ui.components.ProductViewData

@Immutable
data class HomeScreenState(
    val lanes: List<HomeLane> = emptyList(),
)

@Immutable
sealed interface HomeLane {

    @Immutable
    data class BonusBoxBanner(val viewData: BonusBoxBannerViewData) : HomeLane

    @Immutable
    data class HorizontalList(val items: List<HomeItem>) : HomeLane
}

@Immutable
sealed interface HomeItem {

    @Immutable
    data class BonusGroup(val viewData: BonusGroupViewData) : HomeItem

    @Immutable
    data class Product(val viewData: ProductViewData) : HomeItem
}