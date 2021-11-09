package com.ufpb.aps.spotags.controller

import com.ufpb.aps.spotags.env.Variables
import com.ufpb.aps.spotags.service.SpotifyClient
import com.ufpb.aps.spotags.service.TokenChecker
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.view.RedirectView

@RestController
@RequestMapping("spotify")
class NewTokenController(
        @Autowired
        val env: Variables,
        val client: SpotifyClient
) {

    @GetMapping(value = ["/authorize"])
    fun authorize(): RedirectView? {
        val redirectView = RedirectView()

        redirectView.url = "${env.AUTHORIZE_URL}?client_id=${env.CLIENT_ID}&response_type=code&redirect_uri=${env.REDIRECT_URL}"
        return redirectView
    }

    @GetMapping("auth")
    fun getToken(@RequestParam code: String): ResponseEntity<Any> {

        try {
            val tokenResponse = client.getToken(code)
            TokenChecker.ACCESS_TOKEN = tokenResponse!!.accessToken
            TokenChecker.REFRESH_TOKEN = tokenResponse.refreshToken
            TokenChecker.AUTHORIZED = true

            return ResponseEntity.ok(tokenResponse)

        } catch (e: Exception) {
            return ResponseEntity.badRequest().body(
                    mapOf("error" to "expired code, authorize again")
            )
        }
    }
}