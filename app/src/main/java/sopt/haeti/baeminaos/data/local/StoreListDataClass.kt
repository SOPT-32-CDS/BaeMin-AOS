package sopt.haeti.baeminaos.data.local

data class StoreListDataClass(
    val id: String,
    val name: String,
    // 서버 통신 시 URl의 String 값으로 수정
    val image: Int,
    val rate: Double,
    val minOrderAmount: Int,
    val minDeliveryFee: Int,
    val minDeliveryTime: Int,
    val maxDeliveryTime: Int,
    val hasCoupon: String
    )