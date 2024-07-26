package com.youppix.ecommercecourse.presentation.home_app.components

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
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.youppix.ecommercecourse.common.Dimens.ExtraSmallPadding2
import com.youppix.ecommercecourse.common.Dimens.MediumPadding
import com.youppix.ecommercecourse.common.Dimens.SmallPadding
import com.youppix.ecommercecourse.common.Urls.IMAGES_URL
import com.youppix.ecommercecourse.presentation.ui.theme.EcommerceCourseTheme
import okhttp3.Dispatcher


@Composable
fun MostPopularItem(
    modifier: Modifier = Modifier,
    imageUrl: String,
    itemName: String,
    itemDiscount: String,
    itemPrice: String,
    time: String,
    onDetailsClick: () -> Unit
) {
    val context = LocalContext.current
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
                    .width(150.dp) // Adjust width as per your need
                    .defaultMinSize(minHeight = 200.dp), // Adjust height as per your need
                shape = RoundedCornerShape(
                    topEnd = 0.dp, topStart = 26.dp,
                    bottomStart = 26.dp, bottomEnd = 60.dp
                ),
                elevation = CardDefaults.elevatedCardElevation(
                    defaultElevation = 4.dp
                )
            ) {
                val imageRequest = ImageRequest.Builder(context)
                    .data(imageUrl)
                    .memoryCacheKey(imageUrl)
                    .diskCacheKey(imageUrl)
                    .diskCachePolicy(CachePolicy.ENABLED)
                    .memoryCachePolicy(CachePolicy.ENABLED)
                    .build()
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
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = itemName ,
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    ),
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
                        text = "$itemDiscount%",
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.error
                        ),
                        modifier = Modifier
                            .background(
                                MaterialTheme.colorScheme.background,
                                shape = RoundedCornerShape(SmallPadding)
                            )
                            .padding(horizontal = ExtraSmallPadding2)
                    )
                    Text(
                        text = time,
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontWeight = FontWeight.Normal,
                            color = MaterialTheme.colorScheme.onErrorContainer
                        ),
                        textAlign = TextAlign.Center
                    )
                }

                // Show details button
                Card(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .clickable { onDetailsClick() },
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary) // text_title color
                ) {

                    Text(
                        text = "Order Now",
                        color = MaterialTheme.colorScheme.background,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),

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
                MostPopularItem(
                    imageUrl = "${IMAGES_URL}product_example.jpg", // Replace with an actual image URL
                    itemName = "Product Name",
                    itemDiscount = "10 %",
                    itemPrice = "4500 DA",
                    time = "10/08/2024",
                    onDetailsClick = { /* Handle click here */ }
                )
            }
        }
    }
}
