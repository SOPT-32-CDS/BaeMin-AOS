package sopt.haeti.baeminaos.module

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import sopt.haeti.baeminaos.BuildConfig
import sopt.haeti.baeminaos.BuildConfig.BAEMIN_BASE_URL
import sopt.haeti.baeminaos.data.service.StoreService

object StoreApiFactory {

    private val client by lazy {
        OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
            level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }).build()
    }

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BAEMIN_BASE_URL)
            .client(client)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    inline fun <reified T> create(): T = retrofit.create<T>(T::class.java)
}

object StoreServicePool {
    val storeService = StoreApiFactory.create<StoreService>()
}