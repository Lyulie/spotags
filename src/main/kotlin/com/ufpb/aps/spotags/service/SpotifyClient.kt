package com.ufpb.aps.spotags.service

import com.fasterxml.jackson.annotation.JsonProperty
import com.ufpb.aps.spotags.env.Variables
import org.jboss.logging.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient

@Component
class SpotifyClient(
        @Autowired
        val env: Variables
) {
    val log = Logger.getLogger(SpotifyClient::class.java)

    val client: WebClient = WebClient.create()

    fun getToken(CODE: String): TokenResponse? {

        var params = "?grant_type=${env.GRANT_TYPE_CODE}"
        params += "&code=$CODE"
        params += "&client_id=${env.CLIENT_ID}"
        params += "&client_secret=${env.CLIENT_SECRET}"
        params += "&redirect_uri=${env.REDIRECT_URL}"

        return generateTokenResponse(params)
    }

    fun generateTokenResponse(params: String): TokenResponse? = client.post()
                .uri(env.TOKEN_URL + params)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .header(HttpHeaders.AUTHORIZATION, env.AUTHORIZATION)
                .exchangeToMono { it.bodyToMono(TokenResponse::class.java) }.block()
}

class TokenResponse(
        @JsonProperty("access_token")
        val accessToken: String,
        @JsonProperty("token_type")
        val tokenType: String,
        @JsonProperty("expires_in")
        val expiresIn: Int,
        @JsonProperty("refresh_token")
        val refreshToken: String?
)