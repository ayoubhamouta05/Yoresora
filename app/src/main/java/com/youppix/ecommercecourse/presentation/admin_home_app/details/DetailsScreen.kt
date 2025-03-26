package com.youppix.ecommercecourse.presentation.admin_home_app.details

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getNavigatorScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.youppix.ecommercecourse.common.Constant
import com.youppix.ecommercecourse.domain.model.categories.Category
import com.youppix.ecommercecourse.domain.model.categories.CategoryData
import com.youppix.ecommercecourse.domain.model.details.SizeData
import com.youppix.ecommercecourse.domain.model.items.ColorData
import com.youppix.ecommercecourse.domain.model.items.Item
import com.youppix.ecommercecourse.presentation.admin_home_app.details.components.BottomBarSection
import com.youppix.ecommercecourse.presentation.admin_home_app.details.components.DetailsScreenContent
import com.youppix.ecommercecourse.presentation.admin_home_app.details.components.SelectColorSection
import com.youppix.ecommercecourse.presentation.user_home_app.search.components.SelectColorSection


data class DetailsScreen(
    private val userId: String?,
    private val item: Item,
    private val colors: List<ColorData> = emptyList(),
    private val sizes: List<SizeData> = emptyList(),
    private val categories: List<Category> = emptyList(),
    private var newItem: Boolean? = null,
    private var initialColor: Int? = null,
    private val initialSize: Int? = null,
) : Screen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel: DetailsViewModel = navigator.getNavigatorScreenModel()
        val state by viewModel.state
        val context = LocalContext.current

        var showColorsBottomSheet by remember {
            mutableStateOf(false)
        }
        var showSizesBottomSheet by remember {
            mutableStateOf(false)
        }
        var showCategoriesBottomSheet by remember {
            mutableStateOf(false)
        }

        LaunchedEffect(Unit) {
            userId?.let { id ->
                viewModel.setUserId(id.toInt())
                newItem?.let {
                    viewModel.onEvent(DetailsEvent.GetItemDetails(item.itemId, id.toInt()))
                    newItem = null
                }
            } ?: run {
                val id = context.getSharedPreferences(Constant.APP_ENTRY, 0).getString("userId", "")
                if (!id.isNullOrEmpty()) {
                    viewModel.setUserId(id.toInt())
                    newItem?.let {
                        viewModel.onEvent(DetailsEvent.GetItemDetails(item.itemId, id.toInt()))
                        newItem = null
                    }
                }
            }
        }



        Scaffold(modifier = Modifier
            .fillMaxSize()
            .animateContentSize(),
            bottomBar = {
                BottomBarSection(item.itemPrice.toString()) {
                    viewModel.onEvent(DetailsEvent.SaveChanges(item))
                }
            }

        ) { innerPadding ->


            DetailsScreenContent(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .animateContentSize(),
                event = viewModel::onEvent,
                state = state,
                item = item,
                userId = state.userId ?: 0,
                toggleCategoriesBottomSheet = {
                    showCategoriesBottomSheet = !showCategoriesBottomSheet
                },
                toggleSizesBottomSheet = {
                    showSizesBottomSheet = !showSizesBottomSheet
                },
                toggleColorsBottomSheet = {
                    showColorsBottomSheet = !showColorsBottomSheet
                },
                onBackClicked = { navigator.pop() }
            )

            if (showColorsBottomSheet) {

                ModalBottomSheet(
                    onDismissRequest = { showColorsBottomSheet = false },
                    containerColor = MaterialTheme.colorScheme.background
                ) {
                    SelectColorSection(colors)
                }

            }
            if (showSizesBottomSheet) {

                ModalBottomSheet(
                    onDismissRequest = { showSizesBottomSheet = false },
                    containerColor = MaterialTheme.colorScheme.background
                ) {
                    //SelectColorSection(colors)
                }

            }
            if (showCategoriesBottomSheet) {

                ModalBottomSheet(
                    onDismissRequest = { showCategoriesBottomSheet = false },
                    containerColor = MaterialTheme.colorScheme.background
                ) {
                  //  SelectColorSection(colors)
                }

            }

        }
    }
}
