package com.youppix.ecommercecourse.presentation.home_app.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
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
import java.util.Locale


@Stable
@Composable
fun FlashSaleItem(
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
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val isArabic = Locale.getDefault().language == "ar"

    val brush = Brush.linearGradient(
        listOf(
            MaterialTheme.colorScheme.background,
            MaterialTheme.colorScheme.primary
        ),
        start = Offset(1000f, 0f),
        end = Offset(1000f, 1000f)
    )
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = MediumPadding),
        shape = RoundedCornerShape(26.dp),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 4.dp,
        ),
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        )
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .drawBehind {
                drawRect(brush = brush)
            }) {
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
                    text = if (isArabic) item.itemNameAr else item.itemName,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Start,
                    maxLines = 2
                )
                Row(
                    modifier = Modifier
                        .padding(end = SmallPadding),
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
                        modifier = Modifier.padding(horizontal = ExtraSmallPadding)
                    )
                    Icon(
                        painterResource(id = R.drawable.ic_money_cash), contentDescription = null,
                        Modifier.size(16.dp),
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
                        color = Color.Black,
                        modifier = Modifier
                            .padding(horizontal = ExtraSmallPadding2)
                    )
                    if (item.itemDiscount.toString().isNotEmpty())
                        Icon(
                            painterResource(id = R.drawable.ic_discount), contentDescription = null,
                            Modifier.size(17.dp),
                            tint = Color.Black
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
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontWeight = FontWeight.SemiBold
                        ),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(
                            horizontal = SmallPadding,
                            vertical = ExtraSmallPadding2
                        )
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
