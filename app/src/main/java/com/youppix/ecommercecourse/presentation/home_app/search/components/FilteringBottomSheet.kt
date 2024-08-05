package com.youppix.ecommercecourse.presentation.home_app.search.components

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.youppix.ecommercecourse.R
import com.youppix.ecommercecourse.common.Dimens.ExtraSmallPadding
import com.youppix.ecommercecourse.common.Dimens.ExtraSmallPadding2
import com.youppix.ecommercecourse.common.Dimens.MediumPadding
import com.youppix.ecommercecourse.common.Dimens.SmallPadding
import com.youppix.ecommercecourse.domain.model.items.ColorData
import com.youppix.ecommercecourse.domain.model.items.FilteringItems
import com.youppix.ecommercecourse.presentation.home_app.components.ColorItem
import com.youppix.ecommercecourse.presentation.home_app.search.SearchEvent
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Stable
@Composable
fun FilteringBottomSheet(
    allColors: List<ColorData>,
    filteringItems: FilteringItems,
    event: (SearchEvent) -> Unit,
    onDismissRequest: () -> Unit
) {


    ModalBottomSheet(
        onDismissRequest = { onDismissRequest() },
        containerColor = MaterialTheme.colorScheme.background
    ) {
        LazyColumn(
            modifier = Modifier.padding(
                bottom = ScaffoldDefaults.contentWindowInsets
                    .asPaddingValues()
                    .calculateBottomPadding(),
            )
        ) {

            item {
                ResetConfirmRow(onConfirmCLick = {
                    event(SearchEvent.UpdateFilteringItems(filteringItems))
                    onDismissRequest()
                }, onResetClick = {
                    event(SearchEvent.UpdateFilteringItems(FilteringItems()))
                    onDismissRequest()
                })
            }

            item {
                SelectPriceSection(filteringItems, event)
            }

            item {
                SelectDiscountSection(filteringItems, event)
            }

            item {
                SelectColorSection(allColors, filteringItems)
            }


        }
    }
}

@Stable
@Composable
fun SelectDiscountSection(
    filteringItems: FilteringItems,
    event: (SearchEvent) -> Unit
) {

    var discountRange by remember { mutableStateOf(0f..100f) }

    LaunchedEffect(Unit) {
        discountRange =
            filteringItems.initialDiscount.toFloat()..filteringItems.finalDiscount.toFloat()
    }

    Text(
        text = stringResource(id = R.string.selectDiscount) + " :",
        style = MaterialTheme.typography.titleSmall.copy(
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start
        ),

        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = MediumPadding.plus(ExtraSmallPadding2), vertical = SmallPadding)
    )

    Column(Modifier.fillMaxWidth()) {

        Row(
            Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = stringResource(id = R.string.min),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(end = SmallPadding)
            )
            Row(
                modifier = Modifier
                    .border(
                        2.dp, MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(SmallPadding)
                    )
                    .padding(ExtraSmallPadding)
            ) {
                Text(
                    text = "${discountRange.start.toInt()}",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(horizontal = ExtraSmallPadding2)
                )


                Text(
                    text = " %",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(horizontal = ExtraSmallPadding2)
                )

            }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = stringResource(id = R.string.max),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(end = SmallPadding)
            )

            Row(
                modifier = Modifier
                    .border(
                        2.dp, MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(SmallPadding)
                    )
                    .padding(ExtraSmallPadding)
            ) {
                Text(
                    text = "${discountRange.endInclusive.toInt()}",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(horizontal = ExtraSmallPadding2)
                )


                Text(
                    text = " %",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(horizontal = ExtraSmallPadding2)
                )

            }
            Spacer(modifier = Modifier.weight(1f))
        }
        RangeSlider(
            modifier = Modifier.padding(horizontal = MediumPadding*2),
            value = discountRange,
            valueRange = 0f..100f,
            steps = 9,
            onValueChange = {
                discountRange = it
            },
            onValueChangeFinished = {
                event(
                    SearchEvent.UpdateFilteringItems(
                        filteringItems = filteringItems.copy(
                            initialDiscount = discountRange.start.toInt(),
                            finalDiscount = discountRange.endInclusive.toInt()
                        ),
                        sendRequest = false
                    )
                )
                Log.d(
                    "FilteringBottomSheet",
                    "Filtering Items: ${filteringItems.initialDiscount} .. ${filteringItems.finalDiscount}"
                )
            }
        )
    }
}

