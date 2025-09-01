package com.zagart.navigation.template.presentation.components.bottombar

import androidx.lifecycle.ViewModel
import com.zagart.navigation.template.presentation.navigation.BonusNavBarDestination
import com.zagart.navigation.template.presentation.navigation.CookingNavBarDestination
import com.zagart.navigation.template.presentation.navigation.HomeNavBarDestination
import com.zagart.navigation.template.presentation.navigation.MyListNavBarDestination
import com.zagart.navigation.template.presentation.navigation.NavigationEventDelegate
import com.zagart.navigation.template.presentation.navigation.NavigationEventDelegateImpl
import com.zagart.navigation.template.presentation.navigation.ProductsNavBarDestination
import com.zagart.navigation.template.ui.Tab
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NavigationViewModel @Inject constructor() : ViewModel(),
    NavigationEventDelegate by NavigationEventDelegateImpl() {

    val currentDestination = navigationDestinationFlow.value

    fun onBottomBarItemClick(index: Int) {
        when (index) {
            Tab.HOME.ordinal -> sendDestination(HomeNavBarDestination)
            Tab.BONUS.ordinal -> sendDestination(BonusNavBarDestination)
            Tab.COOKING.ordinal -> sendDestination(CookingNavBarDestination)
            Tab.PRODUCTS.ordinal -> sendDestination(ProductsNavBarDestination)
            Tab.MY_LIST.ordinal -> sendDestination(MyListNavBarDestination)
        }
    }
}