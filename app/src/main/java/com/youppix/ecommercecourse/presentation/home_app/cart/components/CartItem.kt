package com.youppix.ecommercecourse.presentation.home_app.cart.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Minimize
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.youppix.ecommercecourse.R
import com.youppix.ecommercecourse.common.Dimens.ExtraSmallPadding
import com.youppix.ecommercecourse.common.Dimens.ExtraSmallPadding2
import com.youppix.ecommercecourse.common.Dimens.MediumPadding
import com.youppix.ecommercecourse.common.Dimens.SmallPadding
import com.youppix.ecommercecourse.common.Urls
import com.youppix.ecommercecourse.domain.model.cart.CartData
import com.youppix.ecommercecourse.domain.model.cart.CartItemData
import kotlinx.coroutines.Dispatchers

@Composable
fun CartItem(
    modifier: Modifier = Modifier,
    cartItem: CartData,
    isArabic: Boolean,
    onCLick: (CartData) -> Unit,
) {
    val context = LocalContext.current
    val image = rememberAsyncImagePainter(
        model = ImageRequest.Builder(context).data(Urls.IMAGES_URL + cartItem.items_image)
            .memoryCacheKey(Urls.IMAGES_URL + cartItem.items_image)
            .diskCacheKey(Urls.IMAGES_URL + cartItem.items_image).dispatcher(Dispatchers.IO)
            .diskCachePolicy(CachePolicy.ENABLED).memoryCachePolicy(CachePolicy.ENABLED).build()
    )
    var quantity = remember {
        mutableIntStateOf(1)
    }
    Box(modifier = modifier){
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = MediumPadding)
                .clip(RoundedCornerShape(SmallPadding))
                .clickable {
                    onCLick(cartItem)
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = image,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(end = MediumPadding)
                    .size(100.dp)
                    .clip(RoundedCornerShape(SmallPadding))
            )

            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = if(isArabic) cartItem.items_name_ar else cartItem.items_name,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
                Row(horizontalArrangement = Arrangement.spacedBy(SmallPadding) ,
                    modifier = Modifier.fillMaxWidth()) {

                    Text(
                        text = stringResource(id = R.string.size, cartItem.sizes_name),
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = colorResource(id = R.color.body)
                        )
                    )


                    Text(
                        text = stringResource(
                            id = R.string.color,
                            if (isArabic) cartItem.colors_name_ar else cartItem.colors_name_ar
                        ),
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = colorResource(id = R.color.body)
                        ),
                        maxLines = 1 ,
                    )
                }

                Text(
                    modifier = Modifier.padding(bottom = SmallPadding),
                    text = stringResource(id = R.string.prixValue, cartItem.items_price.toString()),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold
                    )
                )

            }



        }

        Row(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(horizontal = MediumPadding )
                .padding(bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier
                .size(30.dp)
                .background(MaterialTheme.colorScheme.secondary.copy(alpha = 0.2f),
                    shape = RoundedCornerShape(ExtraSmallPadding)
                )
                .clip( shape = RoundedCornerShape(ExtraSmallPadding))
                .clickable {
                    --quantity.intValue
                }){
                Icon(
                    imageVector = Icons.Default.Minimize, contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .offset(y = -ExtraSmallPadding2.plus(5.dp)),
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }

            Text(
                text = quantity.intValue.toString(),
                modifier = Modifier.padding(horizontal = SmallPadding)
            )
            Box(modifier = Modifier
                .size(30.dp)
                .background(
                    MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(ExtraSmallPadding)
                )
                .clip( shape = RoundedCornerShape(ExtraSmallPadding))
                .clickable {
                    ++quantity.intValue
                }) {
                Icon(
                    imageVector = Icons.Default.Add, contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .fillMaxSize()
                        .padding(ExtraSmallPadding),
                    tint = MaterialTheme.colorScheme.background
                )
            }

        }
    }



}