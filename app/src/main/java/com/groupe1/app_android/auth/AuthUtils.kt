package com.groupe1.app_android.auth

import android.util.Base64
import org.json.JSONObject

fun isJwtValid(refreshToken: String?): Boolean {
    if (refreshToken == null) {
        return false
    }

    return try {
        val parts = refreshToken.split(".")
        if (parts.size != 3) return false

        val payloadBytes = Base64.decode(
            parts[1],
            Base64.URL_SAFE or Base64.NO_PADDING or Base64.NO_WRAP
        )
        val payloadJson = String(payloadBytes, Charsets.UTF_8)
        val payload = JSONObject(payloadJson)

        val expSeconds = payload.optLong("exp", 0L)
        if (expSeconds == 0L) {
            return false
        }

        val nowSeconds = System.currentTimeMillis() / 1000
        expSeconds > nowSeconds
    } catch (e: Exception) {
        false
    }
}