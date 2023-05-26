package sopt.haeti.baeminaos.data.service

import retrofit2.http.GET
import sopt.haeti.baeminaos.data.remote.StoreDetailResponseDto

interface StoreService {

    @GET("store/1")
    suspend fun getStoreDetail(): StoreDetailResponseDto
}