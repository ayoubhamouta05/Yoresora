package com.youppix.ecommercecourse.domain.model.home

import com.youppix.ecommercecourse.domain.model.categories.Category
import com.youppix.ecommercecourse.domain.model.items.Item

data class Home (
    val categories: List<Category>,
    val itemData: List<Item>,
    val status: String
)