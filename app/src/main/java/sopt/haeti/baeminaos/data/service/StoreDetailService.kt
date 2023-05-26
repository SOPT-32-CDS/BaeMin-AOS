package sopt.haeti.baeminaos.data.service

import retrofit2.Call
import retrofit2.http.GET
import sopt.haeti.baeminaos.data.remote.StoreDetailResponseDto

interface StoreDetailService {

    @GET("store/1")
    fun getStoreDetail(): Call<StoreDetailResponseDto>
}