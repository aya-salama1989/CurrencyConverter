package com.example.swenson.currencies.data.model

import com.example.swenson.currencies.domain.entity.CurrencyEntity
import com.example.swenson.currencies.domain.entity.RatesEntity


fun RatesResponse.mapToEntity()
        =
    RatesEntity(success = success,
        base = base,
        date = date,
        timestamp = timestamp,
        rates = rates.map {
            CurrencyEntity(
                it.key,
                it.value
            )
        })




