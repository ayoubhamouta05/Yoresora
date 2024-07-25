package com.youppix.ecommercecourse.presentation.starting_app.auth.login.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import com.youppix.ecommercecourse.R
import com.youppix.ecommercecourse.common.Dimens.SmallPadding
import com.youppix.ecommercecourse.common.Dimens.SocialMediaItemSize

@Composable
fun SocialMediaItem(image : Int , modifier: Modifier = Modifier , onCLick : () -> Unit) {
    Image( painterResource(id = image), contentDescription = null,
        modifier
            .size(SocialMediaItemSize)
            .clip(CircleShape)
            .clickable {
                onCLick()
            }
            .background(color = colorResource(id = R.color.grayExtraLight))
            .padding(
                SmallPadding
            )
    )
}