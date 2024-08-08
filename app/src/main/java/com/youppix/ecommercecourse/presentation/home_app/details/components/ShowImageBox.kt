package com.youppix.ecommercecourse.presentation.home_app.details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.youppix.ecommercecourse.R
import com.youppix.ecommercecourse.common.Dimens
import com.youppix.ecommercecourse.presentation.home_app.components.CustomIconBack
import com.youppix.ecommercecourse.presentation.home_app.components.CustomIconItem

@Composable
fun ShowImageBox(imageRequest: ImageRequest, isArabic: Boolean, onBackClick: () -> Unit) {
    var scale by remember { mutableFloatStateOf(1f) }

    val state = rememberTransformableState { zoomChange, _, _ ->
        if (scale < 0.5)
            scale = 0.5f
        else {
            scale *= zoomChange
        }

    }

    val screenHeight = LocalConfiguration.current.screenHeightDp
    val screenWidth = LocalConfiguration.current.screenWidthDp
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background.copy(alpha = 0.7f))
    ) {

        Box(
            Modifier
                .align(Alignment.Center)
                .transformable(state = state)
                .fillMaxSize()
        ) {

            AsyncImage(
                model = imageRequest,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(screenWidth.dp / 1.3f)
                    .height(screenHeight.dp / 1.5f)
                    .align(Alignment.Center)
                    .graphicsLayer(
                        scaleX = if (scale < 0.5f) 0.5f else scale,
                        scaleY = if (scale < 0.5f) 0.5f else scale,
                    )
                    .clip(RoundedCornerShape(Dimens.SmallPadding))

            )
        }
        CustomIconBack(
            modifier = Modifier
                .rotate(if (isArabic) 180f else 0f)
                .align(
                    Alignment.TopStart
                )
                .padding(horizontal = Dimens.SmallPadding, vertical = Dimens.SmallPadding)
        ) {
            onBackClick()
        }
    }

}