@Stable
@Composable
fun SelectPriceSection(
    filteringItems: FilteringItems,
    event: (SearchEvent) -> Unit
) {

    var priceRange by remember { mutableStateOf(filteringItems.minPrice.toFloat()..filteringItems.maxPrice.toFloat()) }

    LaunchedEffect(Unit) {
        priceRange = filteringItems.initialPrice.toFloat()..filteringItems.finalPrice.toFloat()
    }
    val isArabic = Locale.getDefault().language == "ar"
    Text(
        text = stringResource(id = R.string.selectPrice) + " :",
        style = MaterialTheme.typography.titleSmall.copy(
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start
        ),

        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = MediumPadding.plus(ExtraSmallPadding2), vertical = SmallPadding)
    )

    Column(Modifier.fillMaxWidth()) {

        Row(
            Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = stringResource(id = R.string.min),
                style =  MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(end = SmallPadding)
            )
            Row(
                modifier = Modifier
                    .border(
                        2.dp, MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(SmallPadding)
                    )
                    .padding(ExtraSmallPadding)
            ) {
                Text(
                    text = "${priceRange.start.toInt()}",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(horizontal = ExtraSmallPadding2)
                )


                Text(
                    text = stringResource(id = R.string.da),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(horizontal = ExtraSmallPadding2)
                )

            }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = stringResource(id = R.string.max),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(end = SmallPadding)
            )

            Row(
                modifier = Modifier
                    .border(
                        2.dp, MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(SmallPadding)
                    )
                    .padding(ExtraSmallPadding)
            ) {
                Text(
                    text = "${priceRange.endInclusive.toInt()}",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(horizontal = ExtraSmallPadding2)
                )


                Text(
                    text = stringResource(id = R.string.da),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(horizontal = ExtraSmallPadding2)
                )

            }
            Spacer(modifier = Modifier.weight(1f))
        }
        RangeSlider(
            modifier = Modifier.padding(horizontal = MediumPadding * 2),
            value = priceRange,
            valueRange = filteringItems.minPrice.toFloat()..filteringItems.maxPrice.toFloat(),
            steps = 19,
            onValueChange = {
                priceRange = it
            },
            onValueChangeFinished = {
                event(
                    SearchEvent.UpdateFilteringItems(
                        filteringItems = filteringItems.copy(
                            initialPrice = priceRange.start.toInt(),
                            finalPrice = priceRange.endInclusive.toInt()
                        ),
                        sendRequest = false
                    )
                )
                Log.d(
                    "FilteringBottomSheet",
                    "Filtering Items: ${filteringItems.initialPrice} .. ${filteringItems.finalPrice}"
                )
            }
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Stable
@Composable
private fun SelectColorSection(
    allColors: List<ColorData>,
    filteringItems: FilteringItems,
) {
    Text(
        text = stringResource(id = R.string.choseColors) + " :",
        style = MaterialTheme.typography.titleSmall.copy(
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = MediumPadding.plus(ExtraSmallPadding2))
    )
    FlowRow(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = MediumPadding, vertical = SmallPadding),
        horizontalArrangement = Arrangement.Center,

        ) {
        allColors.forEach { color ->
            ColorItem(
                modifier = Modifier.padding(ExtraSmallPadding),
                color = color,
                filteringItemsColors = filteringItems.colors
            ) {
                if (filteringItems.colors.contains(color)) {
                    filteringItems.colors.remove(color)
                } else {
                    filteringItems.colors.add(color)
                }
            }
        }
    }
}

@Stable
@Composable
private fun ResetConfirmRow(onConfirmCLick: () -> Unit, onResetClick: () -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = MediumPadding),
        horizontalArrangement = Arrangement.spacedBy(SmallPadding)
    ) {
        Button(
            onClick = { onResetClick() }, shape = CircleShape, colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.09f)
            ), modifier = Modifier.weight(1f)
        ) {
            Text(
                text = stringResource(id = R.string.resetFilter),
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary
                ),
            )
        }

        Button(
            onClick = { onConfirmCLick() },
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            ),
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = stringResource(id = R.string.confirm),
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.background
                ),
            )
        }

    }
}