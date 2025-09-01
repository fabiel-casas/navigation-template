package com.zagart.navigation.template.presentation.navigation

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data object BackDestination : NavBarDestination, Parcelable

@Parcelize
@Serializable
data object BonusBoxDestination : PanelDestination(false), Parcelable

@Parcelize
@Serializable
data class BonusGroupDestination(
    val id: String,
): PanelDestination(true), Parcelable

@Parcelize
@Serializable
data class ProductDetailsDestination(
    val id: String,
): PanelDestination(true), Parcelable