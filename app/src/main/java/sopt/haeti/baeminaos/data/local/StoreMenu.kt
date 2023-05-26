package sopt.haeti.baeminaos.data.local

data class StoreMenu(
    var menuName: String = "",
    var isExpanded: Boolean = false,
    val storeMenuDetail: List<StoreMenuDetail>
)

data class StoreMenuDetail(
    val menuDetailName: String,
    val menuDescription: String,
    val menuPrice: Int,
    val menuImage: String,
)
