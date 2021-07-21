package com.example.smallcinema.domain.exception

abstract class ImdbClientException : SmallCinemaException()

class ImdbClientFailureException() : ImdbClientException() {
    override val message = "The Omdb client don't respond"
    override val errorCode = 4001
}

class MovieNotFoundOnImdbException(imdbId: String) : ImdbClientException() {
    override val message = "The $imdbId movie don't exists on API"
    override val errorCode = 4002
}