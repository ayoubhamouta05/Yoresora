package com.youppix.ecommercecourse.domain.useCases.home

data class HomeUseCases(
    val getHomeData: GetHomeDataUseCase ,
    val getAllItems: GetAllItemsUseCase ,
    val getItemsByCategory : GetItemsByCategoryUseCase
)
