package com.youppix.ecommercecourse.presentation.home_app.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.youppix.ecommercecourse.R
import com.youppix.ecommercecourse.common.Dimens.ExtraSmallPadding
import com.youppix.ecommercecourse.common.Dimens.MediumPadding
import com.youppix.ecommercecourse.common.Urls.IMAGES_URL
import com.youppix.ecommercecourse.domain.model.items.Item
import com.youppix.ecommercecourse.presentation.ui.theme.EcommerceCourseTheme
import kotlinx.coroutines.Dispatchers
import java.util.Locale

@Composable
fun ItemsListItem(
    modifier: Modifier = Modifier,
    item: Item
) {
    val context = LocalContext.current
    val imageUrl = IMAGES_URL + item.itemImage
    val imageRequest = ImageRequest.Builder(context)
        .data(imageUrl)
        .dispatcher(Dispatchers.IO)
        .memoryCacheKey(imageUrl)
        .diskCacheKey(imageUrl)
        .diskCachePolicy(CachePolicy.ENABLED)
        .memoryCachePolicy(CachePolicy.ENABLED)
        .build()

    val isArabic = Locale.getDefault().language == "ar"
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = MediumPadding)
            .sizeIn(maxHeight = 300.dp),
        shape = RoundedCornerShape(26.dp),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 4.dp,
        ),
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        )
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = CenterHorizontally
        ) {

            AsyncImage(
                model = imageRequest,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .sizeIn(
                        maxWidth = (LocalConfiguration.current.screenWidthDp.dp / 2).minus(
                            MediumPadding
                        )
                    )
                    .weight(1f)
                    .align(CenterHorizontally)
            )

            Text(
                text = if(isArabic) item.itemNameAr else item.itemName, style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.SemiBold
                ) ,
                modifier = Modifier.padding( horizontal = ExtraSmallPadding),
                maxLines = 2,
            )

            Row(
                modifier = Modifier.padding( start = ExtraSmallPadding),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = item.itemPrice.toString(),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                    ),

                )
                Text(
                    text = stringResource(id = R.string.da), style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.padding(horizontal = ExtraSmallPadding),

                )

                Icon(
                    painterResource(id = R.drawable.ic_money_cash), contentDescription = null,
                    Modifier.size(16.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
            }

        }
    }
}


@Preview(showBackground = true)
@Composable
private fun ItemsListItemPreview() {
    EcommerceCourseTheme {
        ItemsListItem(
            item = Item(
                itemName = "name",
                itemNameAr = "اسم",
                itemImage = "product_example.jpg",
                itemPrice = 3500,
                itemColor = "",
                itemDiscount = 0,
                itemDesc = "",
                itemDescAr = "",
                itemId = 0
            )
        )
    }
}