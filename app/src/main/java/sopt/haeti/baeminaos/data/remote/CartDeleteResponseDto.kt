package sopt.haeti.baeminaos.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CartDeleteResponseDto(
    @SerialName("status")
    val status: Int,
    @SerialName("message")
    val message: String,
    @SerialName("data")
    val data: Data
) {
    @Serializable
    data class Data(
        @SerialName("cart_item_id")
        val cartItemId: Int,
        @SerialName("count")
        val count: Int,
        @SerialName("name")
        val name: String,
        @SerialName("price")
        val price: Int
    )
}