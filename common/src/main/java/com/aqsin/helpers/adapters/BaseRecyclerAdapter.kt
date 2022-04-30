package com.aqsin.helpers.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerAdapter<T> : RecyclerView.Adapter<BaseAdapterViewHolder>() {

    abstract fun getLayout() : Int
    abstract override fun onBindViewHolder(holder: BaseAdapterViewHolder, position: Int)

    protected var list = arrayListOf<T>()
    var onClickEvent : ((data : T) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseAdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(getLayout(), parent, false)
        return BaseAdapterViewHolder(view)
    }

    fun setData(l : List<T>){
        list.clear()
        list.addAll(l)
        notifyDataSetChanged()
    }

    override fun getItemCount() = list.size
}


class BaseAdapterViewHolder(view : View) : RecyclerView.ViewHolder(view)