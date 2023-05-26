package sopt.haeti.baeminaos.data.service

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import sopt.haeti.baeminaos.data.remote.*

interface CartService {

    @GET("cart")
    fun getList(): Call<CartItemResponseDto>

    @PATCH("cart")
    fun updateCount(
        @Body request: CartCountRequestDto,
    ): Call<CartCountResponseDto>

    @DELETE("cart/{cartItemId}")
    fun deleteCart(
        @Path("cartItemId") cartItemId: Int
    ): Call<CartDeleteResponseDto>

    @POST("cart/{cartId}")
    fun order(
        @Path("cartId") cartId: Int
    ): Call<CartOrderResponseDto>
}