package com.example.swenson.currencies.domain

import com.example.swenson.currencies.data.model.RatesResponse

interface CurrenciesRepository {

    suspend fun getRates(): RatesResponse
}