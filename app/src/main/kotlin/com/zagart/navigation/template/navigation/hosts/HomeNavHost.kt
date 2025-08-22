package com.zagart.navigation.template.navigation.hosts

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.zagart.navigation.template.feature.bonus.presentation.bonusbox.BonusBoxScreen
import com.zagart.navigation.template.feature.bonus.presentation.segment.BonusGroupScreen
import com.zagart.navigation.template.navigation.defaultTypeMap
import com.zagart.navigation.template.navigation.screen
import com.zagart.navigation.template.presentation.HomeScreen
import com.zagart.navigation.template.presentation.navigation.BonusBoxDestination
import com.zagart.navigation.template.presentation.navigation.BonusGroupDestination
import com.zagart.navigation.template.presentation.navigation.BannerDestination
import com.zagart.navigation.template.presentation.navigation.ProductDetailsDestination
import com.zagart.navigation.template.presentation.navigation.ScrollStateHolder
import com.zagart.navigation.template.product.presentation.details.ProductDetailsScreen

@Composable
fun HomeNavHost(
    navController: NavHostController,
    scrollStateHolder: ScrollStateHolder,
) {
    NavHost(
        navController = navController,
        startDestination = BannerDestination::class,
        typeMap = defaultTypeMap,
        enterTransition = {
            slideInHorizontally(initialOffsetX = { -it })
        },
        exitTransition = {
            slideOutHorizontally(targetOffsetX = { -it })
        },
    ) {
        screen<BannerDestination> { HomeScreen(it) }
        screen<BonusBoxDestination> { BonusBoxScreen(it, scrollStateHolder) }
        screen<BonusGroupDestination> { BonusGroupScreen(it) }
        screen<ProductDetailsDestination> { ProductDetailsScreen(it) }
    }
}