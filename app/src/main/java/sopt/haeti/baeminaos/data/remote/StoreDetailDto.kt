package sopt.haeti.baeminaos.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StoreDetailData(
    @SerialName("status")
    var status: Int,
    @SerialName("message")
    var message: String,
    @SerialName("data")
    var data: Data
) {
    @Serializable
    data class Data(
        @SerialName("id")
        var id: Int,
        @SerialName("name")
        var name: String,
        @SerialName("description")
        var description: String,
        @SerialName("image")
        var image: String,
        @SerialName("base_price")
        var basePrice: Int,
        @SerialName("option_categories")
        var optionCategories: ArrayList<OptionCategories>
    ) {
        @Serializable
        data class OptionCategories(
            @SerialName("id")
            var id: Int,
            @SerialName("name")
            var name: String,
            @SerialName("description")
            var description: String,
            @SerialName("options")
            var options: ArrayList<Options>
        ) {
            @Serializable
            data class Options(
                @SerialName("id")
                var id: Int,
                @SerialName("name")
                var name: String,
                @SerialName("price")
                var price: Int
            )
        }
    }
}