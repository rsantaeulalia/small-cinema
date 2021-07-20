package com.example.smallcinema.domain.exception

open class SmallCinemaException(open val errorCode:Int = 500) : RuntimeException()
