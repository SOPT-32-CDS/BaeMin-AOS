package sopt.haeti.baeminaos.presentation.storedetail


import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import sopt.haeti.baeminaos.data.remote.CartItemAddRequestDto
import sopt.haeti.baeminaos.data.remote.CartItemAddResponseDto
import sopt.haeti.baeminaos.data.remote.StoreDetailData

interface StoreDetailService {

    @GET("/menu/1")
    fun getMenu(): Call<StoreDetailData>

    @POST("/cart")
    fun addCart(
        @Body request: CartItemAddRequestDto
    ): Call<CartItemAddResponseDto>

}