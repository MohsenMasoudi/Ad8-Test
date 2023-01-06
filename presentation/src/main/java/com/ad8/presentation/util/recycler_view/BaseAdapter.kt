package com.ad8.presentation.util.recycler_view

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T> : RecyclerView.Adapter<BaseHolder<T>>() {

    var list: MutableList<T> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: BaseHolder<T>, position: Int) {
        holder.bindUI(list[position])
    }

    fun addToList(additionalList: MutableList<T>) {
        val addList = list
        val sP = addList.size
        addList.addAll(additionalList)
        val eP = addList.size - 1
        if (sP == 0) {
            notifyDataSetChanged()
        } else {
            notifyItemRangeInserted(sP, eP)
        }
    }
}

abstract class BaseHolder<T>(
    binding: ViewDataBinding
) : RecyclerView.ViewHolder(binding.root) {
    abstract fun bindUI(item: T)
}

