package sopt.haeti.baeminaos.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import sopt.haeti.baeminaos.data.local.StoreList

@Serializable
data class StoreListResponseDto(
    @SerialName("data")
    val `data`: List<Data>,
    @SerialName("message")
    val message: String,
    @SerialName("status")
    val status: Int
) {
    @Serializable
    data class Data(
        @SerialName("coupon")
        val coupon : String? = "direct-coupon",
        @SerialName("delivery_fee")
        val delivery_fee: Int,
        @SerialName("id")
        val id: Int,
        @SerialName("image")
        val image: String,
        @SerialName("max_delivery_time")
        val max_delivery_time: Int,
        @SerialName("min_delivery_time")
        val min_delivery_time: Int,
        @SerialName("min_order_amount")
        val min_order_amount: Int,
        @SerialName("name")
        val name: String,
        @SerialName("rate")
        val rate: Double
    )

    fun toStoreList() = data.map { storeInfo ->
        StoreList(
            id = storeInfo.id,
            delivery_fee = storeInfo.delivery_fee,
            image = storeInfo.image,
            max_delivery_time = storeInfo.max_delivery_time,
            min_delivery_time = storeInfo.min_delivery_time,
            min_order_amount = storeInfo.min_order_amount,
            name = storeInfo.name,
            rate = storeInfo.rate
        )
    }
}