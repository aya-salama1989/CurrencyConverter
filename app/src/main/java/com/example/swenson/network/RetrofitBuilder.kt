package com.example.swenson.network

import com.example.swenson.BuildConfig
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


private const val BASE_URL = "http://data.fixer.io/api/"



interface CurrenciesApiService {
    @GET("latest?access_key=ddc52d04fe4882b5144c1d7a166989b6")
    suspend fun getPropertiesAsync():
            RatesResponse
}


object MarsApi {
    val retrofitService : CurrenciesApiService by lazy {
        retrofit.create(CurrenciesApiService::class.java) }
}





fun getClient(): OkHttpClient {
    val httpClient = OkHttpClient.Builder()
    if(BuildConfig.DEBUG){
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        httpClient.addInterceptor(logging)
    }
    return  httpClient.build()
}



private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .client(getClient()).build()



