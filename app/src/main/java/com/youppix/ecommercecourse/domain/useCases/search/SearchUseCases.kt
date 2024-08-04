package com.youppix.ecommercecourse.domain.useCases.search

data class SearchUseCases(
    val getAllCategories: GetAllCategoriesUseCase,
    val getItemsByFiltering: GetItemsByFilteringUseCase,
    val getAllColors : GetAllColorsUseCase
)
