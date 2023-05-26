package sopt.haeti.baeminaos.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StoreDetailResponseDto(
    @SerialName("data")
    val datas: Data,
    @SerialName("message")
    val message: String,
    @SerialName("status")
    val status: Int
) {
    @Serializable
    data class Data(
        @SerialName("delivery_fee")
        val delivery_fee: Int,
        @SerialName("id")
        val id: Int,
        @SerialName("image")
        val image: String,
        @SerialName("max_delivery_time")
        val max_delivery_time: Int,
        @SerialName("menu_categories")
        val menu_categories: List<MenuCategory>,
        @SerialName("coupon")
        val coupon: String = "direct-coupon",
        @SerialName("min_delivery_time")
        val min_delivery_time: Int,
        @SerialName("min_order_amount")
        val min_order_amount: Int,
        @SerialName("name")
        val name: String,
        @SerialName("rate")
        val rate: Double
    ) {
        @Serializable
        data class MenuCategory(
            @SerialName("id")
            val id: Int,
            @SerialName("menus")
            val menus: List<Menu>,
            @SerialName("name")
            val name: String,
            var isExpanded: Boolean = false
        ) {
            @Serializable
            data class Menu(
                @SerialName("base_price")
                val base_price: Int,
                @SerialName("description")
                val description: String,
                @SerialName("id")
                val id: Int,
                @SerialName("image")
                val image: String,
                @SerialName("name")
                val name: String
            )
        }
    }
}