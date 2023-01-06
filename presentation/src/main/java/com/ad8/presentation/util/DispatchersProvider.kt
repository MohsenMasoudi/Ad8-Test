package com.ad8.presentation.util

import kotlinx.coroutines.CoroutineDispatcher


interface DispatchersProvider {
    fun getIO(): CoroutineDispatcher
    fun getMain(): CoroutineDispatcher
    fun getDefault(): CoroutineDispatcher
}