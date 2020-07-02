package com.example.swenson.network

data class RatesResponse(
    val base: String,
    val date: String,
    val success: Boolean,
    val timestamp: Int,
    val rates: Map<String, Double>  = HashMap()
)

