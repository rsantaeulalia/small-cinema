package com.example.smallcinema.infra.model

data class ShowTime(
    val day: String,
    val schedule: List<TimeSchedule>,
)