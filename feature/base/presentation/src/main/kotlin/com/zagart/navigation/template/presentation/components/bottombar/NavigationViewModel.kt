package com.zagart.navigation.template.presentation.components.bottombar

import androidx.lifecycle.ViewModel
import com.zagart.navigation.template.presentation.navigation.BonusBackstack
import com.zagart.navigation.template.presentation.navigation.CookingBackstack
import com.zagart.navigation.template.presentation.navigation.HomeBackstack
import com.zagart.navigation.template.presentation.navigation.MyListBackstack
import com.zagart.navigation.template.presentation.navigation.NavigationEventDelegate
import com.zagart.navigation.template.presentation.navigation.NavigationEventDelegateImpl
import com.zagart.navigation.template.presentation.navigation.ProductsBackstack
import com.zagart.navigation.template.ui.Tab
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NavigationViewModel @Inject constructor() : ViewModel(),
    NavigationEventDelegate by NavigationEventDelegateImpl() {

    val currentDestination = navigationDestinationFlow.value

    fun onBottomBarItemClick(index: Int) {
        when (index) {
            Tab.HOME.ordinal -> sendDestination(HomeBackstack)
            Tab.BONUS.ordinal -> sendDestination(BonusBackstack)
            Tab.COOKING.ordinal -> sendDestination(CookingBackstack)
            Tab.PRODUCTS.ordinal -> sendDestination(ProductsBackstack)
            Tab.MY_LIST.ordinal -> sendDestination(MyListBackstack)
        }
    }
}