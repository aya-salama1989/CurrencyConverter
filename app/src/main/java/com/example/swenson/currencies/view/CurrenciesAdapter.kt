package com.example.swenson.currencies.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ListAdapter
import com.example.swenson.R
import com.example.swenson.currencies.domain.entity.CurrencyEntity
import kotlinx.android.synthetic.main.item_concurrency.view.*
import kotlinx.android.synthetic.main.item_header.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private val ITEM_VIEW_TYPE_HEADER = 0
private val ITEM_VIEW_TYPE_ITEM = 1

class CurrenciesAdapter (val clickListener: CurrencyClickListener):
    ListAdapter<CurrenciesAdapter.DataItem, RecyclerView.ViewHolder>(
        DiffCallback
    ) {

    private val adapterScope = CoroutineScope(Dispatchers.Default)

    fun addHeaderAndSubmitList(list: List<CurrencyEntity>?) {
        adapterScope.launch {
            val items = when (list) {
                null -> listOf(DataItem.Header)
                else -> listOf(DataItem.Header) + list.map {
                    DataItem.CurrencyItem(
                        it
                    )
                }
            }
            withContext(Dispatchers.Main) {
                submitList(items)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_VIEW_TYPE_HEADER -> TextViewHolder.from(
                parent
            )
            ITEM_VIEW_TYPE_ITEM -> CurrencyViewHolder.from(
                parent
            )
            else -> throw ClassCastException("Unknown viewType ${viewType}")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CurrencyViewHolder -> {
                val currencyItem = getItem(position) as DataItem.CurrencyItem
                holder.bind(currencyItem.currencyEntity, clickListener)
            }
            is TextViewHolder ->{
                holder.itemView.base_currency.text = "EUR"
            }
        }
    }


    companion object DiffCallback : DiffUtil.ItemCallback<DataItem>() {
        override fun areItemsTheSame(
            oldItem: DataItem,
            newItem: DataItem
        ): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: DataItem,
            newItem: DataItem
        ): Boolean {
            return oldItem == newItem
        }
    }


    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is DataItem.Header -> ITEM_VIEW_TYPE_HEADER
            is DataItem.CurrencyItem -> ITEM_VIEW_TYPE_ITEM
        }
    }

    class TextViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        companion object {
            fun from(parent: ViewGroup): TextViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.item_header, parent, false)
                return TextViewHolder(
                    view
                )
            }
        }
    }


    class CurrencyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(currencyEntity: CurrencyEntity, clickListener: CurrencyClickListener) {
            itemView.setOnClickListener {
              clickListener.onClick(currencyEntity)
            }
            itemView.txtCurrency.text = currencyEntity.initials
            itemView.txtValue.text = currencyEntity.value.toString()
        }

        companion object {
            fun from(parent: ViewGroup): CurrencyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.item_concurrency, parent, false)
                return CurrencyViewHolder(
                    view
                )
            }
        }
    }


    sealed class DataItem {
        data class CurrencyItem(val currencyEntity: CurrencyEntity) : DataItem()
        object Header : DataItem()
    }


    class CurrencyClickListener(val clickListener: (currencyEntity: CurrencyEntity) -> Unit){
        fun onClick(currencyEntity: CurrencyEntity) = clickListener(currencyEntity)
    }
}