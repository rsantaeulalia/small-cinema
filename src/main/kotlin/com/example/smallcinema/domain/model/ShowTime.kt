package com.example.smallcinema.domain.model

import java.time.DayOfWeek
import java.time.LocalDateTime

data class ShowTime(val id: Long, val day: DayOfWeek, val times: List<LocalDateTime>)
