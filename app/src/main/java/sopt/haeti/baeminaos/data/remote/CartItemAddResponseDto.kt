package sopt.haeti.baeminaos.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CartItemAddResponseDto (
    @SerialName("status")
    val status : Int,
    @SerialName("message")
    val message : String,
    @SerialName("data")
    val data : Data
        ) {
    @Serializable
    data class Data(
        @SerialName("cartItemId")
        val cartItemId : Int,
        @SerialName("name")
        val name : String,
        @SerialName("count")
        val count : Int,
        @SerialName("price")
        val price : Int
    )
}
