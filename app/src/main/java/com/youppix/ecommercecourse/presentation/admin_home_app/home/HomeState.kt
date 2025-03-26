package com.youppix.ecommercecourse.presentation.admin_home_app.home

import com.youppix.ecommercecourse.domain.model.items.Item

data class HomeState(
    val isLoading : Boolean = false ,
    val bestSellingItems : List<Item> = emptyList()
)
