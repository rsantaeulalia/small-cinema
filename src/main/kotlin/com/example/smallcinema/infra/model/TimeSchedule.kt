package com.example.smallcinema.infra.model

import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime

data class TimeSchedule(
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    val time: LocalDateTime,
    val price: Float
)
