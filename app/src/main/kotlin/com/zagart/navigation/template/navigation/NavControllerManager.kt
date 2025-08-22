package com.zagart.navigation.template.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.zagart.navigation.template.presentation.navigation.BottomBanner
import com.zagart.navigation.template.presentation.navigation.BonusBackstack
import com.zagart.navigation.template.presentation.navigation.CookingBackstack
import com.zagart.navigation.template.presentation.navigation.HomeBackstack
import com.zagart.navigation.template.presentation.navigation.MyListBackstack
import com.zagart.navigation.template.presentation.navigation.ProductsBackstack

@Composable
fun rememberNavControllerManager(): NavControllerManager {
    val homeNavController = rememberNavController()
    val bonusNavController = rememberNavController()
    val cookingNavController = rememberNavController()
    val productsNavController = rememberNavController()
    val myListNavController = rememberNavController()

    return remember {
        NavControllerManager(
            homeNavController = homeNavController,
            bonusNavController = bonusNavController,
            cookingNavController = cookingNavController,
            productsNavController = productsNavController,
            myListNavController = myListNavController,
        )
    }
}

class NavControllerManager(
    private val homeNavController: NavHostController,
    private val bonusNavController: NavHostController,
    private val cookingNavController: NavHostController,
    private val productsNavController: NavHostController,
    private val myListNavController: NavHostController,
) {

    fun getController(bottomBanner: BottomBanner): NavHostController {
        return when (bottomBanner) {
            is HomeBackstack -> homeNavController
            is BonusBackstack -> bonusNavController
            is CookingBackstack -> cookingNavController
            is ProductsBackstack -> productsNavController
            is MyListBackstack -> myListNavController
            else -> throw IllegalArgumentException("Unsupported backstack type: ${bottomBanner::class.simpleName}")
        }
    }
}