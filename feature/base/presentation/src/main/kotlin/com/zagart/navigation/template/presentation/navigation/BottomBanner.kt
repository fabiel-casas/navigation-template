package com.zagart.navigation.template.presentation.navigation

import android.os.Parcelable
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

fun NavBarDestination?.toIndex(): Int {
    return when (this) {
        HomeNavBarDestination -> 0
        BonusNavBarDestination -> 1
        CookingNavBarDestination -> 2
        ProductsNavBarDestination -> 3
        MyListNavBarDestination -> 4
        else -> 0
    }
}