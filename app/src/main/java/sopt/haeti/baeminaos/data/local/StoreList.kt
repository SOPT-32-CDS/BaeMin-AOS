package sopt.haeti.baeminaos.data.local

data class StoreList(
    val id : Int,
    val delivery_fee: Int,
    val image: String,
    val max_delivery_time: Int,
    val min_delivery_time: Int,
    val min_order_amount: Int,
    val name: String,
    val rate: Double
)