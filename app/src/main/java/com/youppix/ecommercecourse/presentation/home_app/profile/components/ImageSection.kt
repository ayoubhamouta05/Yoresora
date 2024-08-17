package com.youppix.ecommercecourse.presentation.home_app.profile.components

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.youppix.ecommercecourse.R
import com.youppix.ecommercecourse.common.Dimens.ExtraSmallPadding
import com.youppix.ecommercecourse.common.Dimens.SmallPadding
import com.youppix.ecommercecourse.common.Dimens.SocialMediaItemSize
import com.youppix.ecommercecourse.common.Urls.PROFILE_IMAGES_URL
import kotlinx.coroutines.Dispatchers

@Composable
fun ImageSection(
    modifier: Modifier = Modifier,
    selectedImageUri : String?,
    userName: String,
    onEditClick: () -> Unit
) {

    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(PROFILE_IMAGES_URL + selectedImageUri)
            .memoryCacheKey(PROFILE_IMAGES_URL + selectedImageUri)
            .diskCacheKey(PROFILE_IMAGES_URL + selectedImageUri)
            .dispatcher(Dispatchers.IO)
            .diskCachePolicy(CachePolicy.ENABLED)
            .memoryCachePolicy(CachePolicy.ENABLED)
            .build()
    )
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = SmallPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(ExtraSmallPadding)
    ) {
        Box(modifier = Modifier.size(110.dp)) {
            Image(
                painter = painter,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape)
            )

            Card(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .offset(x = ExtraSmallPadding, y = ExtraSmallPadding),
                shape = CircleShape,
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                border = BorderStroke(3.dp, MaterialTheme.colorScheme.background),
                onClick = {
                    onEditClick()
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_edit), contentDescription = null,
                    modifier = Modifier
                        .size(SocialMediaItemSize)
                        .padding(SmallPadding)
                )
            }
        }

        Text(
            text = userName, style = MaterialTheme.typography.titleSmall
        )

    }

}