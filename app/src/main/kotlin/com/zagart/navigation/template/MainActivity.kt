package com.zagart.navigation.template

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator
import com.zagart.navigation.template.feature.bonus.presentation.bonusbox.BonusBoxScreen
import com.zagart.navigation.template.feature.bonus.presentation.overview.BonusScreen
import com.zagart.navigation.template.feature.bonus.presentation.segment.BonusGroupScreen
import com.zagart.navigation.template.feature.cooking.presentation.CookingScreen
import com.zagart.navigation.template.feature.mylist.presentation.MyListScreen
import com.zagart.navigation.template.presentation.HomeScreen
import com.zagart.navigation.template.presentation.components.bottombar.ExampleBottomBar
import com.zagart.navigation.template.presentation.components.bottombar.NavigationViewModel
import com.zagart.navigation.template.presentation.navigation.BonusBoxDestination
import com.zagart.navigation.template.presentation.navigation.BonusGroupDestination
import com.zagart.navigation.template.presentation.navigation.BonusNavBarDestination
import com.zagart.navigation.template.presentation.navigation.CookingNavBarDestination
import com.zagart.navigation.template.presentation.navigation.HomeNavBarDestination
import com.zagart.navigation.template.presentation.navigation.LocalNavigation
import com.zagart.navigation.template.presentation.navigation.MyListNavBarDestination
import com.zagart.navigation.template.presentation.navigation.NavigationFlowImpl
import com.zagart.navigation.template.presentation.navigation.ProductDetailsDestination
import com.zagart.navigation.template.presentation.navigation.ProductsNavBarDestination
import com.zagart.navigation.template.product.presentation.details.ProductDetailsScreen
import com.zagart.navigation.template.product.presentation.overview.ProductsScreen
import com.zagart.navigation.template.ui.theme.NavigationTemplateTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            NavigationTemplateTheme {
                CompositionLocalProvider(LocalNavigation provides NavigationFlowImpl()) {
                    NavTemplate()
                }
            }
        }
        handleDeeplink(intent)
    }

    @Composable
    private fun NavTemplate(
        viewModel: NavigationViewModel = hiltViewModel(),
    ) {

        val currentDestination =
            LocalNavigation.current.destinationFlow.collectAsStateWithLifecycle(
                initialValue = HomeNavBarDestination
            ).value
        Scaffold(
            modifier = Modifier.safeDrawingPadding(),
            bottomBar = {
                ExampleBottomBar(
                    modifier = Modifier,
                    currentDestination = currentDestination,
                    onBottomBarItemClick = viewModel::onBottomBarItemClick
                )
            },
        ) { paddingValues ->
            val backStack = remember { mutableStateListOf<Any>(HomeNavBarDestination) }

            NavDisplay(
                modifier = Modifier.padding(paddingValues),
                backStack = backStack,
                onBack = { backStack.removeLastOrNull() },

                // In order to add the `ViewModelStoreNavEntryDecorator` (see comment below for why)
                // we also need to add the default `NavEntryDecorator`s as well. These provide
                // extra information to the entry's content to enable it to display correctly
                // and save its state.
                entryDecorators = listOf(
                    rememberSceneSetupNavEntryDecorator(),
                    rememberSavedStateNavEntryDecorator(),
                    rememberViewModelStoreNavEntryDecorator()
                ),
                entryProvider = entryProvider {
                    // Bottom Bar Destinations
                    entry<HomeNavBarDestination> { HomeScreen(it) }
                    entry<BonusNavBarDestination> { BonusScreen(it) }
                    entry<MyListNavBarDestination> { MyListScreen(it) }
                    entry<CookingNavBarDestination> { CookingScreen(it) }
                    entry<ProductsNavBarDestination> { ProductsScreen(it) }
                    // Other Destinations
                    entry<BonusBoxDestination> { BonusBoxScreen(it) }
                    entry<BonusGroupDestination> { BonusGroupScreen(it) }
                    entry<ProductDetailsDestination> { ProductDetailsScreen(it) }
                }
            )
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleDeeplink(intent)
    }
}

//TODO: Reset backstack/controller on new deeplink?
private fun handleDeeplink(intent: Intent) {
//    val destinations = DeeplinkConverter.apply(intent.data)
//    val coroutineScope = CoroutineScope(Dispatchers.Main)
//
//    coroutineScope.launch {
//        destinations.forEach {
////            NavigationFlow.send(it)
//            //TODO: Try different destination channel implementations
//            delay(50) //controller can't handle destinations faster
//        }
//    }
}