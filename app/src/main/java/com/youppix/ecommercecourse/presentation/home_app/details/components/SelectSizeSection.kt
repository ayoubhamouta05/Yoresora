package com.youppix.ecommercecourse.presentation.home_app.details.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.youppix.ecommercecourse.common.Dimens
import com.youppix.ecommercecourse.presentation.home_app.components.SizesListItem
import com.youppix.ecommercecourse.presentation.home_app.details.DetailsEvent
import com.youppix.ecommercecourse.presentation.home_app.details.DetailsState

@Composable
fun SelectSizeSection(state : DetailsState, event: (DetailsEvent)-> Unit) {
    if (state.details.sizes.isNotEmpty()) {
        LazyRow(
            modifier = Modifier.padding(horizontal = Dimens.MediumPadding)
        ) {
            items(state.details.sizes.size, key = { it }) { index ->

                SizesListItem(
                    name = state.details.sizes[index].sizes_name,
                    id = index,
                    selected = index == state.selectedSize
                ) {
                    event(DetailsEvent.UpdateSizeSelected(it))
                }
            }
        }
    }
}