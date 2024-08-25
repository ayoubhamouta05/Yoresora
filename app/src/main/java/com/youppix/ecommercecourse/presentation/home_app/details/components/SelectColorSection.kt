package com.youppix.ecommercecourse.presentation.home_app.details.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.youppix.ecommercecourse.common.Dimens
import com.youppix.ecommercecourse.domain.model.items.ColorData
import com.youppix.ecommercecourse.presentation.home_app.components.ColorItemWithId
import com.youppix.ecommercecourse.presentation.home_app.details.DetailsEvent
import com.youppix.ecommercecourse.presentation.home_app.details.DetailsState

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SelectColorSection(
    state: DetailsState,
    event: (DetailsEvent) -> Unit,
) {
    FlowRow(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = Dimens.MediumPadding)
            .padding(bottom = Dimens.MediumPadding)
            .animateContentSize(),
        horizontalArrangement = Arrangement.Start,
    ) {
        if (state.details.colors.isNotEmpty()) {
            repeat(state.details.colors.size) { index ->
                val color = ColorData(
                    colors_name = state.details.colors[index].colors_name,
                    colors_hex = state.details.colors[index].colors_hex,
                    colors_id = state.details.colors[index].colors_id,
                    colors_name_ar = state.details.colors[index].colors_name_ar
                )
                ColorItemWithId(
                    modifier = Modifier.padding(end = Dimens.ExtraSmallPadding),
                    color = color,
                    selected = state.selectedColor == index
                ) {
                    event(DetailsEvent.UpdateColorSelected(index))
                }
            }
        }
    }
}