package com.youppix.ecommercecourse.presentation.home_app.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.youppix.ecommercecourse.common.Dimens.ExtraSmallPadding
import com.youppix.ecommercecourse.common.Dimens.ExtraSmallPadding2
import com.youppix.ecommercecourse.common.Dimens.SmallPadding
import com.youppix.ecommercecourse.domain.model.items.ColorData


@Stable
@Composable
fun ColorItem(
    modifier: Modifier = Modifier,
    color: ColorData,
    colorsList: List<ColorData>,
    onClick: () -> Unit
) {
    val colorConverted = Color(android.graphics.Color.parseColor(color.colors_hex))

    var selected by remember {
        mutableStateOf(colorsList.contains(color))
    }

    Box(
        modifier = modifier
            .size(40.dp)
            .clip(CircleShape)
            .clickable {
                onClick()
                selected = colorsList.contains(color)
            }
            .background(
                color = colorConverted.copy(alpha = if (selected) 0.2f else 1f),
                shape = CircleShape
            )
            .border(width = SmallPadding, color = colorConverted, shape = CircleShape)

    )
}

@Stable
@Composable
fun ColorItemWithId(
    modifier: Modifier = Modifier,
    color: ColorData,
    selected : Boolean,
    onClick: () -> Unit
) {
    val colorConverted = Color(android.graphics.Color.parseColor(color.colors_hex))

    Box(
        modifier = modifier
            .padding(end = ExtraSmallPadding)
            .size(40.dp)
            .clip(CircleShape)
            .clickable {
                onClick()
            }
            .background(
                color = colorConverted.copy(alpha = if (selected) 0.2f else 1f),
                shape = CircleShape
            )
            .border(width = SmallPadding, color = colorConverted, shape = CircleShape)

    )
}