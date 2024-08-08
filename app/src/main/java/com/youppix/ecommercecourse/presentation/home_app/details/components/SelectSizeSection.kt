package com.youppix.ecommercecourse.presentation.home_app.details.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.youppix.ecommercecourse.common.Dimens
import com.youppix.ecommercecourse.presentation.home_app.components.CategoriesListItem
import com.youppix.ecommercecourse.presentation.home_app.components.SizesListItem
import com.youppix.ecommercecourse.presentation.home_app.details.DetailsEvent
import com.youppix.ecommercecourse.presentation.home_app.details.DetailsState

@Composable
fun SelectSizeSection(state : DetailsState, event: (DetailsEvent)-> Unit) {
    if (state.details.sizes_name.isNotEmpty()) {
        LazyRow(modifier = Modifier.padding(horizontal = Dimens.MediumPadding)) {
            items(state.details.sizes_name.size) { index ->
                SizesListItem(
                    name = state.details.sizes_name[index],
                    id = state.details.sizes_id[index],
                    selected = state.details.sizes_id[index] == state.selectedSize
                ) {
                    event(DetailsEvent.UpdateSizeSelected(it))
                }
            }
        }
    }
}