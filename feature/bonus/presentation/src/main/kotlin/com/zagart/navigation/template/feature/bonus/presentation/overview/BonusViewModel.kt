package com.zagart.navigation.template.feature.bonus.presentation.overview

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.zagart.navigation.template.feature.bonus.domain.BonusGroupRepository
import com.zagart.navigation.template.feature.bonus.ui.bonusbox.AdvertisementViewData
import com.zagart.navigation.template.feature.bonus.ui.bonusbox.BonusBoxBannerViewData
import com.zagart.navigation.template.feature.bonus.ui.components.models.BonusGroupViewData
import com.zagart.navigation.template.feature.bonus.ui.components.models.BonusItem
import com.zagart.navigation.template.feature.bonus.ui.components.models.BonusLane
import com.zagart.navigation.template.feature.bonus.ui.overview.BonusScreenState
import com.zagart.navigation.template.feature.product.domain.ProductDomainData
import com.zagart.navigation.template.feature.product.domain.ProductRepository
import com.zagart.navigation.template.feature.product.ui.components.ProductViewData
import com.zagart.navigation.template.presentation.navigation.BonusBackstack
import com.zagart.navigation.template.presentation.navigation.BonusBoxDestination
import com.zagart.navigation.template.presentation.navigation.BonusGroupDestination
import com.zagart.navigation.template.presentation.navigation.CookingBackstack
import com.zagart.navigation.template.presentation.navigation.Destination
import com.zagart.navigation.template.presentation.navigation.HomeBackstack
import com.zagart.navigation.template.presentation.navigation.NavigationViewModel
import com.zagart.navigation.template.presentation.navigation.ProductDetailsDestination
import com.zagart.navigation.template.presentation.navigation.ProductsBackstack
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BonusViewModel @Inject constructor() : NavigationViewModel() {

    private val _state = MutableStateFlow(BonusScreenState())
    val state = _state.asStateFlow()

    fun load(destination: Destination) {
        viewModelScope.launch {
            val products = ProductRepository.getProducts().map {
                ProductViewData(it.id, it.title)
            }
            val bonusGroups = BonusGroupRepository.getBonusGroups().map {
                BonusGroupViewData(it.id, it.title)
            }

            _state.update { currentState ->
                currentState.copy(
                    title = "Bonus",
                    lanes = listOf(
                        BonusLane.BonusBoxBanner(BonusBoxBannerViewData("Bonus Box")),
                        BonusLane.HorizontalList(products.map { BonusItem.Product(it) }),
                        BonusLane.Advertisement(AdvertisementViewData(products[1])),
                        BonusLane.HorizontalList(bonusGroups.map { BonusItem.BonusGroup(it) }),
                        BonusLane.Advertisement(AdvertisementViewData(products[3])),
                    )
                )
            }
        }
    }

    fun onBonusBoxClick(backstackIndex: Int) {
        sendDestination(
            BonusBoxDestination(
                args = Destination.Args(backstackIndex)
            )
        )
    }

    fun onBonusGroupClick(bonusGroup: BonusGroupViewData, backstackIndex: Int) {
        sendDestination(
            BonusGroupDestination(
                id = bonusGroup.id,
                args = Destination.Args(backstackIndex)
            )
        )
    }

    fun onProductClick(product: ProductViewData, backstackIndex: Int) {
        viewModelScope.launch {
            val randomTime = (1000L..3000L).random()
            Log.i("BonusViewModel", "Simulating delay of $randomTime ms before navigating to ProductsBackstack")
            delay(randomTime)
            sendDestination(
                ProductDetailsDestination(
                    id = product.id,
                    args = Destination.Args(backstackIndex)
                )
            )
        }
    }
}