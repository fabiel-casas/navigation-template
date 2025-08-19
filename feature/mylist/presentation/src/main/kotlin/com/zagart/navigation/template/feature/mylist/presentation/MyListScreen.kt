package com.zagart.navigation.template.feature.mylist.presentation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.zagart.navigation.template.presentation.navigation.MyListDestination
import com.zagart.navigation.template.presentation.navigation.collectNavigationEvents
import com.zagart.navigation.template.ui.Tab
import com.zagart.navigation.template.ui.DummyScreen

@Composable
fun MyListScreen(
    destination: MyListDestination,
    modifier: Modifier = Modifier,
    viewModel: MyListViewModel = hiltViewModel(),
) {
    LaunchedEffect(destination) {
        viewModel.load(destination)
    }
    viewModel.collectNavigationEvents("MyListScreen")

    BackHandler(onBack = viewModel::onBack)
    DummyScreen(
        modifier = modifier,
        title = "My List",
    )
}