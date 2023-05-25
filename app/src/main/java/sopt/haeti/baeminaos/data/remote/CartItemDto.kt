package sopt.haeti.baeminaos.data.remote

import kotlinx.serialization.Serializable

data class CartItemDto(
    val status: Int,
    val message: String,
    val data: List<Cart>
) {
    @Serializable
    data class Cart(
        val cartId: Int,
        val totalPrice: Int,
        val deliveryFee: Int,
        val cartStoreList: List<CartStore>
    ) {
        @Serializable
        data class CartStore(
            val cartStoreId: Int,
            val name: String,
            val image: String,
            val cartItemList: List<CartItem>
        ) {
            @Serializable
            data class CartItem(
                val cartItemId: Int,
                val name: String,
                val totalPrice: Int,
                val options: String,
                val count: Int
            )
        }
    }
}