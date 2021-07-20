package com.example.smallcinema.domain.exception

abstract class MovieException: SmallCinemaException()

class MovieNotFoundException(title: String) : MovieException() {
    override val message = "The movie $title doesn't exists"
    override val errorCode = 5001
}
