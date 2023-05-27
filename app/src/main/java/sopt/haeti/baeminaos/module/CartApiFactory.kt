package sopt.haeti.baeminaos.module

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import sopt.haeti.baeminaos.BuildConfig
import sopt.haeti.baeminaos.data.service.CartService

object CartApiFactory {
    private const val BASE_URL = BuildConfig.BAEMIN_BASE_URL

    private val client by lazy {
        OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
            level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }).addInterceptor(AddingMobileTokenInterceptor()).build()
    }

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .client(client)
            .build()
    }

    inline fun <reified T> create(): T = retrofit.create<T>(T::class.java)
}

object ServicePool {
    val cartService = CartApiFactory.create<CartService>()
}

class AddingMobileTokenInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token =
            "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MSwiYWRkcmVzcyI6IuyGoe2MjOq1rCDsmKzrprztlL3roZwgMTM1In0.hh0main0EWtZYLHWlO3GfdrDgPDaAipNTkBlxgc5KSY"

        val tokenAddedRequest =
            chain.request().newBuilder().addHeader("authorization", token).build()

        return chain.proceed(tokenAddedRequest)
    }
}