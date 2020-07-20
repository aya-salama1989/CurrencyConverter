package com.example.swenson.currencies.domain.interactor

import com.example.swenson.currencies.data.datasource.CurrenciesAPI
import com.example.swenson.currencies.data.repsitory.CurrenciesRepositoryImp
import com.example.swenson.currencies.data.model.mapToEntity
import com.example.swenson.currencies.domain.CurrenciesRepository
import com.example.swenson.currencies.domain.entity.RatesEntity

class GetCurrenciesUseCase {

    suspend fun build():RatesEntity?{
        val currenciesAPI =
            CurrenciesAPI()
        val currenciesRepository: CurrenciesRepository =
            CurrenciesRepositoryImp(
                currenciesAPI
            )
        return currenciesRepository.getRates().mapToEntity()
    }
}