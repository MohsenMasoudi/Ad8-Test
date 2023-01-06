package com.ad8.presentation.util.extentions

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

fun<T : ViewDataBinding> inflateView( parent: ViewGroup,layoutId: Int): T {
    return  DataBindingUtil.inflate(
    LayoutInflater.from(parent.context),
        layoutId,
    parent,
    false
    )
}