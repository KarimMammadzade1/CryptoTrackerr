package com.aqsin.cryptotracker.ui.history

import androidx.recyclerview.widget.LinearLayoutManager
import com.aqsin.cryptotracker.ui.home.CryptoRatesAdapter
import com.aqsin.cryptotracker.R
import com.aqsin.cryptotracker.data.model.CryptoModel
import com.aqsin.helpers.adapters.BaseAdapterViewHolder
import com.aqsin.helpers.adapters.BaseRecyclerAdapter
import kotlinx.android.synthetic.main.crypto_history_item.view.*

class CryptoHistoryAdapter : BaseRecyclerAdapter<CryptoModel>() {

    override fun getLayout() = R.layout.crypto_history_item

    override fun onBindViewHolder(holder: BaseAdapterViewHolder, position: Int) {
        val item = list[position]
        holder.itemView.crypto_date.text = item.date

        val adapter = CryptoRatesAdapter()
        holder.itemView.crypto_history_list.layoutManager = LinearLayoutManager(holder.itemView.context)
        holder.itemView.crypto_history_list.adapter = adapter
        adapter.setData(item.crypto)
    }



}