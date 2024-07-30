package com.youppix.ecommercecourse.presentation.home_app.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
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

    val imageRequest = ImageRequest.Builder(context)
        .data(IMAGES_URL + item.itemImage)
        .memoryCacheKey(IMAGES_URL + item.itemImage)
        .diskCacheKey(IMAGES_URL + item.itemImage)
        .dispatcher(Dispatchers.IO)
        .diskCachePolicy(CachePolicy.ENABLED)
        .memoryCachePolicy(CachePolicy.ENABLED)
        .build()
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = MediumPadding),
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
                    .padding(horizontal = SmallPadding)
                    .fillMaxSize()
                    .defaultMinSize(minHeight = 200.dp),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = item.itemName,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Start,
                    maxLines = 2
                )
                Row(
                    modifier = Modifier
                        .padding(end = SmallPadding),
                    verticalAlignment = Alignment.CenterVertically
//                    horizontalArrangement = Arrangement.spacedBy(SmallPadding)
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
                            color = Color.Black,
                        ),
                        modifier = Modifier.padding(horizontal = ExtraSmallPadding)
                    )
                    Icon(
                        painterResource(id = R.drawable.ic_money_cash), contentDescription = null,
                        Modifier.size(23.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )


                }

                Row(
                    modifier = Modifier
                        .padding(end = SmallPadding),
                    verticalAlignment = Alignment.CenterVertically,
                ) {

                    Text(
                        text = if (item.itemDiscount.toString()
                                .isNotEmpty()
                        ) "${item.itemDiscount}%" else "",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        color = Color.Red,
                        modifier = Modifier
                            .padding(horizontal = ExtraSmallPadding2)
                    )
                    if (item.itemDiscount.toString().isNotEmpty())
                        Icon(
                            painterResource(id = R.drawable.ic_discount), contentDescription = null,
                            Modifier.size(17.dp),
                            tint = Color.Red
                        )


                }

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
                            horizontal = SmallPadding,
                            vertical = SmallPadding
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
