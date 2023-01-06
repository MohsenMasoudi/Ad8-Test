package com.ad8.presentation.util.recycler_view

import android.view.View

interface RecyclerViewItemClick {
    fun<T> onItemClick(position:Int, view: View,t:T)
}