package com.example.smallcinema.infra.controller

import com.example.smallcinema.domain.exception.ImdbClientFailureException
import com.example.smallcinema.domain.exception.MovieNotFoundException
import com.example.smallcinema.domain.exception.MovieNotFoundOnOmdbException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class ExceptionController : ResponseEntityExceptionHandler() {
    @ExceptionHandler(MovieNotFoundException::class)
    fun handleMovieNotFoundException(ex: MovieNotFoundException): ResponseEntity<Any> {
        val body = mapOf("message" to ex.message)
        return ResponseEntity(body, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(ImdbClientFailureException::class)
    fun handleImdbClientFailureException(ex: ImdbClientFailureException): ResponseEntity<Any> {
        val body = mapOf("message" to ex.message)
        return ResponseEntity(body, HttpStatus.CONFLICT)
    }

    @ExceptionHandler(MovieNotFoundOnOmdbException::class)
    fun handleMovieNotFoundOnOmdbException(ex: MovieNotFoundOnOmdbException): ResponseEntity<Any> {
        val body = mapOf("message" to ex.message)
        return ResponseEntity(body, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(Exception::class)
    fun handleDefaultException(ex: Exception): ResponseEntity<Any> {
        val body = mapOf("message" to ex.message)
        return ResponseEntity(body, HttpStatus.CONFLICT)
    }
}