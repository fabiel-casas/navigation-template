package com.zagart.navigation.template.feature.bonus.presentation.bonusbox

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.zagart.navigation.template.feature.bonus.domain.BonusGroupRepository
import com.zagart.navigation.template.feature.bonus.ui.bonusbox.AdvertisementViewData
import com.zagart.navigation.template.feature.bonus.ui.bonusbox.BonusBoxScreenState
import com.zagart.navigation.template.feature.bonus.ui.components.models.BonusGroupViewData
import com.zagart.navigation.template.feature.bonus.ui.components.models.BonusItem
import com.zagart.navigation.template.feature.bonus.ui.components.models.BonusLane
import com.zagart.navigation.template.feature.product.domain.ProductRepository
import com.zagart.navigation.template.feature.product.ui.components.ProductViewData
import com.zagart.navigation.template.presentation.navigation.BonusBoxDestination
import com.zagart.navigation.template.presentation.navigation.CookingBackstack
import com.zagart.navigation.template.presentation.navigation.NavigationViewModel
import com.zagart.navigation.template.presentation.navigation.ProductsBackstack
import com.zagart.navigation.template.presentation.navigation.isScreen
import com.zagart.navigation.template.ui.Tab
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BonusBoxViewModel @Inject constructor() : NavigationViewModel() {

    private val _state = MutableStateFlow(BonusBoxScreenState())
    val state = _state.asStateFlow()

    fun load(destination: BonusBoxDestination) {
        viewModelScope.launch {
            val products = ProductRepository.getProducts().map {
                ProductViewData(it.id, it.title)
            }
            val bonusGroups = BonusGroupRepository.getBonusGroups().map {
                BonusGroupViewData(it.id, it.title)
            }

            val lanes = listOf(
                BonusLane.HorizontalList(products.map { BonusItem.Product(it) }),
                BonusLane.Advertisement(AdvertisementViewData(products[0])),
                BonusLane.HorizontalList(bonusGroups.map { BonusItem.BonusGroup(it) }),
                BonusLane.Advertisement(AdvertisementViewData(products[2])),
                BonusLane.HorizontalList(products.map { BonusItem.Product(it) }),
                BonusLane.Advertisement(AdvertisementViewData(products[4])),
                BonusLane.HorizontalList(bonusGroups.map { BonusItem.BonusGroup(it) }),
            )

            _state.update { currentState ->
                currentState.copy(
                    lanes = lanes,
                    currentTab = Tab.entries[destination.args.backstackIndex],
                    title = "Bonus Box",
                    showTopBar = destination.args.topBarScope.isScreen(),
                    showBottomBar = destination.args.bottomBarScope.isScreen(),
                )
            }
        }
    }

    override fun onProductClick(product: ProductViewData, backstackIndex: Int) {
        viewModelScope.launch {
            val randomTime = (1000L..3000L).random()
            Log.i("BonusBoxViewModel", "Simulating delay of $randomTime ms before navigating to ProductsBackstack")
            delay(randomTime) // Simulate a delay for demonstration purposes
            super.onProductClick(product, backstackIndex)
        }
    }
}