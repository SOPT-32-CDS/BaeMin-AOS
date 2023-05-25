package sopt.haeti.baeminaos.data.service

import retrofit2.Call
import retrofit2.http.GET
import sopt.haeti.baeminaos.data.remote.CartItemResponseDto

interface CartService {

    @GET("/cart")
    fun getList(): Call<CartItemResponseDto>
}