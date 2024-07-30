package com.youppix.ecommercecourse.presentation.home_app.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.youppix.ecommercecourse.R
import com.youppix.ecommercecourse.common.Dimens.ExtraSmallPadding
import com.youppix.ecommercecourse.common.Dimens.ExtraSmallPadding2
import com.youppix.ecommercecourse.common.Dimens.MediumPadding
import com.youppix.ecommercecourse.common.Dimens.SmallPadding
import com.youppix.ecommercecourse.common.Urls.IMAGES_URL
import com.youppix.ecommercecourse.domain.model.items.Item
import com.youppix.ecommercecourse.presentation.ui.theme.EcommerceCourseTheme
import kotlinx.coroutines.Dispatchers



@Stable
@Composable
fun MostPopularItem(
    modifier: Modifier = Modifier,
    item: Item,
    onDetailsClick: () -> Unit
) {
    val context = LocalContext.current

    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(context)
            .data(IMAGES_URL+item.itemImage)
            .memoryCacheKey(IMAGES_URL+item.itemImage)
            .diskCacheKey(IMAGES_URL+item.itemImage)
            .dispatcher(Dispatchers.IO)
            .diskCachePolicy(CachePolicy.ENABLED)
            .memoryCachePolicy(CachePolicy.ENABLED)
            .build()
    )
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = MediumPadding),
        shape = RoundedCornerShape(26.dp),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 4.dp
        ),
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.background
        )
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Card(
                modifier = Modifier
                    .width(150.dp)
                    .defaultMinSize(minHeight = 200.dp),
                shape = RoundedCornerShape(
                    topEnd = 0.dp, topStart = 26.dp,
                    bottomStart = 26.dp, bottomEnd = 60.dp
                ),
                elevation = CardDefaults.elevatedCardElevation(
                    defaultElevation = 4.dp
                )
            ) {

                Image(
                    painter = painter,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Column(
                modifier = Modifier
                    .padding(horizontal = SmallPadding)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    text = item.itemName,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Start,
                    maxLines = 2
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = SmallPadding),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = if (item.itemDiscount.toString().isNotEmpty()) "${item.itemDiscount}%" else "",
                        fontWeight = FontWeight.Bold,
                        color = Color.Red,
                        modifier = Modifier
                            .background(
                                MaterialTheme.colorScheme.background,
                                shape = RoundedCornerShape(SmallPadding)
                            )
                            .padding(horizontal = ExtraSmallPadding2)
                    )
                    Text(
                        text = "${item.itemPrice} DA",
                        fontWeight = FontWeight.Bold,
                        color = Color.Green,
                        textAlign = TextAlign.Center
                    )
                }

                Text(
                    text = item.itemDesc,
                    maxLines = 2,
                    fontSize = 14.sp,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Start
                )

                Spacer(modifier = Modifier.weight(1f))
                // Show details button
                Card(
                    modifier = Modifier
                        .padding(bottom = SmallPadding)
                        .clickable { onDetailsClick() },
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary)
                ) {

                    Text(
                        text = stringResource(id = R.string.orderNow),
                        color = MaterialTheme.colorScheme.background,
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(
                            horizontal = ExtraSmallPadding,
                            vertical = ExtraSmallPadding
                        ),

                        )

                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CustomMostPopularItemPreview() {
    EcommerceCourseTheme {
        LazyColumn {
            item {
//                MostPopularItem(
//                    item = Item(
//                        itemName = "Product Name",
//                        itemDiscount = "10",
//                        itemPrice = "4500 DA",
//                        itemDescription = "${IMAGES_URL}product_example.jpg${IMAGES_URL}product_example.jpg${IMAGES_URL}product_example.jpg${IMAGES_URL}product_example.jpg${IMAGES_URL}product_example.jpg",
//                    ),
//                    onDetailsClick = { /* Handle click here */ }
//                )
            }
        }
    }
}
