package com.example.smallcinema.domain.action

interface SmallCinemaAction<out T> {
    fun execute(): T
}