package sopt.haeti.baeminaos.data.service

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import sopt.haeti.baeminaos.data.remote.CartCountRequestDto
import sopt.haeti.baeminaos.data.remote.CartCountResponseDto
import sopt.haeti.baeminaos.data.remote.CartItemResponseDto

interface CartService {

    @GET("/cart")
    fun getList(): Call<CartItemResponseDto>

    @PATCH("/cart")
    fun updateCount(
        @Body request: CartCountRequestDto,
    ): Call<CartCountResponseDto>
}