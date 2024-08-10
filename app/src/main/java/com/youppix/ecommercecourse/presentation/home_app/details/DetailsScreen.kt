package com.youppix.ecommercecourse.presentation.home_app.details

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getNavigatorScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.youppix.ecommercecourse.domain.model.items.Item
import com.youppix.ecommercecourse.presentation.home_app.details.components.BottomBarSection
import com.youppix.ecommercecourse.presentation.home_app.details.components.DetailsScreenContent
import com.youppix.ecommercecourse.presentation.home_app.details.customSize.CustomSizeScreen

data class DetailsScreen(private val userId : String ? , private val item: Item ,private var newItem: Boolean? = null) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel: DetailsViewModel = navigator.getNavigatorScreenModel()
        val state by viewModel.state

        LaunchedEffect(Unit) {
            userId?.let {id ->
                viewModel.setUserId(id.toInt())
                newItem?.let {
                    viewModel.onEvent(DetailsEvent.GetItemDetails(item.itemId, item.itemCat ,id.toInt() ))
                    newItem = null
                }
            }


        }


        Scaffold(modifier = Modifier
            .fillMaxSize()
            .animateContentSize(),
            bottomBar = {
                BottomBarSection(item.itemPrice.toString())
            }

        ) { innerPadding ->


            DetailsScreenContent(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .animateContentSize(),
                event = viewModel::onEvent,
                state = state ,
                item = item,
                userId = state.userId ?: 0,
                makeCustomSize = {navigator.push(CustomSizeScreen(state.userId))},
                onBackClicked = {navigator.pop()}
            )
        }
    }
}
