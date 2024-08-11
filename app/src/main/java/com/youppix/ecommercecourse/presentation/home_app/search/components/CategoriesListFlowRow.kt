package com.youppix.ecommercecourse.presentation.home_app.search.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import com.youppix.ecommercecourse.common.Dimens
import com.youppix.ecommercecourse.domain.model.categories.Category
import com.youppix.ecommercecourse.presentation.home_app.components.CategoriesListItem
import com.youppix.ecommercecourse.presentation.home_app.search.SearchEvent

@Stable
@Composable
fun CategoriesListFlowRow(
    list: List<Category>,
    currentCategory: Int?,
    isArabic: Boolean,
    searchEvent: (SearchEvent) -> Unit
) {

    LazyRow(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(
                start = Dimens.MediumPadding,
                top = Dimens.SmallPadding,
                bottom = Dimens.SmallPadding
            )
    ) {
        items(list.size , key = {it}){index->
            val category = list[index]
            CategoriesListItem(
                name = if (isArabic) category.nameAr else category.name,
                id = index,
                selected = index == (currentCategory ?: 0)
            ) {
                searchEvent(SearchEvent.UpdateCategorySelected((it)))
            }
        }
    }


}