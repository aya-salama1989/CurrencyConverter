package com.example.swenson.currencies.data.datasource

import com.example.swenson.currencies.data.model.RatesResponse
import com.example.swenson.network.RetrofitBuilder.retrofit
import retrofit2.http.GET

interface CurrenciesApiService {
    @GET("latest?access_key=ddc52d04fe4882b5144c1d7a166989b6")
    suspend fun getPropertiesAsync(): RatesResponse
}



class CurrenciesAPI {
    val retrofitService : CurrenciesApiService by lazy {
        retrofit.create(CurrenciesApiService::class.java) }
}