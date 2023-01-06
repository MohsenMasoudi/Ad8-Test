package com.ad8.presentation.base

import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer


abstract class BaseDialogFragment<VDB : ViewDataBinding>(
    @LayoutRes private val resId: Int
) : DialogFragment() {

    abstract fun expireToken()
    lateinit var viewDataBinding: VDB





    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = DataBindingUtil.inflate(
            inflater, resId, container, false
        )
        return viewDataBinding.root
        //return inflater.inflate(resId, container, false)
    }

    fun <T> LiveData<T>.observe(observer: (T) -> Unit) {
        observe(this@BaseDialogFragment, Observer {
            it?.let { observer(it) }
        })
    }




    fun dpToPx(dp: Float): Int = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp,
        requireContext().resources.displayMetrics
    ).toInt()
}