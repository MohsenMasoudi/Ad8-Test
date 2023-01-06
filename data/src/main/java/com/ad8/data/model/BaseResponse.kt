package com.ad8.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class BaseResponse(
    val `data`: String
) : Parcelable

