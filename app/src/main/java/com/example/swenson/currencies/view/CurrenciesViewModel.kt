package com.example.swenson.currencies.view

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.swenson.currencies.domain.interactor.GetCurrenciesUseCase
import com.example.swenson.currencies.domain.entity.CurrencyEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class CurrenciesViewModel:ViewModel() {

    private var viewModelJob = Job()

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _currencies = MutableLiveData<List<CurrencyEntity>>()

    val currenciesList: LiveData<List<CurrencyEntity>>
        get() = _currencies


    init {
        getCurrencies()
    }


    private fun getCurrencies(){
        val getCurrenciesUseCase =
            GetCurrenciesUseCase()
        coroutineScope.launch {
            val data = getCurrenciesUseCase.build()
            try {
                _currencies.value = data?.rates
            }catch (e: Exception) {
               Log.e("error", data.toString())
            }
        }
    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}

