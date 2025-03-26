package com.youppix.ecommercecourse.presentation.admin_home_app.details.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import com.youppix.ecommercecourse.common.Dimens
import com.youppix.ecommercecourse.common.Dimens.ExtraSmallPadding
import com.youppix.ecommercecourse.common.Dimens.ExtraSmallPadding2
import com.youppix.ecommercecourse.common.Dimens.MediumPadding
import com.youppix.ecommercecourse.common.Dimens.SmallPadding
import com.youppix.ecommercecourse.common.Dimens.SocialMediaItemSize
import com.youppix.ecommercecourse.domain.model.items.ColorData
import com.youppix.ecommercecourse.presentation.admin_home_app.details.DetailsEvent
import com.youppix.ecommercecourse.presentation.admin_home_app.details.DetailsState
import com.youppix.ecommercecourse.presentation.user_home_app.address.AddressEvent
import com.youppix.ecommercecourse.presentation.user_home_app.components.ColorItemWithId
import com.youppix.ecommercecourse.presentation.user_home_app.components.CustomIcon

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SelectColorSection(
    state: DetailsState,
    event: (DetailsEvent) -> Unit ,
    toggleBottomSheet: () -> Unit
) {
    FlowRow(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = Dimens.MediumPadding)
            .padding(bottom = Dimens.MediumPadding)
            .animateContentSize(),
        horizontalArrangement = Arrangement.Start,
    ) {
        Box(
            modifier = Modifier
                .padding(end = SmallPadding + ExtraSmallPadding2)
                .size(30.dp)
                .clip(CircleShape)
                .clickable {
                    toggleBottomSheet()
                }
                .border(
                    width = 0.3.dp, color = MaterialTheme.colorScheme.onBackground,
                    shape = CircleShape
                )
        ) {
            Image(
                imageVector = Icons.Default.Edit, contentDescription = null,
                Modifier
                    .background(
                        color = Color.Transparent,
                        shape = CircleShape
                    )
                    .align(Alignment.Center)
                    .scale(0.7f),
                colorFilter = ColorFilter.tint(color = Color.Black)
            )
        }

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
                    selected = false
                ) {

                }
            }
        }
    }
}