package com.groupe1.app_android.networks.session

import com.groupe1.app_android.data.remote.services.RefreshRequest
import com.groupe1.app_android.data.remote.services.UserApi
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(
    private val refreshApi: UserApi
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        //Skip: attach token on auth endpoints
        val path = original.url.encodedPath
        if (path.contains("/api/users/login") ||
            path.contains("/api/users/register") ||
            path.contains("/api/users/refresh")) {
            return chain.proceed(original)
        }

        val access = TokenProvider.getAccessToken()
        val firstRequest = if (!access.isNullOrBlank()) {
            original.newBuilder()
                .header("Authorization", "Bearer $access")
                .build()
        } else original

        val firstResponse = chain.proceed(firstRequest)

        if (firstResponse.code != 401) return firstResponse

        firstResponse.close()

        val refresh = TokenProvider.getRefreshToken()
        if (refresh.isNullOrBlank()) return firstResponse

        val newPair = try {
            runBlocking { refreshApi.refresh(RefreshRequest(refresh)) }
        } catch (e: Exception) {
            return firstResponse
        }

        TokenProvider.setTokens(newPair.access, newPair.refresh)

        val retry = original.newBuilder()
            .header("Authorization", "Bearer ${newPair.access}")
            .build()

        return chain.proceed(retry)
    }
}
