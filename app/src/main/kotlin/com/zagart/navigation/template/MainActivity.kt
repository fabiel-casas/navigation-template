package com.zagart.navigation.template

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.zagart.navigation.template.navigation.deeplinks.DeeplinkConverter
import com.zagart.navigation.template.navigation.hosts.BonusNavHost
import com.zagart.navigation.template.navigation.hosts.CookingNavHost
import com.zagart.navigation.template.navigation.hosts.HomeNavHost
import com.zagart.navigation.template.navigation.hosts.MyListNavHost
import com.zagart.navigation.template.navigation.hosts.ProductsNavHost
import com.zagart.navigation.template.navigation.rememberNavControllerManager
import com.zagart.navigation.template.presentation.components.bottombar.ExampleBottomBar
import com.zagart.navigation.template.presentation.components.bottombar.NavigationViewModel
import com.zagart.navigation.template.presentation.navigation.BackDestination
import com.zagart.navigation.template.presentation.navigation.BottomBanner
import com.zagart.navigation.template.presentation.navigation.BonusBackstack
import com.zagart.navigation.template.presentation.navigation.CookingBackstack
import com.zagart.navigation.template.presentation.navigation.Destination
import com.zagart.navigation.template.presentation.navigation.HomeBackstack
import com.zagart.navigation.template.presentation.navigation.LocalNavigation
import com.zagart.navigation.template.presentation.navigation.MyListBackstack
import com.zagart.navigation.template.presentation.navigation.NavigationFlowImpl
import com.zagart.navigation.template.presentation.navigation.ProductsBackstack
import com.zagart.navigation.template.presentation.navigation.ScrollStateHolder
import com.zagart.navigation.template.ui.theme.NavigationTemplateTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            NavigationTemplateTheme {
                CompositionLocalProvider(LocalNavigation provides NavigationFlowImpl()){
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
        val scrollStateHolder = remember { ScrollStateHolder() }
        val navControllerManager = rememberNavControllerManager()

        val currentDestination = LocalNavigation.current.destinationFlow.collectAsStateWithLifecycle(
            initialValue = HomeBackstack
        ).value

//        var currentController = navControllerManager.getController(currentDestination)
//
//        LaunchedEffect(currentDestination) {
//            if (currentDestination is BottomBanner) {
//                currentBottomBanner = currentDestination
//            } else {
//                if (currentDestination.args.backstackIndex >= 0) {
//                    currentBottomBanner = BottomBanner.from(currentDestination.args.backstackIndex)
//                    currentController =
//                        navControllerManager.getController(currentBottomBanner)
//
//                    if (currentController.currentBackStackEntry == null) {
//                        // [Workaround] Giving some time to NavHost to initialize first destination
//                        delay(50)
//                    }
//                }
//                currentController.open(currentDestination)
//            }
//        }
        Scaffold(
            modifier = Modifier.safeDrawingPadding(),
            bottomBar = { ExampleBottomBar(
                modifier = Modifier,
                currentDestination = currentDestination,
                onBottomBarItemClick = viewModel::onBottomBarItemClick
            ) },
        ) { paddingValues ->
            Surface(modifier = Modifier.padding(paddingValues)) {
                when (currentDestination) {
                    is HomeBackstack -> HomeNavHost(
                        currentController,
                        scrollStateHolder
                    )
                    is BonusBackstack -> BonusNavHost(
                        currentController,
                        scrollStateHolder
                    )
                    is CookingBackstack -> CookingNavHost(currentController)
                    is ProductsBackstack -> ProductsNavHost(currentController)
                    is MyListBackstack -> MyListNavHost(currentController)
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleDeeplink(intent)
    }

    private fun NavHostController.open(destination: Destination) {
        if (destination is BackDestination) {
            if (!navigateUp()) {
                finish()
            }
        } else {
            navigate(destination)
        }
    }
}

//TODO: Reset backstack/controller on new deeplink?
private fun handleDeeplink(intent: Intent) {
    val destinations = DeeplinkConverter.apply(intent.data)
    val coroutineScope = CoroutineScope(Dispatchers.Main)

    coroutineScope.launch {
        destinations.forEach {
//            NavigationFlow.send(it)
            //TODO: Try different destination channel implementations
            delay(50) //controller can't handle destinations faster
        }
    }
}