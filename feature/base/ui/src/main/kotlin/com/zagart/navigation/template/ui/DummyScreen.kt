package com.zagart.navigation.template.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun DummyScreen(
    title: String,
    modifier: Modifier = Modifier,
    content: String = "Some fancy description",
) {
    Scaffold(
        modifier = modifier,
        topBar = { ExampleTopBarUi(title) },
    ) { padding ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(padding)
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = content,
                fontSize = 28.sp,
            )
        }
    }
}