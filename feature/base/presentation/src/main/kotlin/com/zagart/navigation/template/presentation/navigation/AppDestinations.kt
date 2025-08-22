package com.zagart.navigation.template.presentation.navigation

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data object BackDestination : BannerDestination(), Parcelable

@Parcelize
data object BonusBoxDestination : BannerDestination(), Parcelable

@Parcelize
data class BonusGroupDestination(
    val id: String,
): BannerDestination(), Parcelable

@Parcelize
data class ProductDetailsDestination(
    val id: String,
): FullScreenDestination(), Parcelable