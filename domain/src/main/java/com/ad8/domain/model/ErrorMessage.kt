package com.ad8.domain.model

import com.ad8.domain.util.HttpErrors


data class ErrorMessage(
    val message: String?,
    val status: HttpErrors,
)