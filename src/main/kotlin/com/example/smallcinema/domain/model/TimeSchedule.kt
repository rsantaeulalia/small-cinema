package com.example.smallcinema.domain.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
class TimeSchedule(@Id val id: String? = null, val time: String, val price: Float)
