package com.zagart.navigation.template.feature.bonus.presentation.segment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zagart.navigation.template.feature.bonus.domain.BonusGroupRepository
import com.zagart.navigation.template.feature.bonus.ui.components.models.BonusGroupViewData
import com.zagart.navigation.template.feature.bonus.ui.segment.BonusGroupScreenState
import com.zagart.navigation.template.feature.product.ui.components.ProductViewData
import com.zagart.navigation.template.presentation.navigation.BonusGroupDestination
import com.zagart.navigation.template.presentation.navigation.Destination
import com.zagart.navigation.template.presentation.navigation.NavigationEventDelegate
import com.zagart.navigation.template.presentation.navigation.NavigationEventDelegateImpl
import com.zagart.navigation.template.presentation.navigation.ProductDetailsDestination
import com.zagart.navigation.template.presentation.navigation.isScreen
import com.zagart.navigation.template.ui.Tab
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BonusGroupViewModel @Inject constructor() : ViewModel(),
    NavigationEventDelegate by NavigationEventDelegateImpl()  {

    private val _state = MutableStateFlow(BonusGroupScreenState())
    val state = _state.asStateFlow()

    fun load(destination: BonusGroupDestination) {
        viewModelScope.launch {
            val bonusGroup = BonusGroupRepository.getBonusGroupById(destination.id)
            _state.update { currentState ->
                currentState.copy(
                    currentTab = Tab.entries[destination.args.backstackIndex],
                    bonusGroup = BonusGroupViewData(bonusGroup.id, bonusGroup.title),
                    products = bonusGroup.products.map { ProductViewData(it.id, it.title) },
                    showTopBar = destination.args.topBarScope.isScreen(),
                    showBottomBar = destination.args.bottomBarScope.isScreen(),
                )
            }
        }
    }

    fun onProductClick(product: ProductViewData) {
        sendDestination(
            ProductDetailsDestination(
                id = product.id,
                args = navigationDestinationFlow.value?.args ?: Destination.Args()
            )
        )
    }
}