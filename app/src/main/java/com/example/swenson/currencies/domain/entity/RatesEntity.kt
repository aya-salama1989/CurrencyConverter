package com.example.swenson.currencies.domain.entity


data class RatesEntity (
    val base: String,
    val date: String,
    val success: Boolean,
    val timestamp: Int,
    val rates: List<CurrencyEntity>
)