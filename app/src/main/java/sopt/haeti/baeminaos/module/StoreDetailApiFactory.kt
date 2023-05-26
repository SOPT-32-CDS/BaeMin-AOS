package sopt.haeti.baeminaos.module

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import sopt.haeti.baeminaos.BuildConfig

object StoreDetailApiFactory {

    private const val BASE_URL = BuildConfig.BAEMIN_BASE_URL

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    inline fun <reified T> create(): T = retrofit.create<T>(T::class.java)
}

object StoreDetailServicePool {
    val storeDetailService = StoreDetailApiFactory.create<StoreDetailService>()
    val cartItemAddService = StoreDetailApiFactory.create<StoreDetailService>()
}