package com.aqsin.cryptotracker.ui.home

import com.aqsin.cryptotracker.R
import com.aqsin.cryptotracker.data.model.Rate
import com.aqsin.helpers.adapters.BaseAdapterViewHolder
import com.aqsin.helpers.adapters.BaseRecyclerAdapter
import kotlinx.android.synthetic.main.cryppto_rate_item.view.*


class CryptoRatesItemAdapter : BaseRecyclerAdapter<Rate>() {

    override fun getLayout() = R.layout.cryppto_rate_item

    override fun onBindViewHolder(holder: BaseAdapterViewHolder, position: Int) {
        val item = list[position]
        holder.itemView.currency.text = item.currency.uppercase()
        holder.itemView.rate.text = item.rate.toString()
    }
}