package com.aqsin.cryptotracker.ui.home

import androidx.recyclerview.widget.LinearLayoutManager
import com.aqsin.cryptotracker.R
import com.aqsin.cryptotracker.data.model.Crypto
import com.aqsin.helpers.adapters.BaseAdapterViewHolder
import com.aqsin.helpers.adapters.BaseRecyclerAdapter
import kotlinx.android.synthetic.main.crypto_item.view.*

class CryptoRatesAdapter : BaseRecyclerAdapter<Crypto>() {

    override fun getLayout() = R.layout.crypto_item

    override fun onBindViewHolder(holder: BaseAdapterViewHolder, position: Int) {
        val item = list[position]
        holder.itemView.crypto_title.text = item.name.uppercase()

        val adapter = CryptoRatesItemAdapter()
        holder.itemView.crypto_rate.layoutManager = LinearLayoutManager(holder.itemView.context)
        holder.itemView.crypto_rate.adapter = adapter
        adapter.setData(item.rates)

        holder.itemView.setOnClickListener {
            onClickEvent?.invoke(item)
        }
    }



}