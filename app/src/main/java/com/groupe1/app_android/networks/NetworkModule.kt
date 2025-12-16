package com.groupe1.app_android.networks

import com.groupe1.app_android.BuildConfig
import com.groupe1.app_android.data.remote.services.UserApi
import com.groupe1.app_android.networks.session.AuthInterceptor
import com.groupe1.app_android.networks.session.TokenProvider
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModule {

    // For user APIs that don't need authorization header, skip interceptor
    private val userClient by lazy {
        OkHttpClient.Builder().apply {
            if (BuildConfig.DEBUG) {
                val logger = HttpLoggingInterceptor()
                logger.level = Level.BODY
                addInterceptor(logger)
            }
        }.build()
    }

    private val userRetrofitInstance: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL.trimEnd('/') + "/")
            .client(userClient)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    val userApi: UserApi by lazy { userRetrofitInstance.create(UserApi::class.java) }


    // For search bar call api mapbox
    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }

    val searchBarCityApi : Retrofit = Retrofit.Builder()
        .baseUrl("https://api.mapbox.com/")
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()

    // For other api calls to back end that need authorization header
    val client = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor(userApi))
        .addInterceptor(HttpLoggingInterceptor().setLevel(Level.HEADERS))
        .build()

    val api: Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL.trimEnd('/') + "/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}
