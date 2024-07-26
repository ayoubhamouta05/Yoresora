package com.youppix.ecommercecourse.presentation.starting_app.onBoarding.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.youppix.ecommercecourse.common.Dimens.ExtraSmallPadding2
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



@Composable
fun PageIndicator(
    modifier: Modifier = Modifier,
    pageSize: Int,
    selectedPage: Int,
    selectedColor: Color = MaterialTheme.colorScheme.primary,
    unselectedColor: Color = Color.LightGray,
    indicatorSize: Dp ,
) {

    Row(modifier = modifier, horizontalArrangement = Arrangement.SpaceBetween,) {
        repeat(if (pageSize > 5) 5 else pageSize) { index ->
            Box(
                modifier = Modifier
                    .padding(end = ExtraSmallPadding2)
                    .size(indicatorSize)
                    .background(
                        color = if (index == selectedPage) selectedColor else unselectedColor,
                        shape = RoundedCornerShape(100)
                    )
            )

        }


    }
}