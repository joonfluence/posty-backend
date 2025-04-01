package com.posty.web

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync

@EnableAsync
@SpringBootApplication
class WebApplication

fun main(args: Array<String>) {
	runApplication<WebApplication>(*args)
}
