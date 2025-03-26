package com.youppix.ecommercecourse.presentation.admin_home_app.details.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ModeEdit
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import com.youppix.ecommercecourse.common.Dimens
import com.youppix.ecommercecourse.common.Dimens.ExtraSmallPadding2
import com.youppix.ecommercecourse.common.Dimens.MediumPadding
import com.youppix.ecommercecourse.common.Dimens.SmallPadding
import com.youppix.ecommercecourse.presentation.user_home_app.components.SizesListItem
import com.youppix.ecommercecourse.presentation.admin_home_app.details.DetailsEvent
import com.youppix.ecommercecourse.presentation.admin_home_app.details.DetailsState

@Composable
fun SelectSizeSection(state: DetailsState, event: (DetailsEvent) -> Unit , toggleSizesBottomSheet: ()-> Unit ) {

        LazyRow(
            modifier = Modifier.padding(horizontal = Dimens.MediumPadding)
        ) {
            item {
                Box(
                    modifier = Modifier
                        .padding(end = MediumPadding)
                        .clip(RoundedCornerShape(SmallPadding))
                        .clickable {
                            toggleSizesBottomSheet()
                        }
                        .background(
                            color = MaterialTheme.colorScheme.background
                        )
                        .border(
                            0.5.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(
                                SmallPadding
                            )
                        )
                ) {
                    Image(imageVector = Icons.Default.Edit, contentDescription = null,
                        modifier = Modifier.padding(horizontal = SmallPadding, vertical = ExtraSmallPadding2-0.5.dp)
                            .scale(0.8f)
                    )
                }
            }
            if (state.details.sizes.isNotEmpty()) {

            items(state.details.sizes.size, key = { it }) { index ->

                SizesListItem(
                    name = state.details.sizes[index].sizes_name,
                    id = index,
                    selected = false
                ) {

                }
            }
        }
    }
}