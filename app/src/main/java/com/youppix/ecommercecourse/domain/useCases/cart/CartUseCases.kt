package com.youppix.ecommercecourse.domain.useCases.cart

data class CartUseCases(
    val addOrDeleteCart: AddOrDeleteCartUseCase,
    val getCarts: GetCartsUseCase,
)