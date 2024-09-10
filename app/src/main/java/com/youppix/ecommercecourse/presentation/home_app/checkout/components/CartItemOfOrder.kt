package com.youppix.ecommercecourse.presentation.home_app.checkout.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.youppix.ecommercecourse.R
import com.youppix.ecommercecourse.common.Dimens
import com.youppix.ecommercecourse.common.Dimens.SmallPadding
import com.youppix.ecommercecourse.common.Urls
import com.youppix.ecommercecourse.domain.model.cart.CartData
import kotlinx.coroutines.Dispatchers

@Composable
fun CartItemOfOrder(
    modifier: Modifier = Modifier,
    cartItem: CartData,
    isArabic: Boolean,
) {
    val context = LocalContext.current
    val image = rememberAsyncImagePainter(
        model = ImageRequest.Builder(context).data(Urls.IMAGES_URL + cartItem.items_image)
            .memoryCacheKey(Urls.IMAGES_URL + cartItem.items_image)
            .diskCacheKey(Urls.IMAGES_URL + cartItem.items_image).dispatcher(Dispatchers.IO)
            .diskCachePolicy(CachePolicy.ENABLED).memoryCachePolicy(CachePolicy.ENABLED).build()
    )

    Box(modifier = modifier) {
        Row(
            Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(Dimens.SmallPadding)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = image,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(end = Dimens.MediumPadding)
                    .size(100.dp)
                    .clip(RoundedCornerShape(Dimens.SmallPadding))
            )

            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = if (isArabic) cartItem.items_name_ar else cartItem.items_name,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold
                    )
                )

                Text(
                    text = stringResource(
                        id = R.string.size, cartItem.sizes_name
                    ) + "    " + stringResource(
                        id = R.string.color,
                        if (isArabic) cartItem.colors_name_ar else cartItem.colors_name
                    ), style = MaterialTheme.typography.bodySmall.copy(
                        color = colorResource(id = R.color.body)
                    ), modifier = Modifier.fillMaxWidth()
                )

                Text(
                    modifier = Modifier.padding(bottom = Dimens.SmallPadding),
                    text = stringResource(id = R.string.prixValue, cartItem.items_price.toString()),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold
                    ),

                    )

            }
        }


        Text(
            text = stringResource(id = R.string.count, cartItem.item_quantity.toString()),
            style = MaterialTheme.typography.bodyMedium.copy(
                color = colorResource(id = R.color.body), textAlign = TextAlign.Center
            ),
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(horizontal = Dimens.MediumPadding)
                .padding(bottom = SmallPadding),
        )


    }


}