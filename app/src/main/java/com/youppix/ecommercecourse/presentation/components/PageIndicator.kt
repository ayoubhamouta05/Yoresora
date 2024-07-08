package com.youppix.ecommercecourse.presentation.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.youppix.ecommercecourse.common.Dimens.SelectedIndicatorSize
import com.youppix.ecommercecourse.common.Dimens.UnselectedIndicatorSize

@Composable
fun PageIndicator(
    modifier: Modifier = Modifier,
    pageSize: Int,
    selectedPage: Int,
    selectedColor: Color = MaterialTheme.colorScheme.primary,
    unselectedColor: Color = Color.LightGray
) {

    Row(modifier = modifier, horizontalArrangement = Arrangement.SpaceBetween,) {
        repeat(pageSize) { index ->
            Box(
                modifier = Modifier
                    .animateContentSize()
                    .width(
                        if (index == selectedPage) SelectedIndicatorSize else UnselectedIndicatorSize,
                    ).
                        height(UnselectedIndicatorSize)
                    .background(
                        color = if (index == selectedPage) selectedColor else unselectedColor,
                        shape = RoundedCornerShape(100)
                    )
            )

        }


    }
}