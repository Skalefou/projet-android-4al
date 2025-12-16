package com.groupe1.app_android.networks.session

object TokenProvider {
    @Volatile private var accessToken: String? = null
    @Volatile private var refreshToken: String? = null

    fun setTokens(access: String?, refresh: String?) {
        accessToken = access
        refreshToken = refresh
    }

    fun getAccessToken(): String? = accessToken
    fun getRefreshToken(): String? = refreshToken
}

