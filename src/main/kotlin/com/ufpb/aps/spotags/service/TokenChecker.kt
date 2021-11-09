package com.ufpb.aps.spotags.service

import com.ufpb.aps.spotags.env.Variables
import org.jboss.logging.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient

@Component
class TokenChecker(
        @Autowired
        val env: Variables,
        val spotifyClient: SpotifyClient
) {

    companion object {
        var ACCESS_TOKEN: String? = null
        var REFRESH_TOKEN: String? = null
        var EXPIRES_IN: Int = 0
        var AUTHORIZED: Boolean = false
    }

    val log = Logger.getLogger(TokenChecker::class.java)

    @Scheduled(fixedRate = 100000L)
    fun checkToken() {
        ACCESS_TOKEN?.let {
            when(EXPIRES_IN <= 100) {
                true -> refreshAccessToken()
                false -> EXPIRES_IN -= 100
            }
        }
    }

    fun refreshAccessToken() {
        log.info("generating a new access token")

        var params = "?grant_type=${env.GRANT_TYPE_REFRESH}"
        params += "&refresh_token=${REFRESH_TOKEN}"
        params += "&client_id=${env.CLIENT_ID}"

        try {
            val tokenResponse = spotifyClient.generateTokenResponse(params)
            ACCESS_TOKEN = tokenResponse!!.accessToken
            EXPIRES_IN = tokenResponse.expiresIn
            AUTHORIZED = true

        } catch(e: Exception) {
            ACCESS_TOKEN = null
            AUTHORIZED = false
            log.error("something went wrong while refreshing access token.")
        }

        log.info("checking token status: authorized=$AUTHORIZED")
    }

}