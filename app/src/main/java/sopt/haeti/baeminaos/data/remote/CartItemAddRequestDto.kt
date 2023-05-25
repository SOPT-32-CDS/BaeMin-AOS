package sopt.haeti.baeminaos.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CartItemAddRequestDto (
    @SerialName("cartId")
    val cartId : Int,
    @SerialName("storeId")
    val storeId : Int,
    @SerialName("name")
    val name : String,
    @SerialName("image")
    val image : String,
    @SerialName("totalPrice")
    val totalPrice : Int,
    @SerialName("options")
    val options : String,
    @SerialName("count")
    val count : Int
        )
