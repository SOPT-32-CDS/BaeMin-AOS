package sopt.haeti.baeminaos.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CartCountRequestDto(
    @SerialName("cart_item_id")
    val cartItemId: Int,
    @SerialName("count")
    val count: Int
)