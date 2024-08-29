package com.youppix.ecommercecourse.presentation.home_app.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.youppix.ecommercecourse.common.Dimens.ExtraSmallPadding
import com.youppix.ecommercecourse.common.Dimens.ExtraSmallPadding2
import com.youppix.ecommercecourse.common.Dimens.MediumPadding
import com.youppix.ecommercecourse.common.Dimens.SmallPadding
import com.youppix.ecommercecourse.presentation.ui.theme.EcommerceCourseTheme

@Stable
@Composable
fun CategoriesListItem(
    name: String,
    id: Int,
    selected: Boolean,
    onClick: (Int) -> Unit
) {

    Box(
        modifier = Modifier
            .padding(end = MediumPadding)
            .clip(RoundedCornerShape(SmallPadding))
            .clickable {
                onClick(id)
            }
            .background(
                color = if (selected) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.background
            )
            .border(0.5.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(SmallPadding))
    ) {
        Text(
            modifier = Modifier.padding(horizontal = MediumPadding, vertical = ExtraSmallPadding),
            text = name,
            color = if (selected) MaterialTheme.colorScheme.background
            else MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.bodySmall.copy(
                fontWeight = FontWeight.SemiBold
            )
        )
    }

}


@Stable
@Composable
fun SizesListItem(
    name: String,
    id: Int,
    selected: Boolean,
    onClick: (Int) -> Unit
) {

    Box(
        modifier = Modifier
            .padding(end = MediumPadding)
            .clip(RoundedCornerShape(SmallPadding))
            .clickable {
                onClick(id ) // id's starts from 1 but index starts from 0
            }
            .background(
                color = if (selected) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.background
            )
            .border(0.5.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(SmallPadding))
    ) {
        Text(
            modifier = Modifier.padding(horizontal = MediumPadding),
            text = name,
            color = if (selected) MaterialTheme.colorScheme.background
            else MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.bodyMedium
        )
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CategoriesListItemPreview() {
    EcommerceCourseTheme {
        LazyRow(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(SmallPadding),
            horizontalArrangement = Arrangement.Center
        ) {
            item {
                CategoriesListItem(name = "All", selected = false, id = 0) {}
            }
            item {
                CategoriesListItem(name = "Men", selected = false, id = 1) {}
            }
            item {
                CategoriesListItem(name = "Women", selected = true, id = 2) {}
            }
            item {
                CategoriesListItem(name = "Kids", selected = false, id = 3) {}
            }
        }
    }


}