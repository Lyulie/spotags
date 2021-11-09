package com.ufpb.aps.spotags

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class SpotagsApplication

fun main(args: Array<String>) {
	runApplication<SpotagsApplication>(*args)
}
