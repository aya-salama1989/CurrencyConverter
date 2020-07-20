package com.example.swenson.currencies.data.repsitory

import com.example.swenson.currencies.data.datasource.CurrenciesAPI
import com.example.swenson.currencies.data.model.RatesResponse
import com.example.swenson.currencies.domain.CurrenciesRepository

class CurrenciesRepositoryImp(private val currenciesAPI: CurrenciesAPI) : CurrenciesRepository {

    override suspend fun getRates(): RatesResponse {
        return currenciesAPI.retrofitService.getPropertiesAsync()
    }
}