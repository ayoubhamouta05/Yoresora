package com.youppix.ecommercecourse.presentation.home_app.cart

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import cafe.adriel.voyager.core.model.ScreenModel
import com.youppix.ecommercecourse.domain.model.CartItemData
import com.youppix.ecommercecourse.domain.model.items.ColorData
import javax.inject.Inject

class CartViewModel @Inject constructor(
) : ScreenModel {

    private var _state = mutableStateOf(CartState())
    val state : State<CartState> = _state

    init {
        _state.value = state.value.copy(
            cartItems = listOf(
                CartItemData(
                itemId = 13,
                itemName = "Nike Air Max",
                itemColor = ColorData(
                    colors_name = "Black",
                    colors_hex = "#000000",
                    colors_id = 1,
                    colors_name_ar = "اسود"
                ),
                itemPrice = 5500,
                itemSize = "42",
                itemImage = "product_example2.jpg",
                quantity = 1,
            ),
                CartItemData(
                    itemId = 12,
                    itemName = "Nike Air Max",
                    itemColor = ColorData(
                        colors_name = "Black",
                        colors_hex = "#000000",
                        colors_id = 1,
                        colors_name_ar = "اسود"
                    ),
                    itemPrice = 5500,
                    itemSize = "42",
                    itemImage = "product_example2.jpg",
                    quantity = 1,
                ),
                CartItemData(
                    itemId = 11,
                    itemName = "Nike Air Max",
                    itemColor = ColorData(
                        colors_name = "Black",
                        colors_hex = "#000000",
                        colors_id = 1,
                        colors_name_ar = "اسود"
                    ),
                    itemPrice = 5500,
                    itemSize = "42",
                    itemImage = "product_example2.jpg",
                    quantity = 1,
                ),
                CartItemData(
                    itemId = 10,
                    itemName = "Nike Air Max",
                    itemColor = ColorData(
                        colors_name = "Black",
                        colors_hex = "#000000",
                        colors_id = 1,
                        colors_name_ar = "اسود"
                    ),
                    itemPrice = 5500,
                    itemSize = "42",
                    itemImage = "product_example2.jpg",
                    quantity = 1,
                ),
                CartItemData(
                    itemId = 9,
                    itemName = "Nike Air Max",
                    itemColor = ColorData(
                        colors_name = "Black",
                        colors_hex = "#000000",
                        colors_id = 1,
                        colors_name_ar = "اسود"
                    ),
                    itemPrice = 5500,
                    itemSize = "42",
                    itemImage = "product_example2.jpg",
                    quantity = 1,
                ),
                CartItemData(
                    itemId = 8,
                    itemName = "Nike Air Max",
                    itemColor = ColorData(
                        colors_name = "Black",
                        colors_hex = "#000000",
                        colors_id = 1,
                        colors_name_ar = "اسود"
                    ),
                    itemPrice = 5500,
                    itemSize = "42",
                    itemImage = "product_example2.jpg",
                    quantity = 1,
                ),
                CartItemData(
                    itemId = 7,
                    itemName = "Nike Air Max",
                    itemColor = ColorData(
                        colors_name = "Black",
                        colors_hex = "#000000",
                        colors_id = 1,
                        colors_name_ar = "اسود"
                    ),
                    itemPrice = 5500,
                    itemSize = "42",
                    itemImage = "product_example2.jpg",
                    quantity = 1,
                ),
                CartItemData(
                    itemId = 6,
                    itemName = "Nike Air Max",
                    itemColor = ColorData(
                        colors_name = "Black",
                        colors_hex = "#000000",
                        colors_id = 1,
                        colors_name_ar = "اسود"
                    ),
                    itemPrice = 5500,
                    itemSize = "42",
                    itemImage = "product_example2.jpg",
                    quantity = 1,
                ),
                CartItemData(
                    itemId = 5,
                    itemName = "Nike Air Max",
                    itemColor = ColorData(
                        colors_name = "Black",
                        colors_hex = "#000000",
                        colors_id = 1,
                        colors_name_ar = "اسود"
                    ),
                    itemPrice = 5500,
                    itemSize = "42",
                    itemImage = "product_example2.jpg",
                    quantity = 1,
                ),
                CartItemData(
                    itemId = 4,
                    itemName = "Nike Air Max",
                    itemColor = ColorData(
                        colors_name = "Black",
                        colors_hex = "#000000",
                        colors_id = 1,
                        colors_name_ar = "اسود"
                    ),
                    itemPrice = 5500,
                    itemSize = "42",
                    itemImage = "product_example2.jpg",
                    quantity = 1,
                ),
                CartItemData(
                    itemId = 2,
                    itemName = "Nike Air Max",
                    itemColor = ColorData(
                        colors_name = "Black",
                        colors_hex = "#000000",
                        colors_id = 1,
                        colors_name_ar = "اسود"
                    ),
                    itemPrice = 5500,
                    itemSize = "42",
                    itemImage = "product_example2.jpg",
                    quantity = 1,
                ),
                CartItemData(
                    itemId = 2,
                    itemName = "Nike Air Max",
                    itemColor = ColorData(
                        colors_name = "Black",
                        colors_hex = "#000000",
                        colors_id = 1,
                        colors_name_ar = "اسود"
                    ),
                    itemPrice = 5500,
                    itemSize = "42",
                    itemImage = "product_example2.jpg",
                    quantity = 1,
                ),

            )
        )
    }

    fun onEvent (event: CartEvent){
        when (event){
            is CartEvent.OnPromoCodeChange -> {
                _state.value = state.value.copy(
                    promoCode = event.promoCode
                )
            }
        }
    }

}