package com.youppix.ecommercecourse.presentation.home_app.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.youppix.ecommercecourse.common.Dimens.SmallPadding
import com.youppix.ecommercecourse.domain.model.items.ColorData


@Composable
fun ColorItem(
    modifier: Modifier = Modifier,
    color: ColorData,
    filteringItemsColors: List<ColorData>,
    onClick: () -> Unit
) {
    val colorConverted = Color(android.graphics.Color.parseColor(color.colors_hex))

    var selected by remember {
        mutableStateOf(filteringItemsColors.contains(color))
    }

    Box(
        modifier = modifier
            .size(40.dp)
            .clip(CircleShape)
            .clickable {
                onClick()
                selected = filteringItemsColors.contains(color)
            }
            .background(
                color = colorConverted.copy(alpha = if (selected) 0.2f else 1f),
                shape = CircleShape
            )
            .border(width = SmallPadding, color = colorConverted, shape = CircleShape)
            .padding(SmallPadding)
    )
}