package com.example.swenson.currencies.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.swenson.R
import kotlinx.android.synthetic.main.fragment_currencies.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class CurrenciesFragment : Fragment(R.layout.fragment_currencies) {


    private val viewModel: CurrenciesViewModel by lazy {
        ViewModelProvider(this).get(CurrenciesViewModel::class.java)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.currenciesList.observe(viewLifecycleOwner, Observer {
            //TODO: get base currency from API
            //TODO: call the API once and for all
            //TODO: Send args
            val adapter = CurrenciesAdapter(
                CurrenciesAdapter.CurrencyClickListener {
                    val bundle = Bundle()
                    bundle.putParcelable("currency", it)
                    findNavController().navigate(R.id.conversionFragment, bundle)
                })
            rvCurrencies.adapter = adapter
            adapter.addHeaderAndSubmitList(it)
        })
    }

}