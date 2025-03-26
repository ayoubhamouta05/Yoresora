package com.youppix.ecommercecourse.domain.useCases.cart

data class CartUseCases(
    val getCartItems: GetCartItemsUseCase,
    val updateQuantity: UpdateQuantityUseCase ,
    val checkCodePromo : CheckCodePromoUseCase
)