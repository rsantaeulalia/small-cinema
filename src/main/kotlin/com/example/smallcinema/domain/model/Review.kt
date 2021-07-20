package com.example.smallcinema.domain.model

data class Review(val id: Long, val customerName: String? = null, val comment: String? = null, val rating: Float)