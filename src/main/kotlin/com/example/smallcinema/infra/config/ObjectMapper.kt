package com.example.smallcinema.infra.config

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary


@Bean
@Primary
fun objectMapper(): ObjectMapper {
    val objectMapper = ObjectMapper()
    objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true)
    objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_VALUES, true)
    objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS, true)
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    return objectMapper
}