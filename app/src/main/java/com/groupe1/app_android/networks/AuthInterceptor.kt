package com.groupe1.app_android.networks

import androidx.datastore.core.DataStore
import com.groupe1.app_android.auth.UserPreferences
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class AccessTokenInterceptor(
    private val dataStore: DataStore<UserPreferences>
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val token = runBlocking {
            dataStore.data.first().accessToken
        }

        if (token.isNullOrBlank()) {
            return chain.proceed(request)
        }

        val newRequest = request.newBuilder()
            .addHeader("Authorization", "Bearer $token")
            .build()

        return chain.proceed(newRequest)
    }
}
