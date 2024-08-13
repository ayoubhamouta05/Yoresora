package com.youppix.ecommercecourse.domain.useCases.details

data class DetailsUseCases (
    val getItemDetails: GetItemDetailsUseCase,
    val addOrDeleteFromFavorite : AddOrDeleteFromFavoriteUseCase ,
    val checkSizeExistence : CheckSizeExistenceUseCase,
    val upsertCustomSize: UpsertCustomSizeUseCase
)