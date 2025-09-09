package com.zagart.navigation.template.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zagart.navigation.template.feature.bonus.domain.BonusGroupRepository
import com.zagart.navigation.template.feature.bonus.ui.bonusbox.BonusBoxBannerViewData
import com.zagart.navigation.template.feature.bonus.ui.components.models.BonusGroupViewData
import com.zagart.navigation.template.feature.home.ui.HomeItem
import com.zagart.navigation.template.feature.home.ui.HomeLane
import com.zagart.navigation.template.feature.home.ui.HomeScreenState
import com.zagart.navigation.template.feature.product.domain.ProductRepository
import com.zagart.navigation.template.feature.product.ui.components.ProductViewData
import com.zagart.navigation.template.presentation.navigation.BonusBoxDestination
import com.zagart.navigation.template.presentation.navigation.BonusGroupDestination
import com.zagart.navigation.template.presentation.navigation.NavBarDestination
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
class HomeViewModel @Inject constructor() : ViewModel(),
    NavigationEventDelegate by NavigationEventDelegateImpl()  {

    private val _state = MutableStateFlow(HomeScreenState())
    val state = _state.asStateFlow()

    fun load(destination: NavBarDestination) {
        viewModelScope.launch {
            val products = ProductRepository.getProducts().map {
                ProductViewData(it.id, it.title)
            }
            val bonusGroups = BonusGroupRepository.getBonusGroups().map {
                BonusGroupViewData(it.id, it.title)
            }

            _state.update { currentState ->
                currentState.copy(
                    lanes = arrayListOf(
                        HomeLane.HorizontalList(products.map { HomeItem.Product(it) }),
                        HomeLane.BonusBoxBanner(BonusBoxBannerViewData("Bonus Box")),
                        HomeLane.HorizontalList(bonusGroups.map { HomeItem.BonusGroup(it) }),
                        HomeLane.HorizontalList(products.map { HomeItem.Product(it) }),
                        HomeLane.HorizontalList(bonusGroups.map { HomeItem.BonusGroup(it) }),
                    )
                )
            }
        }
    }

    fun onBonusGroupClick(bonusGroup: BonusGroupViewData) {
        sendDestination(
            BonusGroupDestination(
                id = bonusGroup.id,
            )
        )
    }

     fun onBonusBoxClick() {
        sendDestination(
            BonusBoxDestination
        )
    }

    fun onProductClick(product: ProductViewData) {
        sendDestination(
            ProductDetailsDestination(
                id = product.id,
            )
        )
    }

    fun onBackAction() {
        onBack()
    }
}