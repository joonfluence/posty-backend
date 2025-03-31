package com.posty.web

import com.posty.web.service.BatchPostGenerator
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WebApplication(
	private val batchBlogGenerator: BatchPostGenerator,
) : CommandLineRunner {
	override fun run(vararg args: String?) {
		batchBlogGenerator.runBatch()
	}
}

fun main(args: Array<String>) {
	runApplication<WebApplication>(*args)
}
