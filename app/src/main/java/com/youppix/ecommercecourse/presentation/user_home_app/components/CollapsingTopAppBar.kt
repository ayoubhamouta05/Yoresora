package com.youppix.ecommercecourse.presentation.user_home_app.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CollapsingTopAppBar(
    scrollBehavior: TopAppBarScrollBehavior,
    lazyListState: LazyListState,
    content : @Composable () -> Unit
) {
    var appBarHeight by remember { mutableStateOf(200.dp) }

    LaunchedEffect(lazyListState) {
        snapshotFlow { lazyListState.firstVisibleItemScrollOffset }.collect { offset ->
            appBarHeight = (200.dp - offset.dp).coerceIn(56.dp, 200.dp)
        }
    }

    TopAppBar(
        scrollBehavior = scrollBehavior,
        modifier = Modifier
            .height(appBarHeight)
            .background(MaterialTheme.colorScheme.primary),
        title = {
            content()
        }
    )
}