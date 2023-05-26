package sopt.haeti.baeminaos.data.service

import retrofit2.http.GET
import sopt.haeti.baeminaos.data.remote.StoreListResponseDto

interface HomeStoreListService {

    @GET("store")
    suspend fun getStoreList(): StoreListResponseDto
}