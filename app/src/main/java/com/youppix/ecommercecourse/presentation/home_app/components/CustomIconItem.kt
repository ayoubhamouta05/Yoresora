package com.youppix.ecommercecourse.presentation.home_app.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Badge
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.youppix.ecommercecourse.common.Dimens.ExtraSmallPadding
import com.youppix.ecommercecourse.common.Dimens.SearchBarHeight
import com.youppix.ecommercecourse.common.Dimens.SmallPadding

@Composable
fun CustomIconItem(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    hasNotification : Boolean = false,
    onCLick: () -> Unit
) {

    Box(modifier = modifier){
        Image(imageVector = imageVector, contentDescription = null,
            Modifier
                .size(SearchBarHeight)
                .clip(CircleShape)
                .clickable {
                    onCLick()
                }
                .background(
                    MaterialTheme.colorScheme.surfaceContainer,
                    shape = CircleShape
                )
                .padding(
                    SmallPadding
                ),
            colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.primary)
        )

        if (hasNotification) {
            Badge(
                modifier = Modifier

                    .size(16.dp)
                    .offset(x = (-14).dp, y = 4.dp)
                    .align(Alignment.TopEnd)
                    .padding(top = ExtraSmallPadding , start = ExtraSmallPadding)
                    .clip(CircleShape)
                    .background(Color.Red)
                    .border(1.dp, color = Color.White, shape = CircleShape)

            ) {
            }
        }
    }





}