package com.zagart.navigation.template.feature.home.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zagart.navigation.template.feature.bonus.ui.components.BonusBoxBannerCard
import com.zagart.navigation.template.feature.bonus.ui.components.BonusGroupCard
import com.zagart.navigation.template.feature.product.ui.components.ProductCard
import com.zagart.navigation.template.ui.ExampleScaffold
import com.zagart.navigation.template.ui.ExampleTopBarUi

@Composable
fun HomeScreenUi(
    state: HomeScreenState,
    modifier: Modifier = Modifier,
    actions: HomeScreenActions = HomeScreenActions(),
) {
    ExampleScaffold(
        modifier = modifier,
        topBar = {
            ExampleTopBarUi("Home")
        },
        content = { padding ->
            LazyColumn(
                modifier = Modifier.padding(padding),
            ) {
                item { Spacer(modifier = Modifier.size(8.dp)) }
                items(state.lanes) { lane ->
                    when (lane) {
                        is HomeLane.BonusBoxBanner -> BonusBoxBannerCard(
                            viewData = lane.viewData,
                            onClick = actions.onBonusBoxBannerClick
                        )

                        is HomeLane.HorizontalList -> LazyRow(
                            contentPadding = PaddingValues(
                                horizontal = 8.dp
                            ),
                        ) {
                            items(lane.items) { item ->
                                Row {
                                    when (item) {
                                        is HomeItem.BonusGroup -> BonusGroupCard(
                                            viewData = item.viewData,
                                            onClick = actions.onBonusGroupClick
                                        )

                                        is HomeItem.Product -> ProductCard(
                                            viewData = item.viewData,
                                            onClick = actions.onProductClick,
                                        )
                                    }
                                    Spacer(Modifier.size(8.dp))
                                }
                            }
                        }
                    }
                    Spacer(Modifier.size(8.dp))
                }
            }
        }
    )
}