package com.zagart.navigation.template.presentation.navigation

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Parcelize
@Serializable
sealed interface Destination : Parcelable {
    val isFullScreen: Boolean
    val args: Args
    val deepLinks: List<String>
}

sealed class BannerDestination(
    override val isFullScreen: Boolean = false,
    override val args: Args = Args(),
    override val deepLinks: List<String> = emptyList(),
) : Destination

sealed class FullScreenDestination(
    override val isFullScreen: Boolean = true,
    override val args: Args = Args(),
    override val deepLinks: List<String> = emptyList(),
) : Destination

@Parcelize
@Serializable
data class Args(
    val bundleInfo: String? = null, // TODO improve this to support complex data types
    val timestamp: Long = System.currentTimeMillis(),
) : Parcelable {

    // [Workaround] Navigation library does not parse custom NavTypes correctly
    override fun toString(): String {
        return Json.encodeToString(this)
    }
}