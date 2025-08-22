package com.zagart.navigation.template.presentation.navigation

import android.os.Parcelable
import com.zagart.navigation.template.ui.Tab
import kotlinx.parcelize.Parcelize

@Parcelize
data object BonusBackstack : BannerDestination(), Parcelable

@Parcelize
data object CookingBackstack : BannerDestination(), Parcelable

@Parcelize
data object HomeBackstack : BannerDestination(), Parcelable

@Parcelize
data object MyListBackstack : BannerDestination(), Parcelable

@Parcelize
data object ProductsBackstack : BannerDestination(), Parcelable

fun BannerDestination.from(tabIndex: Int): BannerDestination {
    return when (tabIndex) {
        Tab.HOME.ordinal -> HomeBackstack
        Tab.BONUS.ordinal -> BonusBackstack
        Tab.COOKING.ordinal -> CookingBackstack
        Tab.PRODUCTS.ordinal -> ProductsBackstack
        Tab.MY_LIST.ordinal -> MyListBackstack
        else -> throw IllegalArgumentException("Backstack with index $tabIndex does not exist")
    }
}