package com.youppix.ecommercecourse.presentation.home_app.home.components

import android.widget.Space
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.youppix.ecommercecourse.R
import com.youppix.ecommercecourse.common.Dimens
import com.youppix.ecommercecourse.common.Dimens.ExtraSmallPadding2
import com.youppix.ecommercecourse.common.Urls
import com.youppix.ecommercecourse.domain.model.items.Item
import kotlinx.coroutines.Dispatchers

@Stable
@Composable
fun NewArrivalsItem(
    modifier: Modifier = Modifier,
    item: Item,
    onDetailsClick: () -> Unit
) {
    val context = LocalContext.current

    val imageRequest = ImageRequest.Builder(context)
        .data(Urls.IMAGES_URL + item.itemImage)
        .memoryCacheKey(Urls.IMAGES_URL + item.itemImage)
        .diskCacheKey(Urls.IMAGES_URL + item.itemImage)
        .dispatcher(Dispatchers.IO)
        .diskCachePolicy(CachePolicy.ENABLED)
        .memoryCachePolicy(CachePolicy.ENABLED)
        .build()
    Card(
        modifier = modifier
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
            Card(
                modifier = Modifier
                    .width(150.dp)
                    .defaultMinSize(minHeight = 200.dp),
                shape = RoundedCornerShape(
                    topEnd = 0.dp,
                ),
                elevation = CardDefaults.elevatedCardElevation(
                    defaultElevation = 4.dp
                )
            ) {

                AsyncImage(
                    model = imageRequest,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Column(
                modifier = Modifier
                    .padding(horizontal = Dimens.SmallPadding)
                    .fillMaxSize()
                    .defaultMinSize(minHeight = 200.dp),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = item.itemName,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Start,
                    maxLines = 2,
                    modifier = Modifier.padding(top = ExtraSmallPadding2)
                )
                Row(
                    modifier = Modifier
                        .padding(end = Dimens.SmallPadding),
                ) {

                    Text(
                        text = "${item.itemPrice}",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        color = Color.Green,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "DA", style = MaterialTheme.typography.bodyMedium.copy(
                            color = MaterialTheme.colorScheme.primary
                        ),
                        modifier = Modifier.padding(horizontal = Dimens.ExtraSmallPadding)
                    )
                    Icon(
                        painterResource(id = R.drawable.ic_money_cash), contentDescription = null,
                        Modifier.size(23.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )


                }

                Row(
                    modifier = Modifier
                        .padding(end = Dimens.SmallPadding),
                    verticalAlignment = Alignment.CenterVertically,
                ) {

                    Text(
                        text = item.itemDesc,
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = colorResource(id = R.color.text_medium),
                            fontSize = 12.sp,
                        ),

                        modifier = Modifier
                            .padding(horizontal = Dimens.ExtraSmallPadding2),
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )

                }

                Spacer(modifier = Modifier.weight(1f))

                // Show details button
                Card(
                    modifier = Modifier
                        .padding(bottom = Dimens.SmallPadding)
                        .clickable { onDetailsClick() },
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary)
                ) {

                    Text(
                        text = stringResource(id = R.string.details),
                        color = MaterialTheme.colorScheme.background,
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(
                            horizontal = Dimens.SmallPadding,
                            vertical = Dimens.SmallPadding
                        )
                    )
                }
            }
        }
    }

}