package com.zagart.navigation.template.feature.bonus.ui.bonusbox

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.zagart.navigation.template.feature.bonus.ui.components.BonusLanesContent
import com.zagart.navigation.template.ui.ExampleScaffold
import com.zagart.navigation.template.ui.ExampleTopBarUi

@Composable
fun BonusBoxScreenUi(
    state: BonusBoxScreenState,
    modifier: Modifier = Modifier,
    listState: LazyListState = rememberLazyListState(),
    actions: BonusBoxScreenActions = BonusBoxScreenActions(),
) {
    ExampleScaffold(
        modifier = modifier,
        topBar = {
            ExampleTopBarUi(
                title = state.title,
                onBack = actions.onBack,
            )
        },
        content = { padding ->
            BonusLanesContent(
                modifier = Modifier.padding(padding),
                actions = actions.bonusLanesActions,
                listState = listState,
                lanes = state.lanes,
            )
        },
    )
}