package com.example.smallcinema

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@OpenAPIDefinition(
	info = Info(
		title = "small-cinema",
		description = "Small cinema API - Only Fast and Furious Movies allowed",
		version = "1.0.0"
	)
)
class SmallcinemaApplication

fun main(args: Array<String>) {
	runApplication<SmallcinemaApplication>(*args)
}
