package com.example.swenson.network

import com.example.swenson.BuildConfig
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


private const val BASE_URL = "http://data.fixer.io/api/"

private const val BASE_AUTH = "latest?access_key=ddc52d04fe4882b5144c1d7a166989b6"


interface CurrenciesApiService {
    @GET("latest?access_key=ddc52d04fe4882b5144c1d7a166989b6")
    fun getPropertiesAsync():
            Deferred<RatesResponse>
}


object MarsApi {
    val retrofitService : CurrenciesApiService by lazy {
        retrofit.create(CurrenciesApiService::class.java) }
}




fun getLogger(): OkHttpClient.Builder {
    val httpClient = OkHttpClient.Builder()
    val logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.BODY
    return  httpClient.addInterceptor(logging)
}



private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .client(if(BuildConfig.DEBUG) getLogger().build() else null).build()



