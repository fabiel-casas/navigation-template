package com.zagart.navigation.template.presentation.navigation

import android.os.Parcelable
import com.zagart.navigation.template.ui.Tab
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data object BonusNavBarDestination : NavBarDestination, Parcelable

@Parcelize
@Serializable
data object CookingNavBarDestination : NavBarDestination, Parcelable

@Parcelize
@Serializable
data object HomeNavBarDestination : NavBarDestination, Parcelable

@Parcelize
@Serializable
data object MyListNavBarDestination : NavBarDestination, Parcelable

@Parcelize
@Serializable
data object ProductsNavBarDestination : NavBarDestination, Parcelable

fun NavBarDestination.from(tabIndex: Int): NavBarDestination {
    return when (tabIndex) {
        Tab.HOME.ordinal -> HomeNavBarDestination
        Tab.BONUS.ordinal -> BonusNavBarDestination
        Tab.COOKING.ordinal -> CookingNavBarDestination
        Tab.PRODUCTS.ordinal -> ProductsNavBarDestination
        Tab.MY_LIST.ordinal -> MyListNavBarDestination
        else -> throw IllegalArgumentException("Backstack with index $tabIndex does not exist")
    }
}