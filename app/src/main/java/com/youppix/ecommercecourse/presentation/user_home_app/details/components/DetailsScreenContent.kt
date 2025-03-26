package com.youppix.ecommercecourse.presentation.user_home_app.details.components

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.youppix.ecommercecourse.R
import com.youppix.ecommercecourse.common.Dimens
import com.youppix.ecommercecourse.common.Dimens.ExtraSmallPadding
import com.youppix.ecommercecourse.common.Dimens.ExtraSmallPadding2
import com.youppix.ecommercecourse.common.Dimens.MediumPadding
import com.youppix.ecommercecourse.common.Dimens.SmallPadding
import com.youppix.ecommercecourse.common.Urls
import com.youppix.ecommercecourse.domain.model.items.Item
import com.youppix.ecommercecourse.presentation.components.CustomCircularProgress
import com.youppix.ecommercecourse.presentation.user_home_app.MainActivity
import com.youppix.ecommercecourse.presentation.user_home_app.components.CustomIcon
import com.youppix.ecommercecourse.presentation.user_home_app.components.EmptyScreen
import com.youppix.ecommercecourse.presentation.user_home_app.details.DetailsEvent
import com.youppix.ecommercecourse.presentation.user_home_app.details.DetailsState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Locale

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DetailsScreenContent(
    modifier: Modifier,
    state: DetailsState,
    event: (DetailsEvent) -> Unit,
    item: Item,
    userId: Int,
    makeCustomSize: () -> Unit,
    onBackClicked: () -> Unit,
) {

    val context = LocalContext.current
    val isArabic = Locale.getDefault().language == "ar"
    val pagerState =
        rememberPagerState(pageCount = { state.details.images.size }, initialPage = 0)
    val lazyColumnState = rememberLazyListState()
    val scope = rememberCoroutineScope()

    var message by remember {
        mutableStateOf("")
    }
    message =
        if (!state.details.is_favorite) stringResource(id = R.string.addedToWishlist) else stringResource(
            id = R.string.removedFromWishlist
        )

    val brush = Brush.linearGradient(
        listOf(
            MaterialTheme.colorScheme.background,
            MaterialTheme.colorScheme.primary
        ),
        start = Offset(1200f, 1000f),
        end = Offset(0f, 1800f)
    )

    val brushAR = Brush.linearGradient(
        listOf(
            MaterialTheme.colorScheme.background,
            MaterialTheme.colorScheme.primary
        ),
        start = Offset(0f, 1000f),
        end = Offset(2500f, 1000f)
    )

    var showImage by remember {
        mutableStateOf(false)
    }
    var enableBackPress by remember {
        mutableStateOf(false)
    }

    var imageScale by remember { mutableFloatStateOf(1f) }
    var imageOffset by remember { mutableStateOf(Offset.Zero) }

    val transformState = rememberTransformableState { zoomChange, offsetChange, _ ->
        if (imageScale < 0.6)
            imageScale = 0.6f
        else if (imageScale > 3)
            imageScale = 3f
        else {
            imageScale *= zoomChange
            imageOffset += offsetChange
            Log.d("Offset", "x : ${imageOffset.x} , y : ${imageOffset.y}")
        }
    }

    onBackButtonPressed(context, enableBackPress) {
        if (showImage) {
            showImage = false
            enableBackPress = false
            imageScale = 1f
            imageOffset = Offset.Zero
        } else {
            onBackClicked()
        }
    }

    LaunchedEffect(pagerState.currentPage) {
        imageScale = 1f
        imageOffset = Offset.Zero
    }

    Box(modifier = Modifier.fillMaxSize()) {
        if (state.error.isNullOrEmpty()) {
            LazyColumn(
                modifier = modifier,
                userScrollEnabled = !showImage,
                state = lazyColumnState
            ) {
                // HorizontalPager
                item {
                    if (state.details.images.isNotEmpty())
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillParentMaxHeight(if (showImage) 0.9f else 0.4f)
                                .padding(bottom = ExtraSmallPadding)
                                .animateContentSize(),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.surface
                            ),
                            shape = if (!showImage) RoundedCornerShape(
                                bottomStart = 70.dp,
                                bottomEnd = 70.dp
                            )
                            else RoundedCornerShape(0),
                            elevation = CardDefaults.cardElevation(
                                defaultElevation = 4.dp
                            )
                        ) {
                            HorizontalPager(
                                state = pagerState,
                                modifier = Modifier
                                    .fillParentMaxSize()
                                    .animateContentSize()
                            ) { page ->
                                Box(modifier = Modifier
                                    .fillMaxSize()
                                    .transformable(transformState, enabled = showImage)
                                    .clickable(enabled = !showImage) {
                                        showImage = true
                                        enableBackPress = true
                                        scope.launch {
                                            lazyColumnState.animateScrollToItem(0)
                                        }
                                    }
                                    .drawBehind {
                                        drawRect(if (isArabic) brushAR else brush)
                                    }) {
                                    val currentPage =
                                        Urls.IMAGES_URL + state.details.images[page]
                                    val imageRequest =
                                        ImageRequest.Builder(context).data(currentPage)
                                            .dispatcher(Dispatchers.IO)
                                            .diskCachePolicy(CachePolicy.ENABLED)
                                            .memoryCachePolicy(CachePolicy.ENABLED).build()

                                    AsyncImage(
                                        model = imageRequest,
                                        contentDescription = null,
                                        contentScale = ContentScale.Fit,
                                        modifier = Modifier
                                            .scale(imageScale)
                                            .fillParentMaxHeight(if (showImage) 0.6f else 0.4f)
                                            .align(Alignment.Center)
                                            .graphicsLayer(
                                                scaleX = if (imageScale < 0.6f) 0.6f else imageScale,
                                                scaleY = if (imageScale < 0.6f) 0.6f else imageScale,
                                                translationX = imageOffset.x,
                                                translationY = imageOffset.y
                                            )
                                            .clip(RoundedCornerShape(SmallPadding))

                                    )
                                }

                            }
                        }
                }
                // Image Indicator
                item {
                    ImageIndicatorSection(state = state, pagerState = pagerState)
                }

                item {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = MediumPadding)
                    ) {
                        Text(
                            text = if (isArabic) state.details.categories_name_ar else state.details.categories_name,
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Normal
                            ),
                            textAlign = TextAlign.Start,
                            modifier = Modifier.offset(y = (-1).dp)
                        )
                        Spacer(modifier = Modifier.weight(1f))

                        Text(
                            text = if (item.itemDiscount.toString()
                                    .isNotEmpty()
                            )stringResource(id = R.string.discount) + ": " else "",
                            style = MaterialTheme.typography.bodyMedium,
                            color = colorResource(id = R.color.body),
                            modifier = Modifier.offset(y = (-1).dp)
                        )
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
                                painterResource(id = R.drawable.ic_discount),
                                contentDescription = null,
                                Modifier.size(17.dp),
                                tint = Color.Red
                            )


                    }

                }
                item {
                    Text(
                        text = if (isArabic) item.itemNameAr else item.itemName,
                        style = MaterialTheme.typography.displaySmall.copy(
                            fontWeight = FontWeight.SemiBold
                        ),
                        textAlign = TextAlign.Start,
                        modifier = Modifier.padding(
                            horizontal = Dimens.MediumPadding,
                            vertical = SmallPadding
                        )
                    )
                }

                item {
                    Text(
                        text = stringResource(id = R.string.details),
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        textAlign = TextAlign.Start,
                        modifier = Modifier.padding(horizontal = Dimens.MediumPadding)
                    )
                }

                item {
                    Text(
                        text = if (isArabic) item.itemDescAr else item.itemDesc,
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = colorResource(id = R.color.text_medium)
                        ),
                        textAlign = TextAlign.Start,
                        modifier = Modifier.padding(horizontal = Dimens.MediumPadding)
                    )
                }

                item {
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = Dimens.MediumPadding,
                                vertical = Dimens.MediumPadding
                            )
                            .height(1.dp)
                            .background(
                                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)
                            )
                    )
                }

                item {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(horizontal = MediumPadding)
                            .padding(bottom = ExtraSmallPadding),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = stringResource(id = R.string.selectSize) + " :",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Bold
                            ),
                            textAlign = TextAlign.Start,
                        )
                        Text(
                            text = stringResource(id = R.string.or),
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.Normal,
                                color = Color.Gray
                            ),
                            textAlign = TextAlign.Center,

                            )

                        Text(
                            text = stringResource(id = R.string.makeYourOwnSize),
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.SemiBold,
                                textDecoration = TextDecoration.Underline
                            ),
                            textAlign = TextAlign.End,
                            maxLines = 1,
                            modifier = Modifier
                                .clip(RoundedCornerShape(SmallPadding))
                                .clickable {
                                    makeCustomSize()
                                }
                                .background(
                                    Color.Transparent
                                )
                                .padding(horizontal = ExtraSmallPadding2)
                        )


                    }

                }
                item {
                    Box(modifier = Modifier.animateContentSize()) {
                        SelectSizeSection(state = state, event = event)
                    }

                }
                item {
                    Text(
                        text = stringResource(id = R.string.choseColors) + " :",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        textAlign = TextAlign.Start,
                        modifier = Modifier.padding(
                            start = Dimens.MediumPadding,
                            end = Dimens.MediumPadding,
                            top = Dimens.SmallPadding,
                            bottom = ExtraSmallPadding
                        )
                    )
                }
                item {
                    SelectColorSection(state = state, event = event)
                }
            }

            CustomIcon(
                modifier = Modifier
                    .align(
                        Alignment.TopStart
                    )
                    .padding(horizontal = SmallPadding, vertical = SmallPadding),
                backgroundColor = MaterialTheme.colorScheme.background,
                iconColor = MaterialTheme.colorScheme.onBackground,
                imageVector = Icons.AutoMirrored.Filled.ArrowBack
            ) {
                if (showImage) {
                    showImage = false
                    enableBackPress = false
                    imageScale = 1f
                    imageOffset = Offset.Zero
                } else {
                    onBackClicked()
                }
            }

            CustomIcon(
                modifier = Modifier
                    .align(
                        Alignment.TopEnd
                    )
                    .padding(horizontal = SmallPadding, vertical = SmallPadding),
                backgroundColor = MaterialTheme.colorScheme.background,
                iconColor = MaterialTheme.colorScheme.onBackground,
                imageVector = if (state.details.is_favorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder
            ) {
                event(DetailsEvent.UpdateFavoriteState(userId = userId, itemId = item.itemId))
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center)
            )
            EmptyScreen(error = state.error) {
                event(DetailsEvent.GetItemDetails(itemId = item.itemId, userId = userId))
            }
        }
        CustomCircularProgress(state.isLoading)
    }

}

private fun onBackButtonPressed(context: Context, enable: Boolean, onBackPressed: () -> Unit) {
    (context as MainActivity).onBackPressedDispatcher.addCallback(
        context,
        object : OnBackPressedCallback(enable) {
            override fun handleOnBackPressed() {
                onBackPressed()
                remove()
            }
        }
    )
}