package com.example.swenson.currencies

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.swenson.network.CurrenciesAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class CurrenciesViewModel:ViewModel() {


    private var viewModelJob = Job()

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _currencies = MutableLiveData<Map<String, Double>>()


    init {
        getCurrencies()
    }


    private fun getCurrencies(){
        coroutineScope.launch {
            val data = CurrenciesAPI.retrofitService.getPropertiesAsync()
            try {
                _currencies.value = data.rates
            }catch (e: Exception) {
               Log.e("error", data.toString())
            }
        }
    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }


    val currenciesList: LiveData<List<Currency>> = Transformations.map(_currencies){ it ->
        it.map { toCurrency(it.key, it.value) }
    }

    private fun toCurrency(key:String, value:Double): Currency{
        return Currency(key, value)
    }

}

