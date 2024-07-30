package com.youppix.ecommercecourse.presentation.components

import android.annotation.SuppressLint
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.youppix.ecommercecourse.R
import com.youppix.ecommercecourse.common.Dimens
import com.youppix.ecommercecourse.common.Dimens.SmallPadding
import com.youppix.ecommercecourse.presentation.ui.theme.EcommerceCourseTheme

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
fun MostPopularItemShimmerEffect() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Dimens.MediumPadding),
        shape = RoundedCornerShape(26.dp),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 4.dp,
        ),
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.background,
        )
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {


            ShimmerEffect(
                modifier = Modifier
                    .width(150.dp)
                    .defaultMinSize(minHeight = 200.dp)
                    .clip(
                        shape = RoundedCornerShape(
                            topEnd = 0.dp, topStart = 26.dp,
                            bottomStart = 26.dp, bottomEnd = 60.dp
                        )
                    )
                    .shimmerEffect()
            )



            Column(
                modifier = Modifier
                    .padding(horizontal = Dimens.SmallPadding)
                    .fillMaxSize()
                    .defaultMinSize(minHeight = 200.dp),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                ShimmerEffect(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(24.dp)
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

                ShimmerEffect(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(24.dp)
                )

            }

            Row(
                modifier = Modifier
                    .padding(end = Dimens.SmallPadding)
                    .shimmerEffect(),
                verticalAlignment = Alignment.CenterVertically,
            ) {

                Text(
                    text = "",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Red,
                    modifier = Modifier
                        .padding(horizontal = Dimens.ExtraSmallPadding2)
                )


            }

            // Show details button
            Card(
                modifier = Modifier
                    .padding(bottom = Dimens.SmallPadding)
                    .shimmerEffect(),
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary)
            ) {

                Text(
                    text = "",
                    color = MaterialTheme.colorScheme.background,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(
                        horizontal = Dimens.ExtraSmallPadding,
                        vertical = Dimens.ExtraSmallPadding
                    ),

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
            .sizeIn(maxHeight = 300.dp),
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