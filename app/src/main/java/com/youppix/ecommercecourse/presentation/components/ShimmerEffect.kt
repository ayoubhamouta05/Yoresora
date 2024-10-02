package com.youppix.ecommercecourse.presentation.components

import android.annotation.SuppressLint
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.youppix.ecommercecourse.R
import com.youppix.ecommercecourse.common.Dimens
import com.youppix.ecommercecourse.common.Dimens.LargePadding
import com.youppix.ecommercecourse.common.Dimens.MediumPadding
import com.youppix.ecommercecourse.common.Dimens.SmallPadding
import com.youppix.ecommercecourse.common.Dimens.SocialMediaItemSize

@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.shimmerEffect() = composed {
    val transition = rememberInfiniteTransition(label = "")
    val alpha = transition.animateFloat(
        initialValue = 0.2f,
        targetValue = 0.9f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    ).value
    background(color = colorResource(id = R.color.shimmer).copy(alpha = alpha))

}

@Composable
fun ShimmerEffect(
    modifier: Modifier = Modifier
) {
    val transition = rememberInfiniteTransition(label = "")
    val alpha by transition.animateFloat(
        initialValue = 0.3f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )
    Box(
        modifier = modifier
            .background(color = Color.Gray.copy(alpha = alpha))
    )
}


@Composable
fun FlashSaleItemShimmerEffect() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = MediumPadding, end = MediumPadding, bottom = SmallPadding),
        shape = RoundedCornerShape(26.dp),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 4.dp,
        ),
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.background,
        )
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Card(
                modifier = Modifier
                    .width(150.dp)
                    .defaultMinSize(minHeight = 170.dp)
                    .shimmerEffect(),
                shape = RoundedCornerShape(
                    topEnd = 0.dp,
                ),
                elevation = CardDefaults.elevatedCardElevation(
                    defaultElevation = 4.dp
                )
            ) {

                ShimmerEffect(
                    modifier = Modifier.width(150.dp)
                        .defaultMinSize(minHeight = 170.dp)
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .defaultMinSize(minHeight = 170.dp)
                    .padding(horizontal = SmallPadding),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                ShimmerEffect(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(16.dp)
                )

                ShimmerEffect(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(16.dp)
                )


                ShimmerEffect(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(16.dp)
                )


                // Show details button
                ShimmerEffect(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(16.dp)
                )
            }

        }
    }
}

@Composable
fun ItemsListItemShimmerEffect(modifier: Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = Dimens.MediumPadding)
            .sizeIn(minHeight = 300.dp),
        shape = RoundedCornerShape(26.dp),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 4.dp,
        ),
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.background,
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            ShimmerEffect(
                modifier = Modifier
                    .fillMaxHeight(0.7f)
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.weight(1f))

            ShimmerEffect(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = SmallPadding)
                    .height(24.dp)

            )

            ShimmerEffect(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = SmallPadding)
                    .height(24.dp)

            )

        }
    }
}

@Composable
fun CategoriesItemShimmerEffect() {
    Box(
        modifier = Modifier
            .height(40.dp)
            .width(100.dp)
            .padding(end = Dimens.MediumPadding)
            .clip(RoundedCornerShape(10.dp))
            .shimmerEffect()
    ) {

    }
}

@Composable
fun OrderListItemShimmerEffect() {
    val brush = Brush.linearGradient(
        listOf(
            MaterialTheme.colorScheme.background,
            MaterialTheme.colorScheme.primary
        ),
        start = Offset(0f, 1000f),
        end = Offset(2000f, 2500f)
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = MediumPadding, vertical = SmallPadding),
        shape = RoundedCornerShape(26.dp),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 4.dp
        ),
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        )
    ) {
        Box(Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(brush = brush)
                    .padding(horizontal = MediumPadding, vertical = SmallPadding)
            ) {
                // Shimmering Order ID
                ShimmerEffect(
                    modifier = Modifier
                        .padding(vertical = SmallPadding)
                        .fillMaxWidth()
                        .height(MediumPadding)
                        .offset(y = (-1.5).dp)

                )

                // Shimmering Order Date
                ShimmerEffect(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .height(14.dp)
                        .offset(y = (-1.5).dp)
                        .align(Alignment.CenterHorizontally)
                )

                Spacer(
                    modifier = Modifier
                        .padding(vertical = SmallPadding)
                        .height(0.5.dp)
                        .fillMaxWidth()
                        .offset(y = (-1.5).dp)
                        .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.5f))
                )

                // Shimmering Number of Items
                ShimmerEffect(
                    modifier = Modifier
                        .fillMaxWidth(0.6f)
                        .height(14.dp)
                        .offset(y = (-3).dp)
                )

                // Shimmering Order Description
                ShimmerEffect(
                    modifier = Modifier
                        .padding(vertical = SmallPadding)
                        .fillMaxWidth(0.6f)
                        .height(14.dp)
                        .offset(y = (-3).dp)
                )

                // Shimmering Delivery Method
                ShimmerEffect(
                    modifier = Modifier
                        .fillMaxWidth(0.6f)
                        .height(14.dp)
                        .offset(y = (-3).dp)
                )

                Spacer(
                    modifier = Modifier
                        .padding(vertical = SmallPadding)
                        .height(0.5.dp)
                        .fillMaxWidth()
                        .offset(y = (-1.5).dp)
                        .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.5f))
                )

                // Shimmering Price and Details
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = (-3).dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    ShimmerEffect(
                        modifier = Modifier
                            .width(50.dp)
                            .height(14.dp)
                    )
                    ShimmerEffect(
                        modifier = Modifier
                            .width(70.dp)
                            .height(LargePadding)
                            .clip(RoundedCornerShape(SmallPadding))
                    )
                }
            }

        }
    }
}