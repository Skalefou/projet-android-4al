package com.groupe1.app_android.networks

import com.groupe1.app_android.BuildConfig
import com.groupe1.app_android.data.remote.services.UserApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModule {

    private const val BASE_URL = "http://localhost:8080"

    val api: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(
            OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(Level.BASIC))
                .build()
        )
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }

    private val client by lazy {
        OkHttpClient.Builder().apply {
            if (BuildConfig.DEBUG) {
                val logger = HttpLoggingInterceptor()
                logger.level = HttpLoggingInterceptor.Level.BODY
                addInterceptor(logger)
            }
        }.build()
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL.trimEnd('/') + "/")
            .client(client)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    val userApi: UserApi by lazy { retrofit.create(UserApi::class.java) }

    val searchBarCityApi : Retrofit = Retrofit.Builder()
        .baseUrl("https://api.mapbox.com/")
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()
}
