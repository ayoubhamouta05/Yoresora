package com.youppix.ecommercecourse.presentation.home_app.home.components

import androidx.compose.foundation.background
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.youppix.ecommercecourse.R
import com.youppix.ecommercecourse.common.Dimens
import com.youppix.ecommercecourse.common.Dimens.ExtraSmallPadding2
import com.youppix.ecommercecourse.common.Urls
import com.youppix.ecommercecourse.domain.model.items.Item
import kotlinx.coroutines.Dispatchers
import java.util.Locale

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

    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val isArabic = Locale.getDefault().language == "ar"

    val brush = Brush.linearGradient(
        listOf(
            MaterialTheme.colorScheme.background,
            MaterialTheme.colorScheme.primary ),
        start = Offset(1000f, 0f),
        end = Offset(1000f, 1000f)
    )
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = Dimens.MediumPadding),
        shape = RoundedCornerShape(26.dp),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 4.dp,
        ),
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        )
    ) {
        Row(modifier = Modifier.fillMaxWidth().background(
            brush = brush
        )) {
            Card(
                modifier = Modifier
                    .width(screenWidth / 2.5f)
                    .defaultMinSize(minHeight = 200.dp),
                shape = RoundedCornerShape(
                    topEnd = 0.dp,
                ),
                elevation = CardDefaults.elevatedCardElevation(
                    defaultElevation = 4.dp
                ),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            ) {

                AsyncImage(
                    model = imageRequest,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize(),
                )
            }

            Column(
                modifier = Modifier
                    .padding(horizontal = Dimens.SmallPadding)
                    .fillMaxSize()
                    .defaultMinSize(minHeight = 200.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = if (isArabic) item.itemNameAr else item.itemName,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Start,
                    maxLines = 2,
                    modifier = Modifier.padding(top = ExtraSmallPadding2)
                )
                Row(
                    modifier = Modifier
                        .padding(end = Dimens.SmallPadding),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "${item.itemPrice}",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = stringResource(id = R.string.da),
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.padding(horizontal = Dimens.ExtraSmallPadding)
                    )
                    Icon(
                        painterResource(id = R.drawable.ic_money_cash), contentDescription = null,
                        Modifier.size(16.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )


                }

                Row(
                    modifier = Modifier
                        .padding(end = Dimens.SmallPadding),
                    verticalAlignment = Alignment.CenterVertically,
                ) {

                    Text(
                        text = if (isArabic) item.itemDescAr else item.itemDesc,
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = colorResource(id = R.color.text_medium)
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
                            vertical = ExtraSmallPadding2
                        )
                    )
                }
            }
        }
    }

}