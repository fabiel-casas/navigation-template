package com.zagart.navigation.template.navigation

import android.os.Bundle
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.SizeTransform
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.zagart.navigation.template.presentation.navigation.Destination
import com.zagart.navigation.template.presentation.navigation.NavigationViewModel
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.reflect.KType
import kotlin.reflect.typeOf

val defaultTypeMap = mapOf(
    typeOf<Destination.Args>() to DestinationArgsNavType
)

/**
 * Wrapper around [composable]. It keeps [NavigationViewModel.currentDestinationState] updated, so
 * it always should be used instead of [composable].
 * In addition, provides [NavBackStackEntry] to [Destination] conversion.
 */
inline fun <reified T : Destination> NavGraphBuilder.screen(
    typeMap: Map<KType, @JvmSuppressWildcards NavType<*>> = defaultTypeMap,
    deepLinks: List<NavDeepLink> = emptyList(),
    noinline enterTransition:
    (AnimatedContentTransitionScope<NavBackStackEntry>.() -> @JvmSuppressWildcards
    EnterTransition?)? =
        null,
    noinline exitTransition:
    (AnimatedContentTransitionScope<NavBackStackEntry>.() -> @JvmSuppressWildcards
    ExitTransition?)? =
        null,
    noinline popEnterTransition:
    (AnimatedContentTransitionScope<NavBackStackEntry>.() -> @JvmSuppressWildcards
    EnterTransition?)? =
        enterTransition,
    noinline popExitTransition:
    (AnimatedContentTransitionScope<NavBackStackEntry>.() -> @JvmSuppressWildcards
    ExitTransition?)? =
        exitTransition,
    noinline sizeTransform:
    (AnimatedContentTransitionScope<NavBackStackEntry>.() -> @JvmSuppressWildcards
    SizeTransform?)? =
        null,
    noinline content: @Composable AnimatedContentScope.(T) -> Unit
) {
    composable<T>(
        typeMap = typeMap,
        deepLinks = deepLinks,
        enterTransition = enterTransition,
        exitTransition = exitTransition,
        popEnterTransition = popEnterTransition,
        popExitTransition = popExitTransition,
        sizeTransform = sizeTransform,
    ) { entry ->
        val destination = entry.toRoute<T>()

        content(destination)
    }
}

/**
 * Workaround, caused by [toRoute] being not able to parse nested destination objects,
 * even if those are serializable. Hopefully, it's temporary solution.
 */
object DestinationArgsNavType : NavType<Destination.Args>(isNullableAllowed = false) {

    /**
     * Not being called by [NavHost] in some cases; [NavHost] calls [Any.toString] instead.
     * Reason for [Destination.Args] workaround.
     */
    override fun put(bundle: Bundle, key: String, value: Destination.Args) {
        bundle.putString(key, Json.encodeToString(value))
    }

    override fun get(bundle: Bundle, key: String): Destination.Args? {
        return bundle.getString(key)?.let { Json.decodeFromString(it) }
    }

    override fun parseValue(value: String): Destination.Args {
        return Json.decodeFromString<Destination.Args>(value)
    }
}