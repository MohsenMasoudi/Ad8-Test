package com.ad8.presentation.util.extentions

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


fun RecyclerView.isEnd(
    isEndOfRecyclerView: IsEndOfRecyclerView,
    isHorizontal: Boolean = false,
    isRtl: Boolean = false
) {
    this.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            if (isHorizontal) {
                if (isRtl) {
                    if (dx < 0) {
                        val visibleItemCount = layoutManager!!.childCount
                        val totalItemCount = layoutManager!!.itemCount
                        val pastVisibleItems = (layoutManager!! as LinearLayoutManager).findFirstVisibleItemPosition()
                        if (visibleItemCount + pastVisibleItems+1 >= totalItemCount) {
                            isEndOfRecyclerView.onReachToEnd()
                        }
                    }
                } else {
                    if (dx > 0) {
                        val visibleItemCount = layoutManager!!.childCount
                        val totalItemCount = layoutManager!!.itemCount
                        val pastVisibleItems = (layoutManager!! as LinearLayoutManager).findFirstVisibleItemPosition()
                        if (visibleItemCount + pastVisibleItems+1 >= totalItemCount) {
                            isEndOfRecyclerView.onReachToEnd()

                        }
                    }
                }

            } else {
                if (dy > 0) {
                    val visibleItemCount = layoutManager!!.childCount
                    val totalItemCount = layoutManager!!.itemCount
                    val pastVisibleItems =
                        (layoutManager!! as LinearLayoutManager).findFirstVisibleItemPosition()
                    if (visibleItemCount + pastVisibleItems >= totalItemCount) {
                        isEndOfRecyclerView.onReachToEnd()
                    }
                }
            }

        }
    })
}

interface IsEndOfRecyclerView {
    fun onReachToEnd()
}