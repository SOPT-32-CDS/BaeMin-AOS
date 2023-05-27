package sopt.haeti.baeminaos.data.service

import retrofit2.Call
import retrofit2.http.*
import sopt.haeti.baeminaos.data.remote.*

interface CartService {

    @GET("cart")
    fun getList(
        @Header("authorization") token : String = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MSwiYWRkcmVzcyI6IuyGoe2MjOq1rCDsmKzrprztlL3roZwgMTM1In0.hh0main0EWtZYLHWlO3GfdrDgPDaAipNTkBlxgc5KSY"
    ): Call<CartItemResponseDto>

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