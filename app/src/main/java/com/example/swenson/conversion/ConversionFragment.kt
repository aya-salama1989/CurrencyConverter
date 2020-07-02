package com.example.swenson.conversion

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.View
import com.example.swenson.R
import com.example.swenson.currencies.Currency
import kotlinx.android.synthetic.main.fragment_conversion.*


class ConversionFragment : Fragment(R.layout.fragment_conversion) {

    lateinit var currency: Currency

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        currency = arguments?.getParcelable("currency")!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        etBaseCurrency.setText("1")
        tvBaseCurrency.text = "EUR"



        etConversionCurrency.setText(currency.value.toString())
        tvConversionCurrency.text = currency.initials

        val rate = currency.value.getRate()


        var textWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (!s.isNullOrEmpty()) {
                    var newValue = rate * s.toString().toDouble()
                    etBaseCurrency.setText("" + newValue)

                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        }

        etConversionCurrency.addTextChangedListener(textWatcher)

    }


    private fun Double.getRate(): Double {
        return 1 / this
    }



}