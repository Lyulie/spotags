package com.ufpb.aps.spotags.env

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class Variables {

    @Value("\${spotify.authorize-url}")
    lateinit var AUTHORIZE_URL: String

    @Value("\${spotify.token-url}")
    lateinit var TOKEN_URL: String

    @Value("\${spotify.id}")
    lateinit var CLIENT_ID: String

    @Value("\${spotify.secret}")
    lateinit var CLIENT_SECRET: String

    @Value("\${spotify.redirect-url}")
    lateinit var REDIRECT_URL: String

    @Value("\${spotify.auth}")
    lateinit var AUTHORIZATION: String

    val GRANT_TYPE_CODE = "authorization_code"
    val GRANT_TYPE_REFRESH = "refresh_token"

}