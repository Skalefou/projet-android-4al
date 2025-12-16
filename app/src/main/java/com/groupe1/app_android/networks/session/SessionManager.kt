package com.groupe1.app_android.networks.session

class SessionManager(
    private val tokenProvider: TokenProvider
) {
    fun getAccessToken(): String? = tokenProvider.getAccessToken()
    fun getRefreshToken(): String? = tokenProvider.getRefreshToken()

    fun updateTokens(access: String, refresh: String) {
        tokenProvider.setTokens(access, refresh)
    }
}
