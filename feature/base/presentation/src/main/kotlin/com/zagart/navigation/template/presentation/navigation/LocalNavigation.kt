package com.zagart.navigation.template.presentation.navigation

import androidx.compose.runtime.compositionLocalOf

val LocalNavigation = compositionLocalOf<NavigationFlow> {
    throw IllegalStateException("NavigationFlow not provided")
}