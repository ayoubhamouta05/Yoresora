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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Badge
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.youppix.ecommercecourse.common.Dimens.ExtraSmallPadding
import com.youppix.ecommercecourse.common.Dimens.ExtraSmallPadding2
import com.youppix.ecommercecourse.common.Dimens.SearchBarHeight
import com.youppix.ecommercecourse.common.Dimens.SmallPadding
import com.youppix.ecommercecourse.common.Dimens.SocialMediaItemSize

@Stable
@Composable
fun CustomIconItem(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    hasNotification: Boolean = false,
    onCLick: () -> Unit
) {

    Box(modifier = modifier) {
        Image(imageVector = imageVector, contentDescription = null,
            Modifier
                .size(SearchBarHeight)
                .clip(CircleShape)
                .clickable {
                    onCLick()
                }
                .background(
                    MaterialTheme.colorScheme.primary,
                    shape = CircleShape
                )
                .padding(
                    SmallPadding.plus(ExtraSmallPadding2)
                ),
            colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.background)
        )

        if (hasNotification) {
            Badge(
                modifier = Modifier
                    .size(17.5.dp.minus(ExtraSmallPadding2))
                    .offset(x = (-17).dp, y = 9.dp)
                    .align(Alignment.TopEnd)
                    .padding(top = ExtraSmallPadding, start = ExtraSmallPadding)
                    .clip(CircleShape)
                    .background(Color.Red)
                    .border(1.dp, color = Color.White, shape = CircleShape)

            ) {
            }
        }
    }


}

@Stable
@Composable
fun CustomIconItem(
    modifier: Modifier = Modifier,
    painter: Painter,
    onCLick: () -> Unit
) {

    Box(modifier = modifier) {
        Image(painter = painter, contentDescription = null,
            Modifier
                .size(SearchBarHeight)
                .clip(CircleShape)
                .clickable {
                    onCLick()
                }
                .background(
                    MaterialTheme.colorScheme.primary,
                    shape = CircleShape
                )
                .padding(
                    SmallPadding.plus(ExtraSmallPadding2)
                ),
            colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.background)
        )
    }

}


@Stable
@Composable
fun CustomIcon(
    modifier: Modifier = Modifier,
    backgroundColor : Color= MaterialTheme.colorScheme.background,
    iconColor : Color= MaterialTheme.colorScheme.onBackground,
    imageVector: ImageVector,
    onCLick: () -> Unit
) {

    Card(modifier = modifier ,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        shape = CircleShape) {
        Image(imageVector = imageVector, contentDescription = null,
            Modifier
                .size(SocialMediaItemSize)
                .clip(CircleShape)
                .clickable {
                    onCLick()
                }
                .background(
                    backgroundColor,
                    shape = CircleShape
                )
                .padding(
                    SmallPadding
                ),
            colorFilter = ColorFilter.tint(color = iconColor)
        )
    }

}


@Stable
@Composable
fun CustomIcon(
    modifier: Modifier = Modifier,
    backgroundColor : Color= MaterialTheme.colorScheme.background,
    iconColor : Color= MaterialTheme.colorScheme.onBackground,
    imagePainter: Painter,
    onCLick: () -> Unit
) {

    Card(modifier = modifier ,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        shape = CircleShape) {
        Image(painter = imagePainter, contentDescription = null,
            Modifier
                .size(SocialMediaItemSize)
                .clip(CircleShape)
                .clickable {
                    onCLick()
                }
                .background(
                    backgroundColor,
                    shape = CircleShape
                )
                .padding(
                    SmallPadding
                ),
            colorFilter = ColorFilter.tint(color = iconColor)
        )
    }

}