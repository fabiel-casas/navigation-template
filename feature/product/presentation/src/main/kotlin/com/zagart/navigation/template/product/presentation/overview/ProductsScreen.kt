package com.zagart.navigation.template.product.presentation.overview

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.zagart.navigation.template.presentation.navigation.ProductsNavBarDestination
import com.zagart.navigation.template.presentation.navigation.collectNavigationEvents
import com.zagart.navigation.template.presentation.navigation.onBack
import com.zagart.navigation.template.ui.DummyScreen

@Composable
fun ProductsScreen(
    destination: ProductsNavBarDestination,
    modifier: Modifier = Modifier,
    viewModel: ProductsViewModel = hiltViewModel(),
) {
    LaunchedEffect(destination) {
        viewModel.load(destination)
    }
    viewModel.collectNavigationEvents("ProductsScreen")

    BackHandler(onBack = {
        viewModel.onBack()
    })
    DummyScreen(
        modifier = modifier,
        title = "Products",
    )
}