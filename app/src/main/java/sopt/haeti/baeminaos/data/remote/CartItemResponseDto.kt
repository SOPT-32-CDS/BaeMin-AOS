package sopt.haeti.baeminaos.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class CartItemResponseDto(
    @SerialName("status")
    val status: Int,
    @SerialName("message")
    val message: String,
    @SerialName("data")
    val data: List<Cart>
) {
    @Serializable
    data class Cart(
        @SerialName("cart_id")
        val cartId: Int,
        @SerialName("total_price")
        val totalPrice: Int,
        @SerialName("delivery_fee")
        val deliveryFee: Int,
        @SerialName("cart_store_list")
        val cartStoreList: List<CartStore>
    ) {
        @Serializable
        data class CartStore(
            @SerialName("cart_store_id")
            val cartStoreId: Int,
            @SerialName("name")
            val name: String,
            @SerialName("image")
            val image: String,
            @SerialName("cart_item_list")
            val cartItemList: List<CartItem>
        ) {
            @Serializable
            data class CartItem(
                @SerialName("cart_item_id")
                val cartItemId: Int,
                @SerialName("name")
                val name: String,
                @SerialName("total_price")
                val totalPrice: Int,
                @SerialName("options")
                val options: String,
                @SerialName("count")
                val count: Int
            )
        }
    }
}